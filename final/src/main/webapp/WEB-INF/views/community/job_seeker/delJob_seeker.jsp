<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>취준생 공간</title>
</head>
<body>
	<h1>취준생 공간 글 삭제</h1>
<form action="/community/job_seeker/delJob_seekerPro" method="post">
	<jsp:include page="/WEB-INF/views/board/delBoardForm.jsp" flush="false"/>
</form>
</body>
</html>