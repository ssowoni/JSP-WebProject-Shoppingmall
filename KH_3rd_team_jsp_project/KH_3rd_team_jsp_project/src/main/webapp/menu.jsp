<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
   function joinChating() {
      window.open("<%=request.getContextPath()%>/chating.jsp", "chating", "width=640, height=400")
   }
   function adminChating() {
      window.open("<%=request.getContextPath()%>/admin.jsp", "chating", "width=640, height=400")
   }
</script>

<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
   <div class="container-fluid">
      <div class="container-fluid">
         <a class="navbar-brand" href="./welcome.jsp">Home</a>
      </div>
      <div class="col-md-8 text-end">
         <%
            if(session.getAttribute("id") == null) {
         %>
         <a href="./login.jsp" class="btn btn-secondary">Login</a>
         <a href="./signup.jsp" class="btn btn-secondary">Sign-up</a>
         <a href="./products.jsp"class="btn btn-secondary">상품 목록</a>
         <a href="./list.jsp" class="btn btn-secondary">게시판</a>
         <a href="javascript:joinChating()" class="btn btn-secondary">채팅</a>
         <%
            } else {
         %> 
         
         <strong style="color:white;"><%=session.getAttribute("nickname") %> 님</strong>
         <a href="/webmarket/logout.do" class="btn btn-secondary">Logout</a>
         <a href="/editPasswordCheck.jsp" class="btn btn-secondary">Edit</a>
         <a href="./products.jsp"class="btn btn-secondary">상품 목록</a>
         <a href="../webmarket/shippinglist.do" class="btn btn-secondary">배송 목록</a>
         <a href="javascript:joinChating()" class="btn btn-secondary">채팅</a>
         
         <% if((Integer)session.getAttribute("admin")==1) { %>
            <a href="./addProduct.jsp"class="btn btn-secondary">상품 등록</a>
            <a href="javascript:adminChating()" class="btn btn-secondary">운영자채팅</a>
         <% } %>
         <a href="./list.jsp" class="btn btn-secondary">게시판</a>

         <%
            } 
         %> 
      </div>
   </div>
</nav>