<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${result == 0}">
	<h1>등록된 아이디가 없습니다.</h1>
	<a href="/member/signUpForm">회원 가입</a>
	<a href="/member/loginForm">로그인 하기</a>
	<a href="/member/pwFindForm">비밀번호 찾기</a>
</c:if>

<c:if test="${result != 0}">
<h1>id찾기</h1>
<c:if test="${dto.status == 2}">
	<script>
		alert("계정이 활동중지 상태입니다. bgm2007@daum.net으로 문의하시기 바랍니다.")
	</script>
</c:if>
<c:if test="${dto.status == 4}">
	<script>
		alert("계정은 특정 이유로 탈퇴처리 되었습니다. bgm2007@daum.net으로 문의하시기 바랍니다.")
	</script>
</c:if>
<c:if test="${dto.status == 5}">
	<script>
		alert("계정은 현재 휴면상태입니다. 본인확인 페이지로 이동합니다.")
	</script>
</c:if>
	<h1>아이디는 ${dto.memid} 입니다.</h1>
<a href="/member/agreeForm">회원가입</a>
<a href="/member/loginForm?memid=${dto.memid}">로그인 하기</a>
<a href="/member/pwFindForm">비밀번호 찾기</a>
</c:if>