<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dto.Cart" %>
<html>
<head>
<link rel="stylesheet" href="./resources/css/bootstrap.css"/>
<title>장바구니</title>
</head>
<body>
	<jsp:include page="menu.jsp"/>
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3" style="text-align: center;">장바구니</h1>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<table width="100%">
				<tr>
					<td align="left"> <a href="../webmarket/deletecart.do" class="btn btn-danger">삭제하기</a></td>
					<td align="right"><a href="../webmarket/shippinginfo.do" class="btn btn-success">주문하기</a></td>
				</tr>
			</table>
		</div>
		<div style="padding-top: 50px">
			<table class="table table-hover">
				<tr>
					<th>상품</th>
					<th>가격</th>
					<th>수량</th>
					<th>소계</th>
					<th>비고</th>
				</tr>
				<%	
					int sum=0;
					ArrayList<Cart> cartlist = (ArrayList<Cart>) session.getAttribute("cartlist");
					for(int i =0; i< cartlist.size(); i++){ 
						Cart cart = cartlist.get(i);
						sum += cart.getTotalPrice();
				%>
				<tr>
					<td><%=cart.getProductId() %></td>
					<td><%=cart.getTotalPrice()/cart.getProductCount()%></td>
					<td><%=cart.getProductCount() %></td>
					<td><%=cart.getTotalPrice() %></td>
					<td><a href="../webmarket/removecart.do?id=<%=cart.getProductId()%>"class="badge badge-danger">삭제</a></td>
				</tr>
				<% 
					}
				%>
				<tr>
					<th></th>
					<th></th>
					<th>총액</th>
					<th><%=sum %></th>
					<th></th>
				</tr>
			</table>
			<a href="./products.jsp" class="btn btn-secondary"> &laquo; 쇼핑 계속하기</a>
		</div>
		<hr>
	</div>
	<jsp:include page="footer.jsp"/>
</body>
</html>