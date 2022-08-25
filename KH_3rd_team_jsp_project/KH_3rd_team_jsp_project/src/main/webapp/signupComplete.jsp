<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ page import="java.util.List" %>
<html>
<head>
<link rel="stylesheet" href="./resources/css/bootstrap.css">  
<title>상품 목록</title></head>
<body>
	<jsp:include page="menu.jsp"/>
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3" style="text-align: center;">가입 완료</h1>
		</div>
	</div>
	<div class="container">
		<h2 class="alert alert-danger">가입을 진심으로 환영합니다!</h2>
		<p> 로그인하여 다양한 상품들을 확인하고 주문해보세요 !
	</div>
	<div class="container">
		<p> <a href="./login.jsp" class="btn btn-secondary">로그인하기 &raquo;</a>
	</div>
	<jsp:include page="footer.jsp"/>
</body>
</html>