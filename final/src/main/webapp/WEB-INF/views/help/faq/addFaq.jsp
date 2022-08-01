<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자주하는 질문</title>
</head>
<body>
	<c:import url="/navbar"/>
	<section style="margin: 20px 10% 10% 10%">	
		<h1>자주하는 질문 등록</h1>
		<form action="/help/faq/addFaqPro" name="addFaq" 
			method="post" encType="multipart/form-data" onSubmit="return check()">
			<jsp:include page="/WEB-INF/views/board/addBoardForm.jsp" flush="false"/>
			<input type="hidden" name="board_type" value="2"/>
		</form>
		<input type="button" class="btn btn-primary" style="float:right" value="글 목록" onclick="window.location='/help/faq/faqList?board_type=2' "/>
	</section>
</body>
<jsp:include page="/WEB-INF/views/footer.jsp" />
</html>
