<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h1>수정 본인 확인</h1>

<form action="modifyForm" method="post">
	아이디 : <input type="text" name="memid" value="${sessionScope.sid}" readonly/><br/>
	비밀번호 : <input type="password" name="passwd" /><br/>
	<input type="submit" value="확인"/>
</form>