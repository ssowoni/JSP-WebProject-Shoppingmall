package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserRepository;
import dto.User;

@WebServlet("/webmarket/shippinginfo.do")
public class ShippingInfoController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("id");
		
		if(userId == null) {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인을 해주세요.');"
					+ "location.href='../login.jsp';</script>");
			out.flush();
		}
		else {
			UserRepository dao = new UserRepository();
			User dto = dao.getUserDTO(userId);
			dao.close();
			
			String sysdate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

			session.setAttribute("name",dto.getName());
			session.setAttribute("shipping_date",sysdate);
			session.setAttribute("address",dto.getAddress());
			session.setAttribute("zipCode",dto.getZipCode());
			session.setAttribute("phoneNum",dto.getPhoneNum());
			
			response.sendRedirect("../shippingInfo.jsp");
		} 
	}
}
