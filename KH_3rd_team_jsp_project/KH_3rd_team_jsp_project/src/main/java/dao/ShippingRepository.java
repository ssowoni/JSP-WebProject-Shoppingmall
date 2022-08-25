package dao;

import java.util.ArrayList;
import common.JDBConnect;
import dto.Shipping;

public class ShippingRepository extends JDBConnect {
	
	// userId에 해당하는 배송내역들을 List에 담아 리턴
	// 계정에 해당하는 배송목록 조회
	public ArrayList<Shipping> getShippingList(String userId) {
		ArrayList<Shipping> shipping = new ArrayList<Shipping>();
		String query = "SELECT * FROM shippingInfo WHERE userId=? "; // 쿼리문 템플릿
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, userId);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				Shipping dto = new Shipping();
				
				dto.setShippingId(rs.getInt(1));
				dto.setUserId(rs.getString(2));
				dto.setName(rs.getString(3));
				dto.setShipping_date(rs.getDate(4));
				dto.setProductId(rs.getString(5));
				dto.setProductCount(rs.getInt(6));
				dto.setAddress(rs.getString(7));
				dto.setZipCode(rs.getString(8));
				dto.setPhoneNum(rs.getString(9));
				
				shipping.add(dto);
			}
		} catch (Exception e) {
			System.out.println("배송목록 조회 중 예외 발생(인자1개)");
			e.printStackTrace();
		}
		return shipping;
	}

	// 계정에 해당하는 배송정보 상세 조회
	public ArrayList<Shipping> getShippingList(String shippingId, String userId) {
		ArrayList<Shipping> shipping = new ArrayList<Shipping>();
		String query = "SELECT * FROM shippingInfo WHERE shippingId=? AND userId=? "; // 쿼리문 템플릿
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, shippingId);
			psmt.setString(2, userId);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				Shipping dto = new Shipping();
				
				dto.setShippingId(rs.getInt(1));
				dto.setUserId(rs.getString(2));
				dto.setName(rs.getString(3));
				dto.setShipping_date(rs.getDate(4));
				dto.setProductId(rs.getString(5));
				dto.setProductCount(rs.getInt(6));
				dto.setAddress(rs.getString(7));
				dto.setZipCode(rs.getString(8));
				dto.setPhoneNum(rs.getString(9));
				
				shipping.add(dto);
			}
		} catch (Exception e) {
			System.out.println("배송 상세정보 조회 중 예외 발생(인자2개)");
			e.printStackTrace();
		}
		return shipping;
	}
	
	// 특정 데이터만 리턴
	public Shipping getShippingData(String shippingId, String userId) {
		String query = " SELECT name, shipping_date, address, zipCode, phoneNum FROM shippingInfo "
					+ " WHERE shippingId=? AND userId=? AND ROWNUM = 1 ";
					
		Shipping dto = new Shipping();
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, shippingId);
			psmt.setString(2, userId);
			rs = psmt.executeQuery();
					
			if (rs.next()) {
				dto.setName(rs.getString(1));
				dto.setShipping_date(rs.getDate(2));
				dto.setAddress(rs.getString(3));
				dto.setZipCode(rs.getString(4));
				dto.setPhoneNum(rs.getString(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	// 추가
	public void addShipping(Shipping dto) {
		try {
			// 쿼리문 템플릿 준비
			String query= " INSERT INTO shippingInfo "
					+ " VALUES(seq_shippingId.CURRVAL,"
					+ "	? , ? , ? , ? , ? , ? , ? , ?) ";
			
			// 쿼리문 준비
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getUserId());
			psmt.setString(2, dto.getName());
			psmt.setDate(3, dto.getShipping_date());
			psmt.setString(4, dto.getProductId());
			psmt.setInt(5, dto.getProductCount());
			psmt.setString(6, dto.getAddress());
			psmt.setString(7, dto.getZipCode());
			psmt.setString(8, dto.getPhoneNum());

			// 쿼리문 실행
			psmt.executeUpdate();
		} catch(Exception e) {
			System.out.println("Shipping 데이터 추가 중 예외 발생");
			e.printStackTrace();
		}
	}
	
	public void nextSeq() {
		try {
			// 쿼리문 템플릿 준비
			String query= " SELECT seq_shippingId.nextval FROM dual ";
			
			// 쿼리문 준비
			psmt = con.prepareStatement(query);
			psmt.executeUpdate();
		} catch(Exception e) {
			System.out.println("시퀀스 값 증가 도중 예외 발생");
			e.printStackTrace();
		}
	}
}
