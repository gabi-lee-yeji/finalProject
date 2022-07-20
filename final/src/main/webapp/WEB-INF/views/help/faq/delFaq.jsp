<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FAQ</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/userNavBar.jsp"/>
<h1>자주하는 질문 삭제</h1>
<form action="/help/faq/delFaqPro" method="get">
	<input type="hidden" name="pnum" value="${pnum}" />
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="hidden" name="memid" value="${sessionScope.sid}" />
	<table>
		<tr>
			<td>비밀번호를 입력해주세요</td>
			<td><input type="text" name="passwd" /></td>
		</tr>
		<tr>
			<td><input type="submit" value="전송" /></td>
		</tr>
	</table>
</form>
</body>
</html>