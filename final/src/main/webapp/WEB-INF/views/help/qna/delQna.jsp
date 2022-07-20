<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
<meta charset="UTF-8">
<title>1:1 문의</title>
</head>

<body>
<jsp:include page="/WEB-INF/views/userNavBar.jsp"/>
<h1>1:1 문의 삭제</h1>
<form action="/help/qna/delQnaPro" method="get">
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

