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

@WebServlet("/webmarket/shippingview.do")
public class ShippingViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String shippingId = request.getParameter("shippingId");
		String userId = (String)session.getAttribute("id");
		
		ShippingRepository shippingDao = new ShippingRepository();
		ArrayList<Shipping> dtoList = shippingDao.getShippingList(shippingId, userId);

		Shipping dto = shippingDao.getShippingData(shippingId, userId);
		
		session.setAttribute("name", dto.getName());
		session.setAttribute("zipCode", dto.getZipCode());
		session.setAttribute("address", dto.getAddress());
		session.setAttribute("phoneNum", dto.getPhoneNum());
		session.setAttribute("shippingDate", dto.getShipping_date());
		session.setAttribute("shippingList", dtoList);
		
		response.sendRedirect("../shippingView.jsp");
	}
}