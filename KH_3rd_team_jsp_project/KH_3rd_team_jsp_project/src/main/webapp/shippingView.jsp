<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dto.Shipping" %>
<%@ page import="dto.Product" %>
<%@ page import="dao.ProductRepository" %>
<%@ page import="java.sql.Date" %>
<html><head>
<link rel="stylesheet" href="./resources/css/bootstrap.css"/>
<title>주문정보</title></head>
<body>
	<jsp:include page="menu.jsp"/>
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3" style="text-align: center;">배송 정보</h1>
		</div>
	</div>
	<% 
		ArrayList<Shipping> shippingList = (ArrayList) session.getAttribute("shippingList");
	%>
	<div class="container col-8 alert alert-light">
		<div class="text-center">
			<h1>영수증</h1>
		</div>
		<div class="row justify-content-between">
			<div class="col-4" align="left">
				성명: <%=(String)session.getAttribute("name") %><br>
				우편번호 : <%=(String)session.getAttribute("zipCode") %><br> 
				주소 : <%=(String)session.getAttribute("address") %><br>
				연락처 : <%=(String)session.getAttribute("phoneNum") %>
			</div>
			<div class="col-4" align="right">
				<p><em>배송일: <%=(Date)session.getAttribute("shippingDate") %></em>
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
				ProductRepository productDao = new ProductRepository();
				for(int i =0; i<shippingList.size(); i++){ // 상품리스트 하나씩 출력하기
					Shipping shipping = shippingList.get(i);
				
					Product product = productDao.getProductById(shipping.getProductId());
			%>
			<tr>
				<td class="text-center"><em><%=shipping.getProductId() %></em></td>
				<td class="text-center"><%=shipping.getProductCount() %></td>
				<td class="text-center"><%=product.getUnitPrice()%>원</td>
				<td class="text-center"><%=product.getUnitPrice()*shipping.getProductCount() %>원</td>
			</tr>

			<% sum += product.getUnitPrice()*shipping.getProductCount(); } %>
			<tr>
				<td></td>
				<td></td>
				<td class="text-right"><strong>총액: </strong></td>
				<td class="text-center text-danger"><strong><%=sum%></strong></td>
			</tr>
			</table>
			<a href="../webmarket/shippinglist.do" class="btn btn-secondary" role="button">이전</a>
			<a href="./welcome.jsp" class="btn btn-secondary" role="button">홈 화면</a>
			<a href="./products.jsp" class="btn btn-secondary" role="button">상품 목록</a>
		</div>
	</div>
</body>
</html>
<%
session.removeAttribute("shippingList");
session.removeAttribute("name");
session.removeAttribute("zipCode");
session.removeAttribute("address");
session.removeAttribute("phoneNum");
session.removeAttribute("shippingDate");
%>