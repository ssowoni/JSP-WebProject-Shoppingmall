<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dto.Cart" %>
<html><head>
<link rel="stylesheet" href="./resources/css/bootstrap.css"/>
<% request.setCharacterEncoding("utf-8"); %>
<title>주문정보</title></head>
<body>
	<jsp:include page="menu.jsp"/>
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3" style="text-align: center;">주문 정보</h1>
		</div>
	</div>
	
	<div class="container col-8 alert alert-light">
		<div class="text-center">
			<h1>영수증</h1>
		</div>
		<div class="row justify-content-between">
			<div class="col-4" align="left">
				성명: <%=request.getParameter("name") %><br>
				우편번호 : <%=request.getParameter("zipCode") %><br> 
				주소 : <%=request.getParameter("address") %><br>
				연락처 : <%=request.getParameter("phoneNum") %>
			</div>
			<div class="col-4" align="right">
				<p><em>배송일: <%=request.getParameter("shippingDate") %></em>
			</div>
		</div>
		<div>
			<table class="table table-hover">
			<tr>
				<th class="text-center">상품</th>
				<th class="text-center">개수</th>
				<th class="text-center">가격</th>
				<th class="text-center">소계</th>
			</tr>
			<%
				int sum=0;
				ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cartlist");
	
				for(int i =0; i<cartList.size(); i++){ // 상품리스트 하나씩 출력하기
					Cart cart = cartList.get(i);
					sum += cart.getTotalPrice();
			%>
			<tr>
				<td class="text-center"><em><%=cart.getProductId() %></em></td>
				<td class="text-center"><%=cart.getProductCount() %></td>
				<td class="text-center"><%=cart.getTotalPrice()/cart.getProductCount() %>원</td>
				<td class="text-center"><%=cart.getTotalPrice() %>원</td>
			</tr>
			<% } %>
			<tr>
				<td></td>
				<td></td>
				<td class="text-right"><strong>총액: </strong></td>
				<td class="text-center text-danger"><strong><%=sum %></strong></td>
			</tr>
			</table>
			<table>
			<tr>
			<td><a href="../webmarket/shippinginfo.do" class="btn btn-secondary" role="button">이전</a></td>
			<td><form action="../webmarket/addshippinginfo.do" method="post">
			<input type="hidden" name="name" value="<%=request.getParameter("name")%>">
			<input type="hidden" name="zipCode" value="<%=request.getParameter("zipCode")%>">
			<input type="hidden" name="address" value="<%=request.getParameter("address")%>">
			<input type="hidden" name="phoneNum" value="<%=request.getParameter("phoneNum")%>">
			<input type="hidden" name="shippingDate" value="<%=request.getParameter("shippingDate")%>">
			<input type="submit" class="btn btn-success" value="주문 완료">
			</form></td>
			<td><a href="./checkOutCancelled.jsp" class="btn btn-secondary" role="button">취소</a></td>
			</tr></table>
		</div>
	</div>
</body>
</html>