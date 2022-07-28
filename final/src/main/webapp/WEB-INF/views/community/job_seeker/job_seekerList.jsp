<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>취준생 공간</title>
</head>

<body>
<jsp:include page="/WEB-INF/views/userNavBar.jsp"/>
	<h1>취준생 공간 목록(전체 글:${count})</h1>
	
	<c:if test="${count == 0}">
		<table border=1>
			<tr>
				<td>취준생 공간의 글이 없습니다. 알짜 채용정보와 취뽀 팁을 공유해주세요!</td>
			</tr>
		</table>
	</c:if>
	
	<table border=1>
	<c:if test="${count > 0}">
		<tr>
			<th>글번호</th>
			<th>제 목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
	</c:if>
	<c:forEach var="board" items="${boardList}">
		<tr>
			<td>${board.pnum}</td>
			<td><a href="/community/job_seeker/job_seekerContent?pnum=${board.pnum}&pageNum=${currentPage}">${board.subject}</a></td> 
			<td>${board.writer}</td>
			<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${board.reg}"/></td>
			<td>${board.readCnt}</td>
		</tr>
	</c:forEach>
	</table>
	
<form action="/community/job_seeker/searchList" method="get">
	<select name="search" >
		<option value="">==검색==</option>
		<option value="writer">작성자</option>
		<option value="subject">제목</option>
		<option value="post_content">내용</option>
	</select>
	<input type="text" name="keyword"/>
	<input type="submit" value="검색"/>
	<input type="hidden" name="board_type" value="7"/>
</form>

<c:if test="${count > 0}">
	<c:set var="pageCount" value="${count / pageSize + (count % pageSize == 0 ? 0 : 1)}"/>
	<fmt:parseNumber var="result" value="${(currentPage/10)}" integerOnly="true" />
	<c:set var="startPage" value="${result*10+1}" />
	<c:set var="pageBlock" value="${10}" />	
	<c:set var="endPage" value="${startPage + pageBlock - 1}" />
	
	<c:if test="${endPage > pageCount}">
		<c:set var="endPage" value="${pageCount}" />
	</c:if>
	
	<c:if test="${startPage > 10}" >
        <a href="/community/job_seeker/job_seekerList?board_type=7&pageNum=${startPage - 10}">[이전]</a>
    </c:if>
    
    <c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">    
        <a href="/community/job_seeker/job_seekerList?board_type=7&pageNum=${i}">[${i}]</a>
    </c:forEach>
    
    <c:if test="${endPage < pageCount}" >
       <a href="/community/job_seeker/job_seekerList?board_type=7&pageNum=${startPage + 10 }">[다음]</a>
    </c:if>
</c:if>
<c:if test="${sessionScope.sid != null}">
	<a href="/community/job_seeker/addJob_seeker">글쓰기</a>
</c:if>
<jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>