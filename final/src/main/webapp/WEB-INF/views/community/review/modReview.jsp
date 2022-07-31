<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>꿀팁, 리뷰</title>
  </head>
<body>
<c:import url="/navbar"/>
<h1>꿀팁, 리뷰 수정</h1>
	<form action="/community/review/modReviewPro" method="post" onSubmit="return check()">
		<jsp:include page="/WEB-INF/views/board/modBoardForm.jsp" flush="false" />
	</form>
<jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>
