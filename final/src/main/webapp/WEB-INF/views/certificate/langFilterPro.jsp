<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>필터링 결과 (${count}개)</title>
	<style>
		.pagination {
		  display: inline-block;
		}
		
		.pagination a {
		  color: black;
		  float: left;
		  padding: 8px 16px;
		  text-decoration: none;
		  transition: background-color .3s;
		  border: 1px solid #ddd;
		}
		
		.pagination a.active {
		  background-color: #4CAF50;
		  color: white;
		  border: 1px solid #4CAF50;
		}
		
		.pagination a:hover:not(.active) {background-color: #ddd;}
	</style>
</head>
<body>
	<jsp:include page="../userNavBar.jsp"/>
	<div class="row" >
		<div class="col-2" style="margin-left:20px">
			<c:import url="/certificate/langFilterForm"/>
		</div>
		<div class="col-8" >
			<h1>검색 결과 (총 ${count}개)</h1>
			<table class="table">
				<thead class="thead-dark">
					<tr><th style="text-align: center;font-size: 20px">검색된 항목</th></tr>
				</thead>
				<tbody>
					<tr><td>${ncsName}</td></tr>
				</tbody>
			</table>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>NO</th>
						<th>시험명</th>
						<th>자격등급</th>
						<th>분류</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="dto" items="${list}">
						<tr>
							<td>${dto.cnum}</td>
							<td>
								<a href="/certificate/certiContent?cnum=${dto.cnum}">${dto.cname}</a>
								<c:if test="${fn:contains(cnumList, dto.cnum)}">
									<button type="button" class="btn btn-danger btn-xs">접수마감!</button>
								</c:if>
							</td>
							<td>${dto.clevel}</td>
							<td>${dto.category}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
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
       	<div class="pagination">
	        <c:if test="${startPage > 10 }">
	        	<a href="/certificate/langFilterPro?pageNum=${startPage-10}&ncs_cat=${ncs_cat}">
	        		이전
	        	</a>
	        </c:if>
	        <c:forEach var="i" begin="${startPage}" end="${endPage}" step="1" >
	        	<a href="/certificate/langFilterPro?pageNum=${i}&&ncs_cat=${ncs_cat}" >
	        		${i}
	        	</a>
			</c:forEach>
			<c:if test="${endPage < pageCount}">
	        	<a href="/certificate/langFilterPro?pageNum=${startPage + 10}&&ncs_cat=${ncs_cat}">
	        		다음
	        	</a>
			</c:if>
		</div>
   	</c:if>