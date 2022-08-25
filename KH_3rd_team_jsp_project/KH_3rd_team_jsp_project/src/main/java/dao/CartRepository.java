package dao;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import common.JDBConnect;
import dto.Cart;
import dto.User;
public class CartRepository extends JDBConnect {
	
	// userId에 해당하는 장바구니들을 List에 담아 리턴
	public ArrayList<Cart> getCartList(String userId) {
		ArrayList<Cart> carts = new ArrayList<Cart>();
		String query = "SELECT * FROM carts WHERE userId=? "; // 쿼리문 템플릿

		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, userId);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				Cart dto = new Cart();
				
				dto.setCartId(rs.getInt(1));
				dto.setUserId(rs.getString(2));
				dto.setProductId(rs.getString(3));
				dto.setProductCount(rs.getInt(4));
				dto.setTotalPrice(rs.getInt(5));
				
				carts.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return carts;
	}
	
	// userId와 productId에 해당하는 장바구니를 리턴
	public Cart getCartDTO(String userId, String productId) {
		String query = "SELECT * FROM carts WHERE userId=? AND productId=? "; // 쿼리문 템플릿
		Cart dto = new Cart();
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, userId);
			psmt.setString(2, productId);
			rs = psmt.executeQuery();
					
			if (rs.next()) {
				dto.setCartId(rs.getInt(1));
				dto.setUserId(rs.getString(2));
				dto.setProductId(rs.getString(3));
				dto.setProductCount(rs.getInt(4));
				dto.setTotalPrice(rs.getInt(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	public void addCart(Cart dto) {
		try {
			// 쿼리문 템플릿 준비
			String query= " INSERT INTO carts "
					+ " VALUES(seq_cartId.nextval, ?, ?, ?, ? ) ";
			
			// 쿼리문 준비
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getUserId());
			psmt.setString(2, dto.getProductId());
			psmt.setInt(3, dto.getProductCount());
			psmt.setInt(4, dto.getTotalPrice());
			
			// 쿼리문 실행
			psmt.executeUpdate();
		} catch(Exception e) {
			System.out.println("Cart 추가 중 예외 발생");
			e.printStackTrace();
		}
	}
	
	// 유저 데이터를 받아 DB에 저장되어 있던 내용을 갱신
	public void updateCart(Cart dto) {
		try {
			// 쿼리문 템플릿 준비
			String query ="UPDATE carts "
						+" SET productCount=?, totalPrice=? "
						+" WHERE userId=? AND productId=? ";
			
			// 쿼리문 준비
			psmt = con.prepareStatement(query);
			psmt.setInt(1, dto.getProductCount());
			psmt.setInt(2, dto.getTotalPrice());
			psmt.setString(3, dto.getUserId());
			psmt.setString(4, dto.getProductId());
			
			// 쿼리문 실행
			psmt.executeUpdate();
		} catch(Exception e) {
			System.out.println("카트 수정 중 예외 발생");
			e.printStackTrace();
		}
	}
	

	// 유저 Id에 해당하는 모든 장바구니 삭제
	public void deleteCart(String userId) {
		try {
			String query="DELETE FROM carts WHERE userId=? ";
			psmt = con.prepareStatement(query);
			psmt.setString(1, userId);
			psmt.executeUpdate();
		} catch(Exception e) {
			System.out.println("카트 삭제 중 예외 발생");
			e.printStackTrace();
		}
	}
	
	public void removeCart(String userId, String productId) {
		try {
			String query="DELETE FROM carts WHERE userId=? AND productId=? ";
			psmt = con.prepareStatement(query);
			psmt.setString(1, userId);
			psmt.setString(2, productId);
			psmt.executeUpdate();
		} catch(Exception e) {
			System.out.println("특정 카트 삭제 중 예외 발생");
			e.printStackTrace();
		}
	}
}
