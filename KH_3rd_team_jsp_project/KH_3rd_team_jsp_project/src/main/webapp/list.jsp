<%--모델1 방식은 사용자의 요청을 JSP가 받아 모델을 호출
모델이 요청을 처리한 후 결과를 반환하면 JSP를 통해 응답
즉, JSP에 뷰와 컨트롤러가 혼재된 형태
장점 : 배우기 쉽고, 개발속도가 빠름
단점 : 두가지 기능을 JSP에서 구현하므로 코드가 복잡해지고 유지보수 어려움
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="dao.BoardRepository"%>
<%@ page import="dto.Board"%>
<%@ page import="common.BoardPage"%>

<%

BoardRepository dao = new BoardRepository(application);

Map<String, Object> param = new HashMap<String, Object>();

String searchField = request.getParameter("searchField");
String searchWord = request.getParameter("searchWord");
if(searchWord != null) {
   param.put("searchField", searchField);
   param.put("searchWord", searchWord);
}

int totalCount = dao.selectCount(param);   //게시물 수 확인

//**페이지 처리 start**
//전체 페이지 수 계산
int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE"));
int blockPage = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK"));
int totalPage = (int)Math.ceil((double)totalCount / pageSize); //전체 페이지 수

//현재 페이지 확인
int pageNum = 1; //기본값
String pageTemp = request.getParameter("pageNum");
if(pageTemp!= null && !pageTemp.equals(""))
	pageNum = Integer.parseInt(pageTemp); //요청받은 페이지로 수정
	
//목록에 출력할 게시물 범위 계산
int start = (pageNum -1) * pageSize +1; //첫 게시물 번호
int end = pageNum * pageSize; //마지막 게시물 번호
param.put("start", start);
param.put("end", end);
/*** 페이지 처리 end ***/


List<Board> boardLists = dao.selectListPage(param);   //게시물 목록 받기
dao.close();   //DB 연결 닫기

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./resources/css/bootstrap.css">
<title>자유게시판</title>
</head>
<body>

	<jsp:include page="menu.jsp" />

	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3" style="text-align: center;">자유 게시판</h1>
		</div>
	</div>

	<form method="get">
		<table width=100%>
			<tr>
				<td align="center"><select name="searchField"
					class="btn btn-outline-primary">
						<!-- option 타입으로 ▼ 클릭 시 선택 가능한 것 -->
						<option value="title">제목</option>
						<option value="content">내용</option>
				</select> <input type="text" name="searchWord"
					class="btn btn-outline-primary" /> <input type="submit"
					value="검색하기" class="btn btn-outline-primary" /></td>
			</tr>
		</table>
	</form>

	<!-- 게시글 리스트 -->

	<div class="container" style="margin-top: 60px">
		<div class="card shadow">
			<div class="card-body">
				<table class="table table-hover" id='board_list'>
					<thead class="table-light">
						<tr>
							<th class="text-center d-none d-md-table-cell">글번호</th>
							<th class="w-50">제목</th>
							<th class="text-center d-none d-md-table-cell">작성자</th>
							<th class="text-center d-none d-md-table-cell">작성날짜</th>
						</tr>
					</thead>

					<!-- 목록의내용 -->
					<%
      if(boardLists.isEmpty()) {
         //게시물이 하나도 없을 때
      %>
					<tr>
						<td colspan="5" align="center">등록된 게시물이 없습니다^^*</td>
					</tr>
					<%
      }
      else {
         //게시물이 있을 때
         int virtualNum = 0;   //화면 상의 게시물 번호
         int countNum = 0;
         for(Board dto : boardLists) {
            virtualNum = totalCount - (((pageNum -1) * pageSize) + countNum++);   //전체 게시물 수에서 시작해 1씩 감소
	
      %>

					<tbody>
						<tr>
							<td class="text-center d-none d-md-table-cell"><%= virtualNum %></td>
							<td><a href="./view.jsp?num=<%= dto.getNum() %>"><%= dto.getTitle()%>
									<%
				if(Integer.parseInt(dto.getCommentcount())!=0){	
			%> [ <%=dto.getCommentcount() %> ]</a></td>
							<% } %>
							<td class="text-center d-none d-md-table-cell"><%= dto.getNickname() %></td>
							<td class="text-center d-none d-md-table-cell"><%= dto.getPostdate() %></td>

						</tr>

						<%
         }
      }
      %>
					</tbody>
				</table>


			</div>
		</div>
	</div>


	<div class="d-none d-md-block">
		<ul class="pagination justify-content-center">
			<li class="page-item"><a href="#" class="page-link"> <%= BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, request.getRequestURI()) %></a>
			</li>
		</ul>
	</div>


	<div align="center">
		<!-- 목록 하단의 [글쓰기] 버튼 -->
		<a href="./write.jsp" class="btn btn-primary">글쓰기</a>

	</div>


	<jsp:include page="footer.jsp" />
</body>
</html>
