<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
	<meta charset="UTF-8">
	<title>회원 - '${keyword}' 검색결과</title>
</head>
<body>
	<jsp:include page="../adminNavBar.jsp"/>
	<section style="margin-left:200px;margin-right:100px">
		<h2>${search } : "${keyword }" 검색결과 [total : ${count}]</h2>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>ID</th>
					<th>이름</th>
					<th>이메일</th>
					<th>전화번호</th>
					<th>회원상태</th>
					<th></th>
					<th>보유보인트</th>
					<th>가입일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="dto" items="${list }">
					<tr>
						<td><a href="/admin/member/info?memid=${dto.memid }">${dto.memid }</a></td>
						<td>${dto.mem_name }</td>
						<td>${dto.email }</td>
						<td>${dto.mobile }</td>
						<td>${dto.status_name }</td>
						<td>(<fmt:formatDate value="${dto.ref_date }" pattern="yyyy-MM-dd"/>)</td>
						<td>${dto.mem_point}</td>
						<td><fmt:formatDate value="${dto.regdate}" pattern="yyyy-MM-dd"/></td>
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
		        		<a class="page-link" href="/admin/member/list?pageNum=${startPage-10}&status=${status}">이전</a>
		        	</li>
		        </c:if>
		        
		        <c:forEach var="i" begin="${startPage}" end="${endPage}" step="1" >
		        	<li class="page-item">
		        		<a class="page-link" href="/admin/member/list?pageNum=${i}&status=${status}">${i}</a>
					</li>
				</c:forEach>
				
				<c:if test="${endPage < pageCount}">
					<li class="page-item">
		        		<a class="page-link" href="/admin/member/list?pageNum=${startPage + 10}&status=${status}">다음</a>
					</li>
				</c:if>
			</ul>
	    </c:if>
	</section>
</body>
</html>