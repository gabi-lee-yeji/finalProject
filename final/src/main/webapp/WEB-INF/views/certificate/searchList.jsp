<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<html>
	<head>
		<meta charset="UTF-8">
		<title>자격증 검색 결과 : ${keyword}</title>
	</head>
	<body>
		<jsp:include page="../userNavBar.jsp"/>
		<section style="margin-left:5%;margin-right:5%;margin-bottom:5%">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>번호</th>
						<th>종목명</th>
						<th>자격증종류</th>
						<th>등급</th>
						<th>시행기관</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="dto" items="${list }">
						<tr>
							<td>${dto.cnum }</td>
							<td><a href="/certificate/certiContent?cnum=${dto.cnum }"> ${dto.cname } </a></td>
							<td>${dto.category }</td>
							<td>${dto.clevel }</td>
							<td><a href="${dto.website }">${dto.company }</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
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
			        		<a class="page-link" href="/searchCerti?pageNum=${startPage-10}&keyword=${keyword}">이전</a>
			        	</li>
			        </c:if>
			        
			        <c:forEach var="i" begin="${startPage}" end="${endPage}" step="1" >
			        	<li class="page-item">
			        		<a class="page-link" href="/searchCerti?pageNum=${i}&keyword=${keyword}">${i}</a>
			        	</li>
					</c:forEach>
					
					<c:if test="${endPage < pageCount}">
						<li class="page-item">
			        		<a class="page-link" href="/searchCerti?pageNum=${startPage + 10}&keyword=${keyword}">다음</a>
			        	</li>
					</c:if>
				</ul>
	    	</c:if>
		</section>
		<jsp:include page="/WEB-INF/views/footer.jsp" />
	</body>
</html>