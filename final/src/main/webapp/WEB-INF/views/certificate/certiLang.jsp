<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
	<title> 어학 자격증 </title>
</head>
	<jsp:include page="../userNavBar.jsp"/>
	<div>
		<c:import url="/certificate/langFilterForm"/>
	</div>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>NO</th>
				<th>시험명</th>
				<th>자격등급</th>
				<th>시행기관</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="dto" items="${list}">
				<tr>
					<td>${dto.cnum}</td>
					<td>
						<a href="/certificate/certiContent?cnum=${dto.cnum}">${dto.cname}</a>
					</td>
					<td>${dto.clevel}</td>
					<td><a href="${dto.website}">${dto.company}</a></td>
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
       	<div class="pagination">
	        <c:if test="${startPage > 10 }">
	        	<a href="/certificate/langFilterPro?pageNum=${startPage-10}&ncs_cat=${ncs_cat}">
	        		이전
	        	</a>
	        </c:if>
	        <c:forEach var="i" begin="${startPage}" end="${endPage}" step="1" >
	        	<a href="/certificate/langFilterPro?pageNum=${i}&ncs_cat=${ncs_cat}" >
	        		${i}
	        	</a>
			</c:forEach>
			<c:if test="${endPage < pageCount}">
	        	<a href="/certificate/langFilterPro?pageNum=${startPage + 10}&ncs_cat=${ncs_cat}">
	        		다음
	        	</a>
			</c:if>
		</div>
   	</c:if>
