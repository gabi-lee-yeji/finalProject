<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>deleteForm</title>
<h1>회원 탈퇴</h1>

<c:if test="${sessionScope.sid == null}">
	<c:redirect url="/member/loginForm"/>
</c:if>

<c:if test="${sessionScope.sid != null}">
<form name="myForm" action="/member/deletePro">
	<input type="text" name="memid" value="${sessionScope.sid}" readonly/><br/>
	<input type="password" name="passwd"/><br/>
	<input type="submit" value="탈퇴" required/>
	<button type="button" onclick="window.location='/member/main'">돌아가기</button>
</form>
</c:if>