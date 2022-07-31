<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>질문글</title>
</head>
<body>
<c:import url="/navbar"/>
	<h1>질문글 삭제</h1>
<form action="/community/question/delQuestionPro" method="post">
	<jsp:include page="/WEB-INF/views/board/delBoardForm.jsp" flush="false"/>
</form>
<jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>