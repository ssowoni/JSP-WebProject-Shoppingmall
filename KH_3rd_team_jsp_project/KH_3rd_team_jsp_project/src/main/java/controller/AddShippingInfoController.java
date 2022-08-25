package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CartRepository;
import dao.ShippingRepository;

import dto.Cart;
import dto.Shipping;

@WebServlet("/webmarket/addshippinginfo.do")
public class AddShippingInfoController extends HttpServlet {
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
		
		ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cartlist");
	
		java.sql.Date sqlDate = null;
		String reqDate=request.getParameter("shippingDate");
		try {
			java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(reqDate);
			sqlDate = new java.sql.Date(utilDate.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		dao.nextSeq();
		
		for(int i =0; i<cartList.size(); i++){ // 상품리스트 하나씩 출력하기
			Shipping dto = new Shipping();
			dto.setUserId(userId);
			dto.setName(request.getParameter("name"));
			dto.setShipping_date(sqlDate);
			dto.setProductId(cartList.get(i).getProductId());
			dto.setProductCount(cartList.get(i).getProductCount());
			dto.setAddress(request.getParameter("address"));
			dto.setZipCode(request.getParameter("zipCode"));
			dto.setPhoneNum(request.getParameter("phoneNum"));
			dao.addShipping(dto);
		}
		
		CartRepository cartDao = new CartRepository();
		cartDao.deleteCart(userId);
		
		dao.close();
		cartDao.close();
		response.sendRedirect("../thankCustomer.jsp?shippingDate="+reqDate);
	}
}
