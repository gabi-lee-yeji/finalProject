<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자격증 정보</title>
</head>
<body>
	<h1>자격증 정보 삭제</h1>
<form action="/community/info/delInfoPro" method="post">
	<jsp:include page="/WEB-INF/views/board/delBoardForm.jsp" flush="false"/>
</form>
</body>
</html>