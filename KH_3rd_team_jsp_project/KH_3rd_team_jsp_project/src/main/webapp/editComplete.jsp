<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ page import="java.util.List" %>
<%@ page import="dto.Product"%>
<%@ page import="dao.ProductRepository" %>
<html>
<head>
<link rel="stylesheet" href="./resources/css/bootstrap.css">  
<title>수정 완료</title></head>
<body>
	<jsp:include page="menu.jsp"/>
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3" style="text-align: center;">수정 완료</h1>
		</div>
	</div>
	<div class="container">
		<h2 class="alert alert-danger">회원 정보가 변경되었습니다.</h2>
	</div>
	<div class="container">
		<p> <a href="./products.jsp" class="btn btn-secondary">상품 목록 &raquo;</a>
	</div>
	<jsp:include page="footer.jsp"/>
</body>
</html>