<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자주하는 질문</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/userNavBar.jsp"/>
	<h1>자주하는 질문 수정</h1>
	<form action="/help/faq/modFaqPro" method="post" onSubmit="return check()">
		<jsp:include page="/WEB-INF/views/board/modBoardForm.jsp" flush="false"/>
	</form>
<jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>
