package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CartRepository;
import dao.ProductRepository;
import dto.Cart;
import dto.Product;

@WebServlet("/webmarket/addcart.do")
public class AddCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String userId = (String)session.getAttribute("id");
		
		if (userId==null) {
			out.println("<script>alert('로그인 해주세요.');"
					+ "location.href='../login.jsp';</script>");
			out.flush();
		}
		
		else {
			CartRepository dao = new CartRepository();

			String productId = request.getParameter("productId");
			ProductRepository pr = new ProductRepository();
			Product product = pr.getProductById(productId);
			int unitPrice = product.getUnitPrice();
			
			ArrayList<Cart> cartlist = (ArrayList<Cart>)session.getAttribute("cartlist");
				
			if (cartlist.size() != 0) {
				Cart dto = new Cart();
				for (int i=0; i < cartlist.size(); i++) {
					dto = cartlist.get(i);
					if(dto.getProductId().equals(productId)) {
						dto.setProductCount(dto.getProductCount()+1);
						dto.setTotalPrice(dto.getTotalPrice()+unitPrice);
						dao.updateCart(dto);
						break;
					}
				}
				if(!dto.getProductId().equals(productId)) {
					Cart Newdto = new Cart();

					Newdto.setUserId(userId);
					Newdto.setProductId(productId);
					Newdto.setProductCount(1);
					Newdto.setTotalPrice(unitPrice);
					dao.addCart(Newdto);
				}

			} else {
				Cart Newdto = new Cart();

				Newdto.setUserId(userId);
				Newdto.setProductId(productId);
				Newdto.setProductCount(1);
				Newdto.setTotalPrice(unitPrice);
				
				dao.addCart(Newdto);
			}
			cartlist = dao.getCartList(userId);
			session.setAttribute("cartlist",cartlist);
			dao.close();
			response.sendRedirect("../product.jsp?id="+productId);
		}
	}
}