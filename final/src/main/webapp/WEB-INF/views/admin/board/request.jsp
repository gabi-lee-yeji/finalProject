<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
	<meta charset="UTF-8">
	<title>새로 들어온 문의글 목록</title>
</head>
<body>
	<jsp:include page="sidebar.jsp"></jsp:include>
	<div class="w3-main" style="margin-left:200px">
		<div class="bg-dark">
		  <button class="w3-button bg-dark w3-xlarge w3-hide-large" onclick="w3_open()">&#9776;</button>
		  <div class="w3-container">
		    <jsp:include page="../adminNavBar.jsp"></jsp:include>
		  </div>
		</div>
		<div style="margin-left:50px;margin-right:50; margin-top:5%">
			<h1>답변 안달린 1:1 문의 목록 [총 : ${count }]</h1>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>글번호</th>
						<th>글제목</th>
						<th>작성자</th>
						<th>조회수</th>
						<th>작성시간</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="dto" items="${list }">
						<tr>
							<td>${dto.pnum }</td>
							<td><a href="/help/qna/addQna?pnum=${dto.pnum }">${dto.subject }</a></td>
							<td>${dto.writer }</td>
							<td>${dto.readCnt }</td>
							<td><fmt:formatDate pattern="yy-MM-dd" value="${dto.reg }"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	
		<c:if test="${count > 0}">
			<c:set var="pageCount" value="${count / page.pageSize + ( count % page.pageSize == 0 ? 0 : 1)}"/>
			
			<!-- int만 나올 수 있도록 fmt 통해서 포맷팅해줌 -->
			<fmt:parseNumber var="result" value="${page.currentPage/10}" integerOnly="true"/>
			<c:set var="startPage" value="${result*10+1}"/>
			
			<c:set var="pageBlock" value="${10}"/>
			<c:set var="endPage" value="${startPage + pageBlock-1}"/>
			
			<c:if test="${endPage > pageCount}">
				<c:set var="endPage" value="${pageCount}" />
			</c:if>
	        
	        <ul class="pagination justify-content-center">
		        <c:if test="${startPage > 10 }">
		        	<li class="page-item">
		        		<a class="page-link" href="/admin/board/request?pageNum=${startPage-10}">이전</a>
		        	</li>
		        </c:if>
		        
		        <c:forEach var="i" begin="${startPage}" end="${endPage}" step="1" >
		        	<li class="page-item">
		        		<a class="page-link" href="/admin/board/request?pageNum=${i}">${i}</a>
		        	</li>
				</c:forEach>
				
				<c:if test="${endPage < pageCount}">
					<li class="page-item">
		        		<a class="page-link" href="/admin/board/request?pageNum=${startPage + 10}">다음</a>
		        	</li>
				</c:if>
			</ul>
	    </c:if>
	</div>
</body>
