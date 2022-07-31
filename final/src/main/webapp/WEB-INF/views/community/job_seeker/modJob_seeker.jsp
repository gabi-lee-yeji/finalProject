<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>취준생 공간</title>
</head>
<body>
<c:import url="/navbar"/>
	<h1>취준생 공간 글 수정</h1>
	<form action="/community/job_seeker/modJob_seekerPro" method="post" onSubmit="return check()">
		<jsp:include page="/WEB-INF/views/board/modBoardForm.jsp" flush="false"/>
	</form>
<jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>
