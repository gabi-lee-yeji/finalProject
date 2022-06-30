<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${result == 1}">
	<c:set var="memid" value="${MemberInfoDTO.id}" scope="session"/>
	<c:redirect url="/member/main"/>
</c:if>

<c:if test="${result != 1}">
	<script>
		alert("아이디/비밀번호가 틀립니다");
		history.go(-1);
	</script>	
</c:if>


<title>loginPro</title>
<h1>로그인Pro</h1>

