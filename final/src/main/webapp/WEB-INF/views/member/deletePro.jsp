<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>탈퇴Pro</h1>

<c:if test="${sessionScope.sid == null}">
	<script>
		window.location="/member/loginForm";
	</script>
</c:if>
<c:if test="${sessionScope.sid != null}">
	<c:if test="${result == 1}">
		<c:remove var="sid" scope="session"/>
		<script>
			alert("서운하네요...가시는 길 살펴가세요");
			window.location="/member/loginForm";
		</script>
	</c:if>
	<c:if test="${result != 1}">
		<script>
			alert("비밀번호를 확인하세요")
			/* window.location="/member/deleteForm"; */
			history.go(-1)
		</script>
	</c:if>	
</c:if>

