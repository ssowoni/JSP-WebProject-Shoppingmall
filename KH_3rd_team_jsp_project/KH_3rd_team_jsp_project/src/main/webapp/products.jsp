<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ page import="java.util.List" %>
<%@ page import="dto.Product"%>
<%@ page import="dao.ProductRepository" %>
<html>
<head>
<link rel="stylesheet" href="./resources/css/bootstrap.css"> 
<title>상품 목록</title></head>
<body>
	<jsp:include page="menu.jsp"/>
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3" style="text-align: center;">상품목록</h1>
		</div>
	</div>
	<%
		ProductRepository dao = new ProductRepository(); // 추가
		List<Product> products = dao.getProductList();
	%>
	<div class="container">
		<div class="row" align="center">
			<%
				for(int i=0; i<products.size(); i++){
					Product product = products.get(i);
			%>
			<div class="col-md-4">
				<img src ="/upload/<%=product.getFilename()%>" style="width: 100%">
				<h3><%=product.getPname()%></h3>
				<p><%=product.getDescription()%>
				<p><%=product.getUnitPrice()%>원
				<p><a href="./product.jsp?id=<%=product.getProductId()%>" class="btn btn-secondary" role="button"> 상세 정보 &raquo;</a>
			</div>
			<%
				}
			%>
		</div>
		<hr>
	</div>
	<jsp:include page="footer.jsp"/>
</body>
</html>