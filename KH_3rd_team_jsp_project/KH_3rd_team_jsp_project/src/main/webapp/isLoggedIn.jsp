<%@ page import="common.JSFunction" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%	
	if(session.getAttribute("id") ==null){
		JSFunction.alertLocation("로그인 후 이용해주십시오", "../login.jsp",out);
		return; //특정한 조건에서 실행을 멈추고 싶을때는 반드시 return문을 작성해야 한다.
	}
%>