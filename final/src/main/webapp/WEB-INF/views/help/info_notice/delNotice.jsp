<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/userNavBar.jsp"/>
	<h1>공지사항 삭제</h1>
<form action="/community/notice/delNoticePro" method="post">
	<jsp:include page="/WEB-INF/views/board/delBoardForm.jsp" flush="false"/>
</form>
</body>
</html>