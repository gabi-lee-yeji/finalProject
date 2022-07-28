<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/views/mypage/sidebar.jsp"></jsp:include>
<div style="margin-left:200px">
<c:if test="${count == 0}">
	<h1>내 댓글 모음 (전체 댓글:${count})</h1>
		<table border=1>
			<tr>
				<td>작성한 댓글이 없습니다.</td>
			</tr>
		</table>
	</c:if>

	<table border=1>
	
	<c:if test="${count > 0}">
	전체글: ${count}
		<tr>
			<th>댓글번호</th>
			<th>댓글내용</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>게시글번호</th>
		</tr>
	</c:if>
	<c:forEach var="comm" items="${commList}">
		<tr>
			<td>${comm.comm_num}</td>
			<td><a href="/${comm.board_mapping}?pnum=${comm.pnum}">${comm.comm_content}</a></td>
			<td>${comm.writer}</td>
			<td>${comm.reg}</td>
			<td>${comm.pnum}</td>
		</tr>
	</c:forEach>
	</table>
<c:if test="${count > 0}">
	<c:set var="pageCount" value="${count / pageSize + (count % pageSize == 0 ? 0 : 1)}"/>
	<fmt:parseNumber var="result" value="${(currentPage/10)}" integerOnly="true" />
	<c:set var="startPage" value="${result*10+1}" />
	<c:set var="pageBlock" value="${10}" />	
	<c:set var="endPage" value="${startPage + pageBlock - 1}" />
	
	<c:if test="${endPage > pageCount}">
		<c:set var="endPage" value="${pageCount}" />
	</c:if>
	
	<c:if test="${startPage > 10}">
        <a href="/member/myComments?pageNum=${startPage - 10}&writer=${sessionScope.sid}">[이전]</a>
    </c:if>
    <c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">    
        <a href="/member/myComments?pageNum=${i}&writer=${sessionScope.sid}">[${i}]</a>
    </c:forEach>
    
    <c:if test="${endPage < pageCount}" >
        <a href="/member/myComments?pageNum=${startPage + 10}&writer=${sessionScope.sid}">[다음]</a>
    </c:if>
    <br/>
</c:if>
</div>
</body>