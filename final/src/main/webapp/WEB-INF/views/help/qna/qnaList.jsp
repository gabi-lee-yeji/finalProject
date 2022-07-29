<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>1:1 문의</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/userNavBar.jsp"/>
	<section style="margin-left:5%;margin-right:5%;margin-bottom:5%">
		<h1>1:1 문의 목록(전체 글:${count})</h1>
		
		<c:if test="${count == 0}">
			<table class="table table-hover">
				<tr>
					<td><h3>1:1 문의 글이 없습니다.</h3></td>
				</tr>
			</table>
		</c:if>
		<table class="table table-hover">
			<c:if test="${count > 0}">
				<thead>
					<tr>
						<th>글번호</th>
						<th>제 목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>조회수</th>
					</tr>
				</thead>
			</c:if>
			<tbody>
				<c:forEach var="board" items="${boardList}">
					<tr>
						<td>${board.pnum}</td>
						<td><a href="/help/qna/qnaContent?pnum=${board.pnum}&pageNum=${currentPage}">${board.subject}</a></td> 
						<td>${board.writer}</td>
						<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${board.reg}"/></td>
						<td>${board.readCnt}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<form action="/help/qna/searchList" method="get">
			<div class="row">
				<select name="search" class="form-control" style="max-width:8%">
					<option value="">==검색==</option>
					<option value="writer">작성자</option>
					<option value="subject">제목</option>
					<option value="post_content">내용</option>
				</select>
				<input type="text" name="keyword" class="form-control" style="max-width:10%"/>
				<input type="submit" value="검색" class="btn btn-primary"/>
			</div>
			<input type="hidden" name="board_type" value="3"/>
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
			<ul class="pagination justify-content-center">
				<c:if test="${startPage > 10}" >
					<li class="page-item">
			        	<a class="page-link" href="/help/qna/qnaList?board_type=3&pageNum=${startPage - 10}">이전</a>
			    	</li>
			    </c:if>
			    
			    <c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">    
			        <li class="page-item">
			        	<a class="page-link" href="/help/qna/qnaList?board_type=3&pageNum=${i}">${i}</a>
			    	</li>
			    </c:forEach>
			    
			    <c:if test="${endPage < pageCount}" >
			    	<li class="page-item">
			       		<a class="page-link" href="/help/qna/qnaList?board_type=3&pageNum=${startPage + 10 }">다음</a>
			    	</li>
			    </c:if>
			 </ul>
		</c:if>
		<c:if test="${sessionScope.sid != null}">
			<a href="/help/qna/addQna">글쓰기</a>
		</c:if>
	</section>
	<jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
</html>