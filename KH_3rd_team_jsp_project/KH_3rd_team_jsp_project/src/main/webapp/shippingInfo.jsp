<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html><head><link rel="stylesheet" href="./resources/css/bootstrap.css">  
<title>배송 정보</title>
<%
	String phoneNum = (String)session.getAttribute("phoneNum");
	String shipping_date = (String)session.getAttribute("shipping_date");
	String name = (String)session.getAttribute("name");
	String address = (String)session.getAttribute("address");
	String zipCode = (String)session.getAttribute("zipCode");

	session.removeAttribute("phoneNum");
	session.removeAttribute("shipping_date");
	session.removeAttribute("name");
	session.removeAttribute("address");
	session.removeAttribute("zipCode");
%>
</head>
<body>
	<jsp:include page="menu.jsp"/>
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3" style="text-align: center;">배송 정보</h1>
		</div>
	</div>
	<div class="container">
		<form action="./orderConfirmation.jsp" class="form-horizontal" method="post">
			<div class="form-group row">
				<label class="col-sm-2">수신자</label>
				<div class="col-sm-3">
					<input name="name" type="text" class="form-control" value=<%=name%>>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">배송일</label>
				<div class="col-sm-3">
					<input name="shippingDate" type="text" class="form-control" value=<%=shipping_date %> readonly>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">연락처</label>
				<div class="col-sm-3">
					<input name="phoneNum" type="text" class="form-control" value=<%=phoneNum %>>(010-XXXX-XXXX)
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">우편번호</label>
				<div class="col-sm-3">
					<input name="zipCode" type="text" class="form-control" value=<%=zipCode %>>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">주소</label>
				<div class="col-sm-5">
					<input name="address" type="text" class="form-control" value=<%=address %>>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-offset-2 col-sm-10">
					<a href="./cart.jsp" class="btn btn-secondary" role="button">이전</a>
					<input type="submit" class="btn btn-primary" value="등록"/>
					<a href="./checkOutCancelled.jsp" class="btn btn-secondary" role="button">취소</a>
				</div>
			</div>
		</form>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>