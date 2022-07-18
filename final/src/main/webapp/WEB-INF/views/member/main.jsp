<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>main</title>
<h1>메인입니다!!</h1>

<c:if test="${sessionScope.sid == null}">
	<c:redirect url="/member/loginForm"/>
</c:if>

<c:if test="${sessionScope.sid != null}">
	${sessionScope.sid} 님이 방문하셨습니다!!
	
	<br/><button type="button" onclick="javascript:window.location='/member/deleteForm'"/>회원탈퇴</button>
	<button type="button" onclick="javascript:window.location='/member/modifyConfirm'"/>회원정보수정</button>
	<button type="button" onclick="window.location='/member/logout'">로그아웃</button>
	<button type="button" onclick="window.location='/member/myList?writer=${sessionScope.sid}&board_type=4'">내 글 목록</button>
	<button type="button" onclick="window.location='/member/myComments?writer=${sessionScope.sid}'">내 댓글 목록</button>
</c:if>