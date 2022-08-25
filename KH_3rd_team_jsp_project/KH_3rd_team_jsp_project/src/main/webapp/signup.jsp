<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.css">
<script type="text/javascript" src="./resources/js/userForm_validation.js"></script>
<title>Welcome</title>
</head>
<body>
	<%@ include file="menu.jsp"%>
	<div class="jumbotron">
		<div class="container">
			<div class="row">
				<div class="col-md-6 col-md-offset-3">
					<form action="/webmarket/signup.do" method="post" name="signFrm">
						<h3>Sign Up</h3>
						<h4>It's free and always will be.</h4>

						<input type="text" name="name" value=""
							class="form-control input-lg" placeholder="Name">
						<input type="text" id="id" name="id" value=""
							class="form-control input-lg" placeholder="ID">
						<input type="password" id="pass" name="pass" value=""
							class="form-control input-lg" placeholder="Password">
						<input type="password" id="confirm_pass" name="confirm_pass" value=""
							class="form-control input-lg" placeholder="Confirm Password">
						<input type="text" id= "nickname" name="nickname" value=""
							class="form-control input-lg" placeholder="Nickname">
						<input type="text" name="zipCode" value=""
							class="form-control input-lg" placeholder="zipCode, don't need to write it.">
						<input type="text" name="address" value=""
							class="form-control input-lg" placeholder="Address, don't need to write it.">
						<input type="email" id="email" name="email" value=""
							class="form-control input-lg" placeholder="Email">
						<input type="text" id="phoneNum" name="phoneNum" value=""
							class="form-control input-lg" placeholder="phone Number ex) 010-0000-0000">
						<span class="help-block">By clicking Create my
							account, you agree to our Terms and that you have read our Data
							Use Policy, including our Cookie Use.</span>
						<input type ="submit" class="btn btn-primary" value="Create my account" onclick="return CheckUserForm()">
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