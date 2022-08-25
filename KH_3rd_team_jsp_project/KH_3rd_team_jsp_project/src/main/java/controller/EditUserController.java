package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserRepository;
import dto.User;

@WebServlet("/webmarket/edit.do")
public class EditUserController extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String id = request.getParameter("id");
		String nickname = request.getParameter("nickname");
		UserRepository dao = new UserRepository();
		
		boolean overlap = dao.confirm_nickname(nickname);
		PrintWriter out = response.getWriter();
		User dto = dao.getUserDTO(id);
		dao.close();
		
		if (!dto.getNickname().equals(nickname) && overlap) {
			HttpSession session = request.getSession();
			session.setAttribute("email",dto.getEmail());
			session.setAttribute("name",dto.getName());
			session.setAttribute("address",dto.getAddress());
			session.setAttribute("phoneNum",dto.getPhoneNum());
			
			out.println("<script>alert('이미 존재하는 Nickname 입니다.');"
					+ "location.href='../editUser.jsp';</script>");
			out.flush();
		} else {
			// 폼값을 DTO에 저장
			dto.setPass(request.getParameter("pass"));
			dto.setNickname(nickname);
			dto.setAddress(request.getParameter("address"));
			dto.setEmail(request.getParameter("email"));
			dto.setPhoneNum(request.getParameter("phoneNum"));

			// DAO를 통해 DB에 게시 내용 저장
			dao = new UserRepository();
			dao.updateUser(dto);
			dao.close();

			response.sendRedirect("../editComplete.jsp");
		}
	}
}