<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="dao.BoardRepository"%>
<%@ page import="dao.BoardCommentRepository"%>
<%@ page import="dto.Board"%>
<%@ page import="dto.BoardComment"%>
<%@ include file="./isLoggedIn.jsp" %>
<!-- 로그인 확인 즉 로그인 돼 있어야 글보기 가능 -->



<%
String num = request.getParameter("num"); // 일련번호 받기 

BoardRepository dao = new BoardRepository(application); //게시글 DAO 생성 

Board dto = dao.selectView(num); // 게시물 가져오기 
dao.close(); // DB 연결 해제

BoardCommentRepository comment_dao = new BoardCommentRepository(application); // 댓글 DAO 생성
List<BoardComment> comment_dto = comment_dao.selectAllComment(num); //댓글 가져오기
comment_dao.close();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./resources/css/bootstrap.css">
<script type="text/javascript"
	src="./resources/js/writeComment_validation.js"></script>
<title>회원제 게시판</title>
<script>
	function deletePost() {
		var confirmed = confirm("게시글을 삭제하겠습니까?");
		if (confirmed) {
			var form = document.writeFrm;
			form.method = "post";
			form.action = "../webmarket/deleteboard.do";
			form.submit();
		}
	}
	function deleteComment() {
		var confirmed = confirm("댓글을 삭제하겠습니까?");
		if (confirmed) {
			var form = document.commentFrm;
			form.method = "post";
			form.action = "../webmarket/deletecomment.do";
			form.submit();
		}
	}
</script>
</head>
<body>
	<jsp:include page="menu.jsp" />
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3" style="text-align: center;">게시글 보기</h1>
		</div>
	</div>



	<div class="container" style="margin-top: 60px">
		<div class="row">
			<div class="col-sm-3"></div>
			<div class="col-sm-6">
				<div class="card shadow">
					<div class="card-body">

						<form name="writeFrm">
							<input type="hidden" name="num" value="<%=num%>" />
							<!-- 공통 링크 -->
							<div class="form-group">
								<label for="board_writer_name">작성자</label> <input type="text"
									value="<%=dto.getNickname()%>" class="form-control"
									disabled="disabled" />
							</div>
							<div class="form-group">
								<label for="board_date">작성날짜</label> <input type="text"
									value="<%=dto.getPostdate()%>" class="form-control"
									disabled="disabled" />
							</div>
							<div class="form-group">
								<label for="board_subject">제목</label> <input type="text"
									value="<%=dto.getTitle()%>" class="form-control"
									disabled="disabled" />
							</div>
							<div class="form-group">
								<label for="board_content">내용</label>
								<textarea class="form-control" rows="10" style="resize: none"
									disabled="disabled"><%=dto.getContent()%></textarea>
							</div>
							<div class="form-group">
								<label for="board_file">첨부 이미지</label>
								<%
								if (dto.getImagename() == null) {
								%>
								<input type="text" value="첨부 파일 없음" class="form-control" />
								<%
								} else {
								%>
								<img src="/upload/<%=dto.getImagename()%>" style="width: 80%">
								<%
								}
								%>
							</div>
							<div class="form-group">
								<!--text-right : 태그 요소 우측 정렬-->
								<div class="text-right">
									<%
									if (session.getAttribute("nickname") != null && session.getAttribute("nickname").toString().equals(dto.getNickname())) {
									%>
									<button type="button" class="btn btn-primary"
										onclick="location.href='editList.jsp?num=<%=dto.getNum()%>';">
										수정하기</button>
									<button type="button" class="btn btn-danger"
										onclick="deletePost();">삭제하기</button>
									<%
									} else if ((Integer) session.getAttribute("admin") == 1) {
									%>
									<!-- LoginController의 session값에 admin 값도 추가  -->
									<button type="button" class="btn btn-danger"
										onclick="deletePost();">삭제하기</button>
									<%
									}
									%>
									<button type="button" class="btn btn-info"
										onclick="location.href='list.jsp';">목록보기</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			<div class="col-sm-3"></div>
		</div>
	</div>
	<br>


	<!-- 댓글 창 -->
	<div class="container">
		<div class="row">
			<div class="col-sm-3"></div>
			<div class="col-sm-6">
				<div class="card shadow">
					<div class="card-body">
						<h5>
							<b>댓글 창</b>
						</h5>
						<%
						for (int i = 0; i < comment_dto.size(); i++) {
							BoardComment comment = comment_dto.get(i);
						%>
						<form name="commentFrm">
							<div class="form-group">
								<input type="hidden" name="comment_num"
									value="<%=comment.getComment_num()%>" /> <input type="hidden"
									name="comment_board_num"
									value="<%=comment.getComment_board_num()%>" />
								<%=comment.getComment_nickname()%>
								<%
								if ((session.getAttribute("nickname") != null
										&& session.getAttribute("nickname").toString().equals(comment.getComment_nickname()))
										|| (Integer) session.getAttribute("admin") == 1) {
								%>
								<%-- <a onclick="return confirm('정말로 삭제하시겠습니까?')" href = "../webmarket/deletecomment.do?comment_id=<%= comment.getComment_num() %>" class="btn-primary">삭제</a> --%>
								<!-- <button type="button" style="float: right;" onclick="deleteComment();" >삭제하기</button> -->
								<a onclick="return confirm('정말로 삭제하시겠습니까?')"
									href="../webmarket/deletecomment.do?comment_board_num=<%=comment.getComment_board_num()%>&comment_num=<%=comment.getComment_num()%>"
									class="btn-primary">삭제</a>
								<%
								}
								%>

								<textarea class="form-control" rows="3" style="resize: none"
									disabled="disabled"><%=comment.getComment_content()%></textarea>
							</div>
						</form>

						<%
						}
						%>


						<hr>
						<h5>
							<b>댓글 등록</b>
						</h5>

						<form name="writeCommentFrm" method="post"
							action="../webmarket/writecomment.do">
							<input type="hidden" name="comment_board_num" value="<%=num%>" />
							<div class="form-group">
								<label for="board_content"><%=session.getAttribute("nickname")%></label>
								<textarea class="form-control" name="comment_content" rows="3"
									style="resize: none"></textarea>
							</div>

							<div class="form-group">
								<div class="text-right">
									<input type="submit" class="btn btn-primary" value="댓글등록"
										onclick="return CheckWriteCommentForm(this)">
								</div>
							</div>
						</form>


					</div>
				</div>
			</div>
		</div>
	</div>


	<jsp:include page="footer.jsp" />
</body>
</html>