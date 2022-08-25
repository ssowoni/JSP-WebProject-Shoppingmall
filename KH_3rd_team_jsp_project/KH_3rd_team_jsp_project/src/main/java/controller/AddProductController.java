package controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.ProductRepository;
import dto.Product;

@WebServlet("/webmarket/addproduct.do")
public class AddProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String realFolder = "/upload"; // 웹 어플리케이션 상의 절대 경로
		String encType = "utf-8"; // 인코딩 타입
		int maxSize = 5*1024*1024; // 최대 업로드될 파일의 크기 5MB;

		MultipartRequest multi = new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());
		// request 객체, 업로드된 파일이 저장될 경로, 업로드할 파일의 최대 크기, 인코딩 타입, 중복명설정

		String productId = multi.getParameter("productId"); // 상품 아이디
		String pname = multi.getParameter("pname"); // 상품명
		String unitPrice = multi.getParameter("unitPrice"); // 가격
		String description = multi.getParameter("description"); // 상세정보
		String manufacturer = multi.getParameter("manufacturer"); // 제조사
		String category = multi.getParameter("category"); // 분류
		String unitsInStock = multi.getParameter("unitsInStock"); // 재고 수
		String condition = multi.getParameter("condition"); // 상태(신규,중고,재생)
		
		Integer price;
		if(unitPrice.isEmpty()) price=0;
		else price = Integer.valueOf(unitPrice);
		
		long stock;
		if (unitsInStock.isEmpty()) stock=0;
		else stock=Long.valueOf(unitsInStock);
		
		int quantity=0;
		
		Enumeration files = multi.getFileNames(); // 선택한 file의 name을 가져오는 getFileNames()
		String fname = (String) files.nextElement(); // file 이름을 가져오는 getFilesystemName(태그이름)
		String fileName = multi.getFilesystemName(fname); // file의 원본명을 가져오는 getOriginalFileName
		// 폼페이지에서 전송되어 서버에 업로드된 파일을 가져오도록 MultipartRequest 객체 타입의 getFilesystemName() 메소드를 작성
		
		Product newProduct = new Product();
		newProduct.setProductId(productId);
		newProduct.setPname(pname);
		newProduct.setUnitPrice(price);
		newProduct.setDescription(description);
		newProduct.setManufacturer(manufacturer);
		newProduct.setCategory(category);
		newProduct.setUnitsInStock(stock);
		newProduct.setCondition(condition);
		newProduct.setFilename(fileName);
		
		ProductRepository dao = new ProductRepository();
		dao.insertProduct(newProduct);
		dao.close();
		
		response.sendRedirect("../products.jsp");
		// 해당 페이지로 돌림
	}
}