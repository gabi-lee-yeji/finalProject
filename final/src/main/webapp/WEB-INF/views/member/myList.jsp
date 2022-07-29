<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
<meta charset="UTF-8">
</head>
<body>
<jsp:include page="../userNavBar.jsp"></jsp:include>
<jsp:include page="../mypage/sidebar.jsp"></jsp:include>
<div style="margin-left:230px">
	<c:if test="${board_type == 0}">
		<h1>내가 쓴 모든 게시글</h1>
		<c:if test="${count == 0}">
			<table border=1>
					<tr>
						<td>내가 작성한 글이 없습니다.</td>
					</tr>
				</table>
		</c:if>
	</c:if>
	<c:if test="${board_type == 3}">
	<c:if test="${count == 0}">
		<h1>1:1 문의글 (전체 글:${count})</h1>
			<table border=1>
				<tr>
					<td>문의글이 없습니다.</td>
				</tr>
			</table>
		</c:if>
	</c:if>
	<c:if test="${board_type == 4}">
	<c:if test="${count == 0}">
		<h1>내 후기 글 (전체 글:${count})</h1>
			<table border=1>
				<tr>
					<td>후기글이 없습니다.</td>
				</tr>
			</table>
		</c:if>
	</c:if>
	<c:if test="${board_type == 5}">
	<c:if test="${count == 0}">
		<h1>내 질문 글 (전체 글:${count})</h1>
			<table border=1>
				<tr>
					<td>후기글이 없습니다.</td>
				</tr>
			</table>
		</c:if>
	</c:if>
	<c:if test="${board_type == 6}">
	<c:if test="${count == 0}">
		<h1>내 정보글 (전체 글:${count})</h1>
			<table border=1>
				<tr>
					<td>정보글이 없습니다.</td>
				</tr>
			</table>
		</c:if>
	</c:if>
	<c:if test="${board_type == 7}">
	<c:if test="${count == 0}">
		<h1>취준생 공간 (전체 글:${count})</h1>
			<table border=1>
				<tr>
					<td>취준생공간에 올린 글이 없습니다.</td>
				</tr>
			</table>
			 
		</c:if>
	</c:if>
	
		<table border=1>
		
		<c:if test="${count > 0}">
		<c:if test="${board_type == 3}"><h1>1:1 문의</h1></c:if>
		<c:if test="${board_type == 4}"><h1>후기글</h1></c:if>
		<c:if test="${board_type == 5}"><h1>질문글</h1></c:if>
		<c:if test="${board_type == 6}"><h1>정보글</h1></c:if>
		<c:if test="${board_type == 7}"><h1>취준생공간글</h1></c:if>
		전체글: ${count}
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
				<c:if test="${board.board_type == 3}">
				<td><a href="/help/qna/qnaContent?pnum=${board.pnum}&pageNum=${currentPage}">${board.subject}</a></td>
				</c:if>
				<c:if test="${board.board_type == 4}">
				<td><a href="/community/review/reviewContent?pnum=${board.pnum}&pageNum=${currentPage}">${board.subject}</a></td>
				</c:if>
				<c:if test="${board.board_type == 5}">
				<td><a href="/community/question/questionContent?pnum=${board.pnum}&pageNum=${currentPage}">${board.subject}</a></td>
				</c:if>
				<c:if test="${board.board_type == 6}">
				<td><a href="/community/info/infoContent?pnum=${board.pnum}&pageNum=${currentPage}">${board.subject}</a></td>
				</c:if>
				<c:if test="${board.board_type == 7}">
				<td><a href="/community/job_seeker/job_seekerContent?pnum=${board.pnum}&pageNum=${currentPage}">${board.subject}</a></td>
				</c:if>
				<td>${board.writer}</td>
				<td>${board.reg}</td>
				<td>${board.readCnt}</td>
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
	        <a href="/member/myList?pageNum=${startPage - 10}&board_type=${board_type}&writer=${sessionScope.sid}">[이전]</a>
	    </c:if>
	    <c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">    
	        <a href="/member/myList?pageNum=${i}&board_type=${board_type}&writer=${sessionScope.sid}">[${i}]</a>
	    </c:forEach>
	    
	    <c:if test="${endPage < pageCount}" >
	        <a href="/member/myList?pageNum=${startPage + 10}&board_type=${board_type}&writer=${sessionScope.sid}">[다음]</a>
	    </c:if>
	    <br/>
	</c:if>
</div>
<jsp:include page="../footer.jsp"></jsp:include>
</body>
