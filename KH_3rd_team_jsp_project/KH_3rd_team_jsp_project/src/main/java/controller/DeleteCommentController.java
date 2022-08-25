package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardCommentRepository;
import dto.BoardComment;

@WebServlet("/webmarket/deletecomment.do")
public class DeleteCommentController extends HttpServlet{


	private static final long serialVersionUID = 1L;
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String comment_num = request.getParameter("comment_num");
		String comment_board_num = request.getParameter("comment_board_num");
		ServletContext application = request.getServletContext();
		
		BoardCommentRepository dao = new BoardCommentRepository(application);
		BoardComment dto = dao.selectComment(comment_num);
		dao.deleteComment(dto);
		dao.minusCommentCount(comment_board_num);
		dao.close();
		
		response.sendRedirect("../view.jsp?num=" +comment_board_num);
		
	}
	
	
}