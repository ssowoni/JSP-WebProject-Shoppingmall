package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserRepository;
import dto.User;

@WebServlet("/webmarket/signup.do")
public class SignupController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String id = request.getParameter("id");
		String nickname = request.getParameter("nickname");
		UserRepository dao = new UserRepository();
		User dto = dao.getUserDTO(id);
		boolean confirm_nickname = dao.confirm_nickname(nickname);
		
		dao.close();
		PrintWriter out = response.getWriter();

		if (dto.getId() != null) {
			out.println("<script>alert('이미 존재하는 ID 입니다.');"
					+ "location.href='../signup.jsp';</script>");
			out.flush();
		}

		else if (confirm_nickname) {
			out.println("<script>alert('이미 존재하는 Nickname 입니다.');"
					+ "location.href='../signup.jsp';</script>");
			out.flush();
		} else {
			// 폼값을 DTO에 저장
			User Newdto = new User();
			Newdto.setId(id);
			Newdto.setPass(request.getParameter("pass"));
			Newdto.setName(request.getParameter("name"));
			Newdto.setNickname(nickname);
			Newdto.setAddress(request.getParameter("address"));
			Newdto.setEmail(request.getParameter("email"));
			Newdto.setPhoneNum(request.getParameter("phoneNum"));
			Newdto.setAdmin(0);

			// DAO를 통해 DB에 게시 내용 저장
			dao = new UserRepository();
			dao.insertUser(Newdto);
			dao.close();

			// request.getRequestDispatcher("../signupComplete.jsp").forward(request,response);
			response.sendRedirect("../signupComplete.jsp");
		}
	}
}