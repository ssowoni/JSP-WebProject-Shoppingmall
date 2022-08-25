package controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardCommentRepository;
import dto.BoardComment;


@WebServlet("/webmarket/writecomment.do")
public class WriteCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		ServletContext application = request.getServletContext();
		
		
		String comment_board_num = request.getParameter("comment_board_num");
		String comment_content = request.getParameter("comment_content");
		
		BoardComment dto = new BoardComment();
		dto.setComment_board_num(comment_board_num);
		dto.setComment_content(comment_content);
		dto.setComment_nickname(session.getAttribute("nickname").toString());
		
		BoardCommentRepository dao = new BoardCommentRepository(application);
		dao.insertComment(dto);
		dao.plusCommentCount(comment_board_num);
		dao.close();
		
		response.sendRedirect("../view.jsp?num=" +comment_board_num);
		
		
		
		
	}
	}