<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1:1 문의</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/userNavBar.jsp"/>
	<section style="margin: 20px 10% 10% 30%">
		<h1>1:1 문의 글 삭제</h1>
		<form action="/help/qna/delQnaPro" method="post">
			<jsp:include page="/WEB-INF/views/board/delBoardForm.jsp" flush="false"/>
		</form>
	</section>
	<jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>