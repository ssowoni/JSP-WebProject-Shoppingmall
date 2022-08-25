package dao;


import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;

import common.JDBConnect;
import dto.Board;




public class BoardRepository extends JDBConnect{
	
	   public BoardRepository(ServletContext application) {
		      super(application);
		   }
	   
	   public int selectCount(Map<String, Object> map) {
		      int totalCount =0; //결과(게시물 수)를 담을 변수
		      
		      String query = "SELECT COUNT(*) FROM board";
		      if(map.get("searchWord")!=null) {
		         query += " WHERE " + map.get("searchField") + " "
		               + " LIKE '%" + map.get("searchWord") + "%'";
		      }
		      
		      try {
		         stmt = con.createStatement(); //쿼리문 생성
		         rs = stmt.executeQuery(query); //쿼리 실행
		         rs.next(); // 커서를 첫 번째 행으로 이동
		         totalCount = rs.getInt(1); //첫 번째 칼럼 값을 가져옴

		      }
		      catch (Exception e) {
		         System.out.println("게시물 수를 구하는 중 예외 발생");
		         e.printStackTrace();
		      }
		      return totalCount;      
		   }
	   
	   
	   //검색 조건에 맞는 게시물 목록을 반환합니다(페이징 기능 지원)
	   public List<Board> selectListPage(Map<String, Object> map){
		   List<Board> bbs = new Vector<Board>();
		   //결과 (게시물 목록)을 담을 변수
		   //쿼리문 템플릿
		   String query = "SELECT * FROM("
				   +" SELECT Tb.*, ROWNUM rNum FROM ("
				   +"     SELECT * FROM board";
		   
		   //검색 조건 추가
		   if (map.get("searchWord") != null) {
		         query += " WHERE " + map.get("searchField")
		               + " LIKE '%" + map.get("searchWord") + "%' ";
		      }

		      query += " ORDER BY num DESC "
		    		  +" ) Tb"
		    		  +")"
		    		  +"WHERE rNum BETWEEN ? AND ?";
		      
		      try {
		    	  //쿼리문 완성
		    	  psmt = con.prepareStatement(query);
		    	  psmt.setString(1,  map.get("start").toString());
		    	  psmt.setString(2,  map.get("end").toString());
		    	  
		    	  //쿼리문 실행
		    	  rs = psmt.executeQuery();
		    	  
		    	  while(rs.next()) {
	    		  //한 행(게시물 하나)의 데이터를 DTO에 저장
	    		    Board dto = new Board();
		            dto.setNum(rs.getString("num"));  //일련번호
		            dto.setTitle(rs.getString("title")); //제목
		            dto.setContent(rs.getString("content")); //내용
		            dto.setPostdate(rs.getDate("postdate")); //작성일
		            dto.setNickname(rs.getString("nickname")); //작성자 닉네임
		            dto.setImagename(rs.getString("imagename"));//파일 이름
		            dto.setCommentcount(rs.getString("commentcount")); //댓글 개수
		            
		            bbs.add(dto); //결과 목록에 저장
		    	  }
		    	  
		      }catch(Exception e) {
		    	  System.out.println("게시물 조회 중 예외 발생");
		    	  e.printStackTrace();
		      }
		      //목록반환
		      return bbs;
	   }
	   
	   // 게시글 데이터를 받아 DB에 추가합니다. 
	    public int insertWrite(Board dto) {
	        int result = 0;
	        try {

	            String query = "INSERT INTO board ( "
	                + " num,title,content,nickname,imagename) "
	                + " VALUES ( "
	                + " seq_board_num.NEXTVAL, ?, ?, ?,?)"; //
	            psmt = con.prepareStatement(query); // 동적 쿼리 

	            psmt.setString(1, dto.getTitle());  //1번 물음표는 dto의 title
	            psmt.setString(2, dto.getContent()); //2번 물음표는 dto의 content
	            psmt.setString(3, dto.getNickname());//3번 물음표는 dto의 Id
	            psmt.setString(4, dto.getImagename());

	            result = psmt.executeUpdate();  
	        }
	        catch (Exception e) {
	            System.out.println("게시물 입력 중 예외 발생"); 
	            e.printStackTrace();
	        }
	        return result; 
	    }
	    
	    // 지정한 게시물을 찾아 내용을 반환합니다.
	    public Board selectView(String num) { 
	    	Board dto = new Board();

	    	String query = "SELECT * FROM board "
	    			+ "WHERE num=?";
	        
	        
	        try {
	            psmt = con.prepareStatement(query);
	            psmt.setString(1, num); // 물음표의 값, 인파라미터를 일련번호로 설정 
	            rs = psmt.executeQuery(); // 쿼리 실행 
	            // 결과 처리
	            if (rs.next()) { 
	                dto.setNum(rs.getString(1)); //글번호
	                dto.setTitle(rs.getString(2)); //글제목
	                dto.setContent(rs.getString("content")); 
	                dto.setPostdate(rs.getDate("postdate")); 
	                dto.setNickname(rs.getString("nickname")); 
	                dto.setImagename(rs.getString("imagename"));
	                dto.setCommentcount(rs.getString("commentcount"));
	                
	            }
	        }
	        catch (Exception e) {
	            System.out.println("게시물 상세보기 중 예외 발생");
	            e.printStackTrace();
	        }
	        return dto; 
	    }    
	    
	    
	    // 지정한 게시물을 수정합니다.
	    public int updateEdit(Board dto) { 
	        int result = 0;
	        try {

	            String query = "UPDATE board SET "
	                      + " title=?, content=?, imagename=? "
	                      + " WHERE num=?";

	            psmt = con.prepareStatement(query); 
	            psmt.setString(1, dto.getTitle()); 
	            psmt.setString(2, dto.getContent()); 
	            psmt.setString(3, dto.getImagename());
	            psmt.setString(4, dto.getNum());
	            result = psmt.executeUpdate();
	        }
	        catch (Exception e) {
	            System.out.println("게시물 수정 중 예외 발생"); 
	            e.printStackTrace();
	        }
	        return result; // 결과 반환 
	    }
	    
	    // 지정한 게시물을 삭제합니다.
	    public int deletePost(Board dto) { 
	        int result = 0;
	        try {
	            // 쿼리문 템플릿
	        	//board 테이블에서 num이 조건과 맞는다면 삭제!
	            String query = "DELETE FROM board WHERE num=?"; 
	            // 쿼리문 완성
	            psmt = con.prepareStatement(query); 
	            psmt.setString(1, dto.getNum()); 
	            // 쿼리문 실행
	            result = psmt.executeUpdate(); 
	        }
	        catch (Exception e) {
	            System.out.println("게시물 삭제 중 예외 발생"); 
	            e.printStackTrace();
	        }
	        return result; // 결과 반환 
	    }
   

}
