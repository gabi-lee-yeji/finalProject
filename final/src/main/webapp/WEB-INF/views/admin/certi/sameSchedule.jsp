<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
	<title>함께 변경되는 자격증 목록</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  	<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
  	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>


	<table>
		<tr>
			<th>자격증번호</th>
			<th>자격증명</th>
			<th>등급</th>
		</tr>
		<c:forEach var="dto" items="${natList}">
			<tr>
				<td>${dto.cnum }</td>
				<td>${dto.cname }</td>
				<td>${dto.clevel }</td>
			</tr>
		</c:forEach>
	</table>
	<c:if test="${count > 0 }">
		<c:set var="pageCount" value="${count / page.pageSize + ( count % page.pageSize == 0 ? 0 : 1)}"/>
		<fmt:parseNumber var="result" value="${page.currentPage/10}" integerOnly="true"/>
		<c:set var="startPage" value="${result*10+1}"/>
		
		<c:set var="pageBlock" value="${5}"/>
		<c:set var="endPage" value="${startPage + pageBlock-1}"/>
		
		<c:if test="${endPage > pageCount}">
			<c:set var="endPage" value="${pageCount}" />
		</c:if>
		
		<ul class="pagination pagination-sm justify-content-center">
			<c:if test="${startPage > 10 }">
			    <li class="page-item">
			    	<a class="page-link" href="/admin/certi/sameSchedule?pageNum=${startPage-10}&datepk=${datepk}">&#706;</a>
			    </li>
		    </c:if>
		    <c:forEach var="i" begin="${startPage}" end="${endPage}" step="1" >
			    <li class="page-item">
			    	<a class="page-link" href="/admin/certi/sameSchedule?pageNum=${i}&datepk=${datepk}">${i}</a>
			    </li>
		    </c:forEach>
		    <c:if test="${endPage < pageCount}">
			    <li class="page-item">
			    	<a class="page-link" href="/admin/certi/sameSchedule?pageNum=${startPage + 10}&datepk=${datepk}">&#707;</a>
			    </li>
		    </c:if>
		</ul>
	</c:if>
