<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
	<meta charset="UTF-8">
	<title>게시글 검색 결과</title>
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
			<div style="margin-left:50px; margin-top:5%">
				<h1> 검색결과 [총 : ${count}개]</h1>
				<table>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>게시판</th>
						<th>작성일</th>
						<th>조회수</th>
						<th>상태</th>
					</tr>
					<c:forEach var="dto" items="${list }">
						<tr>
							<td>${dto.pnum }</td>
							<td>
								<a href="/${dto.board_mapping }?pnum=${dto.pnum}">${dto.subject}</a>
							</td>
							<td>${dto.writer }</td>
							<td>${dto.board_type }</td>
							<td>
								<fmt:formatDate pattern="yy/MM/dd" value="${dto.reg }"/>
							</td>
							<td>${dto.readCnt }</td>
							<td>
								<c:if test="${dto.status eq 1 }">
									삭제됨
								</c:if>
								<c:if test="${dto.status eq 0 }">
									일반
								</c:if>
							</td>
						</tr>
					</c:forEach>
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
	        
	        <c:if test="${startPage > 10 }">
	        	<a href="/admin/board/search?pageNum=${startPage-10}&board_type=${board_type}&search=${search }&keyword=${keyword }">[이전]</a>
	        </c:if>
	        
	        <c:forEach var="i" begin="${startPage}" end="${endPage}" step="1" >
	        	<a href="/admin/board/search?pageNum=${i}&board_type=${board_type}&search=${search }&keyword=${keyword }">[${i}]</a>
			</c:forEach>
			
			<c:if test="${endPage < pageCount}">
	        	<a href="/admin/board/search?pageNum=${startPage + 10}&board_type=${board_type}&search=${search }&keyword=${keyword }">[다음]</a>
			</c:if>
	    </c:if>
	</div>
</body>
