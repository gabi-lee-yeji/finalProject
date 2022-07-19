<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전체 사원 목록</title>
</head>
<body>
	<jsp:include page="../../adminNavBar.jsp"/>
	<h1>전체 사원 목록 (총 : ${count}명)</h1>
	<input type="button" value="사원등록" onclick="window.location='/admin/emp/addEmp'"/>
	<table>
		<tr>
			<th>사원ID</th>
			<th>이름</th>
			<th>이메일</th>
			<th>직무</th>
			<th>직급</th>
			<th>입사일</th>
		</tr>
		<c:forEach var="map" items="${list}">
			<tr>
				<td><a href="/admin/emp/empInfo?empid=${map.EMPID}">${map.EMPID }</a></td>
				<td>${map.MEM_NAME}</td>
				<td>${map.EMAIL }</td>
				<td>${map.JOB}</td>
				<td>${map.STATUS}</td>
				<td><fmt:formatDate value="${map.HIREDATE}" pattern="yyyy-MM-dd"/></td>
			</tr>
		</c:forEach>
	</table>
	
	
	<c:if test="${count > 0}">
		<c:set var="pageCount" value="${count / page.pageSize + ( count % page.pageSize == 0 ? 0 : 1)}"/>
		
		<fmt:parseNumber var="result" value="${page.currentPage/10}" integerOnly="true"/>
		<c:set var="startPage" value="${result*10+1}"/>
		
		<c:set var="pageBlock" value="${10}"/>
		<c:set var="endPage" value="${startPage + pageBlock-1}"/>
		
		<c:if test="${endPage > pageCount}">
			<c:set var="endPage" value="${pageCount}" />
		</c:if>
        
        <c:if test="${startPage > 10 }">
        	<a href="/admin/emp/empList?pageNum=${startPage-10}">[이전]</a>
        </c:if>
        
        <c:forEach var="i" begin="${startPage}" end="${endPage}" step="1" >
        	<a href="/admin/emp/empList?pageNum=${i}">[${i}]</a>
		</c:forEach>
		
		<c:if test="${endPage < pageCount}">
        	<a href="/admin/emp/empList?pageNum=${startPage + 10}">[다음]</a>
		</c:if>
    </c:if>
</body>
</html>