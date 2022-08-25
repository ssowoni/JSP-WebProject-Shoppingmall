package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ShippingRepository;
import dto.Shipping;

@WebServlet("/webmarket/shippinglist.do")
public class ShippingListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		
		String userId = (String)session.getAttribute("id");
		ShippingRepository dao = new ShippingRepository();
		ArrayList<Shipping> list = dao.getShippingList(userId);
		
		session.setAttribute("list",list);
		
		response.sendRedirect("../shippingList.jsp");
	}
}
