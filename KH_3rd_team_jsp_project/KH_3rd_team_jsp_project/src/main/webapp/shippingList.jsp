<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dto.Shipping" %>
<html><head>
<link rel="stylesheet" href="./resources/css/bootstrap.css"/>
<title>주문정보</title></head>
<body>
	<jsp:include page="menu.jsp"/>
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3"  style="text-align: center;">주문 정보</h1>
		</div>
	</div>

	
	
	<div class="container col-8 alert alert-light">
		<div>
			<table class="table table-hover">
			<tr>
				<th class="text-center">주문번호</th>
				<th class="text-center">상품</th>
				<th class="text-center">개수</th>
				<th class="text-center">배송일</th>
				<th class="text-center">비고</th>
			</tr>
			
			<%
				ArrayList<Shipping> shippingList = (ArrayList) session.getAttribute("list");
				session.removeAttribute("list");
				
				for(int i =0; i<shippingList.size(); i++){ // 상품리스트 하나씩 출력하기
					Shipping shipping = shippingList.get(i);
			%>
			<tr>
			<% if(i==0){ %>
				<td class="text-center"><%=shipping.getShippingId()%></td>
			<% } else if(shipping.getShippingId() != shippingList.get(i-1).getShippingId()){ %>	
			 	<td class="text-center"><%=shipping.getShippingId()%></td>
			<% } else {  %>
				<td> </td>
			<% } %>
				<td class="text-center"><em><%=shipping.getProductId()%></em></td>
				<td class="text-center"><%=shipping.getProductCount()%></td>
				<td class="text-center"><%=shipping.getShipping_date()%></td>
			<% if(shippingList.size() == i+1 ){ %>
				<td class="text-center"><a href="../webmarket/shippingview.do?shippingId=<%=shipping.getShippingId()%>" role="button">상세 보기</a></td>
			<% } else if(shipping.getShippingId() != shippingList.get(i+1).getShippingId()){ %>
				<td class="text-center"><a href="../webmarket/shippingview.do?shippingId=<%=shipping.getShippingId()%>" role="button">상세 보기</a></td>
			<% } %>
			</tr>
			<% } %>
			</table>
		</div>
	</div>
</body>
</html>