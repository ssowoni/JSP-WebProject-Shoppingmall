<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import = "dao.UserRepository" %>
<%@ page import = "dto.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./resources/css/bootstrap.css">  
<script type="text/javascript" src="./resources/js/userForm_validation.js"></script>
<%
	String phoneNum = (String)session.getAttribute("phoneNum");
	String email = (String)session.getAttribute("email");
	String name = (String)session.getAttribute("name");
	String address = (String)session.getAttribute("address");

	session.removeAttribute("phoneNum");
	session.removeAttribute("email");
	session.removeAttribute("name");
	session.removeAttribute("address");
%>
<title>Edit</title>
</head>
<body onload="loginCheck();">
	<%@ include file="menu.jsp"%>
	<div class="jumbotron">
		<div class="container">
			<div class="row">
				<div class="col-md-6 col-md-offset-3">
					<form action="/webmarket/edit.do" method="post" name="signFrm">
						<h3>Edit</h3>

						<input type="text" name="name" value=<%=name%>
							class="form-control input-lg" readonly >
						<input type="text" id="id" name="id" value=<%=session.getAttribute("id")%>
							class="form-control input-lg" readonly>
						<input type="password" id="pass" name="pass" value=""
							class="form-control input-lg" placeholder="Password">
						<input type="password" id="confirm_pass" name="confirm_pass" value=""
							class="form-control input-lg" placeholder="Confirm Password">
						<input type="text" id= "nickname" name="nickname" value=<%=session.getAttribute("nickname")%>
							class="form-control input-lg" placeholder="Nickname">
						<input type="text" name="address" value=<%= address %>
							class="form-control input-lg" placeholder="Address, don't need to write it.">
						<input type="email" id="email" name="email" value=<%= email %>
							class="form-control input-lg" placeholder="Email">
						<input type="text" id="phoneNum" name="phoneNum" value=<%= phoneNum %>
							class="form-control input-lg" placeholder="phone Number ex) 010-0000-0000">
						<input type ="submit" class="btn btn-primary" value="Edit my account" onclick="return CheckUserForm()">
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="text-center">
			<h3></h3>
		</div>
	</div>
	<div class="text-center"></div>
	<%@ include file="footer.jsp"%>
</body>
</html>