<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>비밀번호찾기</h1>

<c:if test="${result == 0}">
	<h1>등록된 아이디가 없습니다.</h1>
	<a href="/member/signUpForm">회원 가입</a>
	<a href="/member/loginForm">로그인 하기</a>
	<a href="/member/idFindForm">아이디 찾기</a>
	<a href="/member/pwFindForm">비밀번호 찾기</a>
</c:if>
<c:if test="${result == 1}">
	<c:if test="${dto.status != '일반'}">
		<script>
		alert("계정이 활동중지 상태입니다. bgm2007@daum.net으로 문의하시기 바랍니다."))
		</script>
	</c:if>
	<h1>비밀번호는 ${dto.passwd} 입니다</h1>	
	<a href="/member/signUpForm">회원 가입</a>
	<a href="/member/loginForm?memid=${dto.memid}">로그인 하기</a>
</c:if>
