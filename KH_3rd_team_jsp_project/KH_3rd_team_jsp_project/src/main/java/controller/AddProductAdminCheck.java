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

@WebServlet("/webmarket/admincheck.do")
public class AddProductAdminCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("id");

		UserRepository dao = new UserRepository();
		User dto = dao.getUserDTO(id);
		PrintWriter out = response.getWriter();
		dao.close();
		
		if(id == null) {
			out.println("<script>alert('로그인을 선행해주세요.');"
					+ "location.href='../login.jsp';</script>");
			out.flush();
		}
		
		else if(dto.getAdmin() != 1) {
			out.println("<script>alert('권한이 부족합니다.');"
					+ "location.href='../products.jsp';</script>");
			out.flush();
		}
		
		else {
			request.getRequestDispatcher("../webmarket/addproduct.do").forward(request,response);
//			response.sendRedirect("../addProduct.jsp");
		}
	}
}