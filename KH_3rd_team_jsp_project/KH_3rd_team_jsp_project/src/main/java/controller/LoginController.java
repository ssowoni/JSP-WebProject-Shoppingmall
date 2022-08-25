package controller;

import java.io.IOException;


import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CartRepository;
import dao.UserRepository;
import dto.Cart;
import dto.User;

@WebServlet("/webmarket/login.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		//로그인 폼으로부터 받은 아이디와 패스워드
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");

		//회원 테이블 DAO를 통해 회원 정보 DTO 획득
		UserRepository dao = new UserRepository();
		User dto = dao.getUserDTO(id);
		dao.close();
		PrintWriter out = response.getWriter();
		
		try {
			// 로그인 성공 여부에 따른 처리
			if (dto.getId() == null) {
				// 로그인 실패
				out.println("<script>alert('아이디 또는 비밀번호가 일치하지 않습니다.');" + "location.href='../login.jsp';</script>");
				out.flush();
			} else if (!dto.getPass().equals(pass)) {
				// 로그인 실패
				out.println("<script>alert('아이디 또는 비밀번호가 일치하지 않습니다.');" + "location.href='../login.jsp';</script>");
				out.flush();
			}

			else {
				// 로그인 성공
				HttpSession session = request.getSession();
				CartRepository cart_dao = new CartRepository();
				ArrayList<Cart> cartlist = cart_dao.getCartList(id);
				session.setAttribute("cartlist", cartlist);
				session.setAttribute("id", dto.getId());
				session.setAttribute("nickname", dto.getNickname());
				session.setAttribute("admin", dto.getAdmin());
				response.sendRedirect("../welcome.jsp");
			}
		} catch (Exception e) {
			out.println("<script>alert('아이디 또는 비밀번호가 일치하지 않습니다.');" + "location.href='../login.jsp';</script>");
			out.flush();
		}
	}
}
