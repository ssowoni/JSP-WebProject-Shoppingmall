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

@WebServlet("/webmarket/editfirst.do")
public class EditPasswordCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");

		UserRepository dao = new UserRepository();
		User dto = dao.getUserDTO(id);
		dao.close();
		PrintWriter out = response.getWriter();
		
		if(!dto.getPass().equals(pass)) {
			out.println("<script>alert('비밀번호가 일치하지 않습니다.');"
					+ "location.href='../editPasswordCheck.jsp';</script>");
			out.flush();
		}
		else {
			HttpSession session = request.getSession();
			
			session.setAttribute("email",dto.getEmail());
			session.setAttribute("name",dto.getName());
			session.setAttribute("address",dto.getAddress());
			session.setAttribute("phoneNum",dto.getPhoneNum());
			response.sendRedirect("../editUser.jsp");
		}
	}
}
