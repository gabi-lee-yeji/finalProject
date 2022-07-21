<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>질문글</title>
</head>
<body>
	<h1>질문글 수정</h1>
	<form action="/community/question/modQuestionPro" method="post">
		<jsp:include page="/WEB-INF/views/board/modBoardForm.jsp" flush="false"/>
		<input type="submit" value="수정 완료" />
	</form>
</body>
</html>
