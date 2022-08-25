package controller;

import java.io.File;
import java.io.IOException;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardCommentRepository;
import dao.BoardRepository;
import dto.Board;
import dto.BoardComment;



@WebServlet("/webmarket/deleteboard.do")
public class DeleteBoardController extends HttpServlet{


	private static final long serialVersionUID = 1L;
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String num = request.getParameter("num"); //일련번호 얻기
		ServletContext application = request.getServletContext();
		
		
		//게시글에 달린 댓글도 모두 삭제한다.
		String comment_board_num = request.getParameter("num");
		BoardCommentRepository comment_dao = new BoardCommentRepository(application);
		comment_dao.deleteAllComment(comment_board_num);
		
		//게시글을 아예 삭제한다.
		Board dto = new Board(); //DTO 객체 생성
		BoardRepository dao = new BoardRepository(application); //DAO 객체 생성
		dto = dao.selectView(num); //주어진 일련번호에 해당하는 기존 게시물 얻기
		dto.setNum(num);
		
		dao.deletePost(dto); //삭제
		dao.close();
		

		
	
	      String sDirectory = request.getServletContext().getRealPath("/Uploads");
	      String saveImagename = dto.getImagename();
	      File file= new File(sDirectory + File.separator + saveImagename);
	     
	      //파일 존재한다면 파일 삭제
	      if(file.exists()) {
	         file.delete();
	      }
	      
	     response.sendRedirect("../list.jsp");
		
		
	}
}

