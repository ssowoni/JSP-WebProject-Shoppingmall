<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./isLoggedIn.jsp" %> 
<!-- 로그인 확인 즉 로그인 돼 있어야 글작성 가능 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./resources/css/bootstrap.css">  
<script type="text/javascript" src="./resources/js/write_validation.js"></script>
<title>자유 게시판</title>

</head>
<body>
<jsp:include page="menu.jsp"/>
<div class="jumbotron">
	<div class="container">
		<h1 class="display-3" style="text-align: center;">게시글 작성하기</h1>
	</div>
</div>

<div class="container" style="margin-top:60px">
	<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-6">
			<div class="card shadow">
				<div class="card-body">
					<form name="writeFrm" method="post" enctype="multipart/form-data" action="../webmarket/write.do"  >
						<div class="form-group">
							<label for="board_subject">제목</label>
							<input type="text" name="title" id="title" value="" class="form-control" />
						</div>
						<div class="form-group">
							<label for="board_content">내용</label>
							<textarea name="content" id="content" class="form-control" rows="10" style="resize:none"></textarea>
						</div>
						<div class="form-group">
							<label for="board_file">첨부 이미지</label>
							<input type="file" name="image" class="form-control" />					
						</div>
						<div class="form-group">
							<div class="text-right">
								<input type ="submit" class="btn btn-primary" value="작성완료" onclick="return CheckWriteForm(this)">
								<button type="reset" class="btn btn-info" >다시입력</button>
								<button type="button" class="btn btn-info" onclick="location.href='list.jsp';">목록보기</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="col-sm-3"></div>
	</div>
</div>

</body>
</html>