package dao;

import java.util.List;
import java.util.Vector;

import javax.servlet.ServletContext;

import common.JDBConnect;
import dto.BoardComment;

public class BoardCommentRepository extends JDBConnect{
	
	   public BoardCommentRepository(ServletContext application) {
		      super(application);
		   }
	  
	   //게시글 번호를 받아 게시글에 해당 댓글을 조회한다.
		public List<BoardComment> selectAllComment(String comment_board_num){
			List<BoardComment> comment = new Vector<BoardComment>();
			
			String query= " SELECT * FROM board_comment "
					+ "WHERE comment_board_num=? "
					+ " ORDER BY comment_num " ;
			try {
				psmt = con.prepareStatement(query);
				psmt.setString(1,comment_board_num);
				rs = psmt.executeQuery();
				
				while(rs.next()) {
					BoardComment dto = new BoardComment ();
					
					dto.setComment_num(rs.getString("comment_num"));
					dto.setComment_board_num(rs.getString("comment_board_num"));
					dto.setComment_content(rs.getString("comment_content"));
					dto.setComment_nickname(rs.getString("comment_nickname"));
					dto.setComment_date(rs.getDate("comment_date"));
					
					comment.add(dto);
				}
			} catch(Exception e) {
				System.out.println("댓글 조회 중 예외 발생");
				e.printStackTrace();
			}
			return comment;
		}
		
		
		//선택된 해당 번호의 댓글 하나만 선택해 조회한다.
		public BoardComment selectComment(String comment_num) {
			BoardComment dto = new BoardComment (); 
			String query = "SELECT * FROM board_comment WHERE comment_num=?"; 
			try {
				psmt = con.prepareStatement(query);
				psmt.setString(1, comment_num);
				rs = psmt.executeQuery(); 
				
				if(rs.next()) { 

					dto.setComment_num(rs.getString("comment_num"));
					dto.setComment_board_num(rs.getString("comment_board_num"));
					dto.setComment_content(rs.getString("comment_content"));
					dto.setComment_nickname(rs.getString("comment_nickname"));
					dto.setComment_date(rs.getDate("comment_date"));

					
				}
			}catch(Exception e) {
				System.out.println("선택한 댓글 조회 중 예외 발생");
				e.printStackTrace();
			}
			
			return dto; 
		}
		
		
		
		
		//form에서 데이터를 받아 DB에 댓글 추가 
		public int insertComment(BoardComment dto) {
	        int result = 0;
	        try {

	            String query = "INSERT INTO board_comment ( "
	                + " comment_num, comment_board_num, comment_content, comment_nickname) "
	                + " VALUES ( "
	                + " seq_comment_num.NEXTVAL, ?, ?, ?)"; //
	            psmt = con.prepareStatement(query); // 동적 쿼리 

	            psmt.setString(1, dto.getComment_board_num()); 
	            psmt.setString(2, dto.getComment_content());
	            psmt.setString(3, dto.getComment_nickname());

	            result = psmt.executeUpdate();  
	        }
	        catch (Exception e) {
	            System.out.println("댓글 입력 중 예외 발생"); 
	            e.printStackTrace();
	        }
	        return result; 
	    }
		
		
	    // 주어진 일련번호에 해당하는 댓글 수를 증가시킨다.
	    public void plusCommentCount(String comment_board_num) { 
	        String query = "UPDATE board SET "
	                     + " commentcount=commentcount+1 "
	                     + " WHERE num=?";
	        try {
	            psmt = con.prepareStatement(query);
	            psmt.setString(1, comment_board_num); 
	            psmt.executeQuery(); // 쿼리 실행 
	        }
	        catch (Exception e) {
	            System.out.println("댓글 개수 증가 중 예외 발생");
	            e.printStackTrace();
	        }
	    }
	    
	    // 주어진 일련번호에 해당하는 댓글 수를 감소
	    public void minusCommentCount(String comment_board_num) { 
	        String query = "UPDATE board SET "
	                     + " commentcount=commentcount-1 "
	                     + " WHERE num=?";
	        try {
	            psmt = con.prepareStatement(query);
	            psmt.setString(1, comment_board_num); 
	            psmt.executeQuery(); // 쿼리 실행 
	        }
	        catch (Exception e) {
	            System.out.println("댓글 개수 증가 중 예외 발생");
	            e.printStackTrace();
	        }
	    }
	    
	    
	    //선택한 댓글을 삭제한다. 
	    public int deleteComment(BoardComment dto) { 
	        int result = 0;
	        try {
	
	            String query = "DELETE FROM board_comment WHERE comment_num=?"; 
	            // 쿼리문 완성
	            psmt = con.prepareStatement(query);
	            psmt.setString(1, dto.getComment_num()); 
	            // 쿼리문 실행
	            result = psmt.executeUpdate(); 
	        }
	        catch (Exception e) {
	            System.out.println("댓글 삭제 중 예외 발생"); 
	            e.printStackTrace();
	        }
	        return result; // 결과 반환 
	    }
		
		
	    //모든 댓글을 삭제한다.
	    public void deleteAllComment(String comment_board_num) { 
	    	
	    	 String query = "DELETE FROM board_comment WHERE comment_board_num=?"; 
		        try {

	        	 	psmt = con.prepareStatement(query);
		            psmt.setString(1, comment_board_num); 
		            psmt.executeUpdate(); // 쿼리 실행 
		        }
		        catch (Exception e) {
		            System.out.println("댓글 전체 삭제 중 예외 발생"); 
		            e.printStackTrace();
		        }
	    }
	 
	   
}