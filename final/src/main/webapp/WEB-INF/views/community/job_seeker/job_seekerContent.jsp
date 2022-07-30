<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>취준생 공간</title>
<script>
	function addMemberReport(pnum){
		window.open("/community/addMemberReportForm?pnum="+pnum, 
			"게시글 신고", "width=400, height=300, left=100, top=50"); 
	}
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/userNavBar.jsp"/>
<section style="margin: 20px 10% 10% 10%">
	<c:if test="${board.status != 0}">
		<script>
			alert("삭제된 게시글 입니다.");
			history.go(-1);
		</script>
	</c:if>
	<h1>취준생 공간 글보기</h1>
	<jsp:include page="/WEB-INF/views/board/boardContent.jsp" flush="false"/>
	<c:if test="${sessionScope.sid != null}">
		<input type="button" value="수정" 
			onclick="window.location = '/community/job_seeker/modJob_seeker?pnum=${board.pnum}&pageNum=${pageNum}' " />
		<input type="button" value="삭제" 
			onclick="window.location = '/community/job_seeker/delJob_seeker?pnum=${board.pnum}&pageNum=${pageNum}' " />
	
		<c:if test="${board.post_level == 0}">
			<input type="button" value="답글" 
				onclick="window.location = '/community/job_seeker/addJob_seeker?pnum=${board.pnum}&post_group=${board.post_group}' " />
		</c:if>
		<input type="button" value="신고" onclick="addMemberReport(${board.pnum});"/>
	</c:if>
	<input type="button" value="목록" 
		onclick="window.location = '/community/job_seeker/job_seekerList?board_type=7' "/>
	<br/><br/>
	<div>
		<jsp:include page="/WEB-INF/views/board/commForm.jsp" flush="false"/>
	</div>
	<br/><br/>
	<c:if test="${memberStatus == 1}">
		<c:import url = "/admin/board/reportDetails" />
	</c:if>
</section>
<jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>