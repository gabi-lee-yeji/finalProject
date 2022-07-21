<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자격증 정보</title>
</head>
<body>
	<h1>자격증 정보 수정</h1>
	<form action="/community/info/modInfoPro" method="post">
		<jsp:include page="/WEB-INF/views/board/modBoardForm.jsp" flush="false"/>
		<input type="submit" value="수정 완료" />
	</form>
</body>
</html>
