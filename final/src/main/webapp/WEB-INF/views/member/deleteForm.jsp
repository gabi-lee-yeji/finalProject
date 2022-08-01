<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>deleteForm</title>

<c:if test="${sessionScope.sid == null}">
	<c:redirect url="/member/loginForm"/>
</c:if>

<c:import url="/navbar"/>
<c:import url="/mypage/sidebar"/>
<div style="margin-left:230px">
	<h1>회원 탈퇴</h1>
	<c:if test="${sessionScope.sid != null}">
		<form action="/member/deletePro" method="post">
			<input type="text" name="memid" value="${sessionScope.sid}" readonly/><br/>
			<input type="password" name="passwd" required/><br/>
			<input type="submit" value="탈퇴" />
			<button type="button" onclick="window.location='/mypage/'">돌아가기</button>
		</form>
	</c:if>
</div>
<jsp:include page="../footer.jsp"></jsp:include>