<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>직원 검색 결과 : ${keyword }</title>
</head>
<body>
	<jsp:include page="../../adminNavBar.jsp"/>
	
	<c:if test="${checkIfMgr != 1}">
		<c:set var="count" value="${totalCnt - quitCnt }"/>
	</c:if>
	<c:if test="${checkIfMgr == 1}">
		<c:set var="count" value="${totalCnt}"/>
	</c:if>
	
	<h1>직원 검색 결과 : '${keyword }' (총 : ${count}명)</h1>
	<table>
		<tr>
			<th>사원ID</th>
			<th>이름</th>
			<th>이메일</th>
			<th>직무</th>
			<th>직급</th>
			<th>입사일</th>
		</tr>
		<c:forEach var="dto" items="${list }">
			<c:if test="${checkIfMgr != 1}">
				<c:if test="${dto.status != '퇴사' }">
					<tr>
						<td><a href="/admin/emp/empInfo?empid=${dto.empid}">${dto.empid}</a></td>
						<td>${dto.memberInfo.mem_name}</td>
						<td>${dto.memberInfo.email }</td>
						<td>${dto.job}</td>
						<td>${dto.status}</td>
						<td><fmt:formatDate value="${dto.hiredate}" pattern="yyyy-MM-dd"/></td>
					</tr>
				</c:if>
			</c:if>
			<c:if test="${checkIfMgr == 1}">
				<tr>
					<td><a href="/admin/emp/empInfo?empid=${dto.empid}">${dto.empid}</a></td>
					<td>${dto.memberInfo.mem_name}</td>
					<td>${dto.memberInfo.email }</td>
					<td>${dto.job}</td>
					<td>${dto.status}</td>
					<td><fmt:formatDate value="${dto.hiredate}" pattern="yyyy-MM-dd"/></td>
				</tr>
			</c:if>
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
        	<a href="/admin/emp/searchList?pageNum=${startPage-10}&search=${search}&keyword=${keyword}">[이전]</a>
        </c:if>
        
        <c:forEach var="i" begin="${startPage}" end="${endPage}" step="1" >
        	<a href="/admin/emp/searchList?pageNum=${i}&search=${search}&keyword=${keyword}">[${i}]</a>
		</c:forEach>
		
		<c:if test="${endPage < pageCount}">
        	<a href="/admin/emp/searchList?pageNum=${startPage + 10}&search=${search}&keyword=${keyword}">[다음]</a>
		</c:if>
    </c:if>
</body>
</html>