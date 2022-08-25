package controller;


import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import dao.BoardRepository;
import dto.Board;



@WebServlet("/webmarket/write.do")

public class WriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		ServletContext application = request.getServletContext();
		
		request.setCharacterEncoding("UTF-8");
		String realFolder = "/upload"; // 웹 어플리케이션 상의 절대 경로
		String encType = "utf-8"; // 인코딩 타입
		int maxSize = 5*1024*1024; // 최대 업로드될 파일의 크기 5MB;

		MultipartRequest multi = new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());
		// request 객체, 업로드된 파일이 저장될 경로, 업로드할 파일의 최대 크기, 인코딩 타입, 중복명설정

		String title = multi.getParameter("title");
		String content = multi.getParameter("content");
		
		
		Enumeration images = multi.getFileNames(); // 선택한 file의 name을 가져오는 getFileNames()
		String iname = (String) images.nextElement(); // file 이름을 가져오는 getFilesystemName(태그이름)
		String imageName = multi.getFilesystemName(iname); // file의 원본명을 가져오는 getOriginalFileName
		// 폼페이지에서 전송되어 서버에 업로드된 파일을 가져오도록 MultipartRequest 객체 타입의 getFilesystemName() 메소드를 작성
		
		Board dto = new Board();
		dto.setTitle(title);
		dto.setContent(content);
		dto.setImagename(imageName);
		dto.setNickname(session.getAttribute("nickname").toString());
		
		BoardRepository dao = new BoardRepository(application);
		
		dao.insertWrite(dto); 
		dao.close();
		
		response.sendRedirect("../list.jsp");
		
		

	}
}