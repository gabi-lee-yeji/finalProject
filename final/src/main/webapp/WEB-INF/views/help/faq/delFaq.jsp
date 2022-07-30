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
	<section style="margin: 20px 10% 10% 30%">
		<h1>자주하는 질문 삭제</h1>
		<form action="/help/faq/delFaqPro" method="post">
			<jsp:include page="/WEB-INF/views/board/delBoardForm.jsp" flush="false"/>
		</form>
	</section>
	<jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>