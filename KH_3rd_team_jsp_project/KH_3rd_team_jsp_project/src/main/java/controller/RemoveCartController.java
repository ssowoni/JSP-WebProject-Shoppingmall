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
import dto.Cart;

@WebServlet("/webmarket/removecart.do")
public class RemoveCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("id");
		String productId = request.getParameter("id");
		
		ArrayList<Cart> cartlist = (ArrayList<Cart>)session.getAttribute("cartlist");
		
		CartRepository dao = new CartRepository();
		Cart dto = new Cart();
		for (int i=0; i < cartlist.size(); i++) {
			dto = cartlist.get(i);
			if(dto.getProductId().equals(productId)) {
				
				dao.removeCart(userId,productId);
				session.setAttribute("cartId",dto.getCartId());
				cartlist = dao.getCartList(userId);
				break;
			}
		}
		session.setAttribute("cartlist",cartlist);
		response.sendRedirect("../cart.jsp");
	}
}
