package dao;
import java.util.List;

import java.util.Vector;

import common.JDBConnect;
import dto.Product;

public class ProductRepository extends JDBConnect {
	
	// 모든 Product 를 List에 담아 리턴
	public List<Product> getProductList(){
		List<Product> product = new Vector<Product>();
		String query= " SELECT * FROM PRODUCTS ";
		try {
			psmt = con.prepareStatement(query);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				Product dto = new Product();
				
				dto.setProductId(rs.getString(1));
				dto.setPname(rs.getString(2));
				dto.setUnitPrice(rs.getInt(3));
				dto.setDescription(rs.getString(4));
				dto.setManufacturer(rs.getString(5));
				dto.setCategory(rs.getString(6));
				dto.setUnitsInStock(rs.getLong(7));
				dto.setCondition(rs.getString(8));
				dto.setFilename(rs.getString(9));
				
				product.add(dto);
			}
		} catch(Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		return product;
	}
	
	// Id 값을 인자로 받아 해당 Id를 가진 Product의 정보를 리턴
	public Product getProductById(String productId) {
		Product productById=null;
		List<Product> products = getProductList();
		
		for(int i=0; i<products.size(); i++) {
			Product product = products.get(i);
			if(product != null&&product.getProductId() != null&&product.getProductId().equals(productId)) {
				productById=product;
				break;
			} 
		}	  
		return productById;
	}
	
	// 데이터를 받아 DB에 Product 추가
	public void insertProduct(Product dto) {
		try {
			// 쿼리문 템플릿 준비
			String query =" INSERT INTO products VALUES ( "
						 +" ?, ?, ?, ?, ?, ?, ?, ?, ? ) ";
			
			// 쿼리문 준비
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getProductId());
			psmt.setString(2, dto.getPname());
			psmt.setInt(3, dto.getUnitPrice());
			psmt.setString(4, dto.getDescription());
			psmt.setString(5, dto.getManufacturer());
			psmt.setString(6, dto.getCategory());
			psmt.setLong(7, dto.getUnitsInStock());
			psmt.setString(8, dto.getCondition());
			psmt.setString(9, dto.getFilename());
			
			// 쿼리문 실행
			psmt.executeUpdate();
		} catch(Exception e) {
			System.out.println("Product 추가 중 예외 발생");
			e.printStackTrace();
		}
	}
}