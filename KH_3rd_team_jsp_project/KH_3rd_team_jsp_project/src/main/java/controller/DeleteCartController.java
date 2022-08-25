package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CartRepository;
import dto.Cart;

@WebServlet("/webmarket/deletecart.do")
public class DeleteCartController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("id");
		ArrayList<Cart> cartlist = new ArrayList();
		
		CartRepository dao = new CartRepository();
		dao.deleteCart(userId);
		
		session.setAttribute("cartlist",cartlist);
		response.sendRedirect("../cart.jsp");
	}
}