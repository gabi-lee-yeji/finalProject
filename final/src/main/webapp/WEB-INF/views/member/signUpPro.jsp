<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1>회원가입Pro</h1>
<c:if test="${result} = 1"></c:if>
<h2>가입완료</h2>

<script>
	alert("가입 되었습니다.");
	window.location="/member/loginForm";
</script>


