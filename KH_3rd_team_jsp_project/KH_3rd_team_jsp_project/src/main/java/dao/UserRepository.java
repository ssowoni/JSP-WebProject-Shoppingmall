package dao;

import common.JDBConnect;
import dto.User;

public class UserRepository extends JDBConnect {
	
	public UserRepository() {}
	
	// 명시한 아이디에 해당하는 회원 정보를 반환한다.
	public User getUserDTO(String id) {
		User dto = new User(); // 회원 정보 DTO 객체 생성
		String query = "SELECT * FROM users WHERE id=? "; // 쿼리문 템플릿

		try {
			// 쿼리실행
			psmt = con.prepareStatement(query); // 동적 쿼리문 준비
			psmt.setString(1, id); // 쿼리문의 첫번쨰 인파라미터에 값설정
			rs = psmt.executeQuery(); // 쿼리문 실행

			// 결과 처리
			if (rs.next()) {
				// 쿼리 결과로 얻은 회원 정보를 DTO 객체에 저장
				dto.setId(rs.getString(1));
				dto.setPass(rs.getString(2));
				dto.setName(rs.getString(3));
				dto.setNickname(rs.getString(4));
				dto.setZipCode(rs.getString(5));
				dto.setAddress(rs.getString(6));
				dto.setEmail(rs.getString(7));
				dto.setPhoneNum(rs.getString(8));
				dto.setAdmin(rs.getInt(9));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	// 명시한 nickname 값 받아서 DB에 해당 nickname 값이 존재하는지 체크 (중복시 true)
	public boolean confirm_nickname(String nickname) {
		boolean result = false;
		String query = " SELECT * FROM users WHERE nickname= ? "; // 쿼리문 템플릿

		try {
			// 쿼리실행
			psmt = con.prepareStatement(query); // 동적 쿼리문 준비
			psmt.setString(1, nickname); // 쿼리문의 두번째 인파라미터에 값설정
			rs = psmt.executeQuery(); // 쿼리문 실행
			
			// 결과 처리
			if (rs.next()) {
				result = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// 데이터를 받아 DB에 User 추가
	public void insertUser(User dto) {
		try {
			// 쿼리문 템플릿 준비
			String query =" INSERT INTO users VALUES ( "
						 +" ?, ?, ?, ?, ?, ?, ?, ?, ? ) ";
			
			// 쿼리문 준비
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPass());
			psmt.setString(3, dto.getName());
			psmt.setString(4, dto.getNickname());
			psmt.setString(5, dto.getZipCode());
			psmt.setString(6, dto.getAddress());
			psmt.setString(7, dto.getEmail());
			psmt.setString(8, dto.getPhoneNum());
			psmt.setInt(9, dto.getAdmin());

			// 쿼리문 실행
			psmt.executeUpdate();
		} catch(Exception e) {
			System.out.println("User 추가 중 예외 발생");
			e.printStackTrace();
		}
	}
	
	// 지정한 id의 계정을 삭제
	public void deleteUser(String id) {
		try {
			String query="DELETE FROM users WHERE id=?";
			psmt = con.prepareStatement(query);
			psmt.setString(1, id);
			psmt.executeUpdate();
		} catch(Exception e) {
			System.out.println("계정 삭제 중 예외 발생");
			e.printStackTrace();
		}
	}
	
	// 유저 데이터를 받아 DB에 저장되어 있던 내용을 갱신
	public void updateUser(User dto) {
		try {
			// 쿼리문 템플릿 준비
			String query ="UPDATE users"
						+" SET pass=?, nickname=?, zipCode=?,address=?, email=?, phoneNum=? "
						+" WHERE id=?";
			
			// 쿼리문 준비
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getPass());
			psmt.setString(2, dto.getNickname());
			psmt.setString(3, dto.getZipCode());
			psmt.setString(4, dto.getAddress());
			psmt.setString(5, dto.getEmail());
			psmt.setString(6, dto.getPhoneNum());
			psmt.setString(7, dto.getId());
			
			// 쿼리문 실행
			psmt.executeUpdate();
		} catch(Exception e) {
			System.out.println("계정 정보 수정 중 예외 발생");
			e.printStackTrace();
		}
	}
}