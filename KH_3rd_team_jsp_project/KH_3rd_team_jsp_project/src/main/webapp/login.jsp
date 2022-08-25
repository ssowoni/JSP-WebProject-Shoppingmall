<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./resources/css/bootstrap.css">
<title>Welcome</title>
</head>
<body>
	<%@ include file="menu.jsp"%>
	<div class="jumbotron">
		<div class="container">
			<form name="loginFrm" action="/webmarket/login.do" method="post">
				<h1 class="h3 mb-3 fw-normal">Please sign in</h1>
				<div class="form-floating">
					<input type="text" class="form-control" id="id" name="id"
						placeholder="ID"> <label for="id">ID</label>
				</div>
				<div class="form-floating">
					<input type="password" class="form-control" id="pass" name="pass"
						placeholder="Password"> <label for="pass">Password</label>
				</div>
				<button class="w-100 btn btn-lg btn-primary" type="submit">Sign in</button>
			</form>
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