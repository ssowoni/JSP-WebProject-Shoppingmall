<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ page import="java.util.List" %>
<html>
<head>
<link rel="stylesheet" href="./resources/css/bootstrap.css">  
<script>
function passwordCheck() {
	if (document.getElementById("pass").value=="") {
		alert("비밀번호를 입력해주세요.");
		return false;
	}
}
</script>
<title>상품 목록</title></head>
<body onload="loginCheck();">
	<%@ include file="menu.jsp"%>
	<div class="jumbotron">
		<div class="container">
			<div class="row">
				<div class="col-md-6 col-md-offset-3">
					<form action="/webmarket/editfirst.do" method="post">
						<h3>Edit</h3>
						<h4>Please enter a password.</h4>
						<input type="hidden" id="id" name="id" value=<%=session.getAttribute("id") %>
							class="form-control input-lg">
						<input type="password" id="pass" name="pass" value=""
							class="form-control input-lg" placeholder="Password">
						<input type ="submit" class="btn btn-primary"
							value="Edit my account" onclick="return passwordCheck()">
					</form>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>