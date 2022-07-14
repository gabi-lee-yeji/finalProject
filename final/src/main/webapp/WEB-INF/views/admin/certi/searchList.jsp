<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
	<meta charset="UTF-8">
	<title> 검색결과 : ${keyword} </title>
</head>
<body>
	<jsp:include page="../adminNavBar.jsp"/>
	<h1>
		<c:if test="${search eq 'cnum' }">
			자격증번호 
		</c:if>
		<c:if test="${search eq 'cname' }">
			종목명 
		</c:if>
		<c:if test="${search eq 'company' }">
			시행기관 
		</c:if>
		'${keyword}' 검색 결과 [총: ${count}개] 
	</h1>
	<input type="button" value="목록으로 돌아가기" onclick="window.location='/admin/certiList'"/>
	<table> 
		<tr>
			<th>번호</th>
			<th>종목명</th>
			<th>자격증종류</th>
			<th>등급</th>
			<th>시행기관</th>
			<th></th>
			<th></th>
		</tr>
		<c:forEach var="dto" items="${list}">
			<tr>
				<td>${dto.cnum }</td>
				<td>${dto.cname }</td>
				<td>${dto.category }</td>
				<td>${dto.clevel }</td>
				<td><a href="${dto.website }">${dto.company }</a></td>
				<td>
					<input type="button" value="수정" onclick="window.location='/admin/certiInfo?cnum=${dto.cnum }'">
				</td>
				<td>
					<input type="button" value="상세일정" onclick="window.location='/admin/certiDate?cnum=${dto.cnum }&cname=${dto.cname }'">
				</td>
			</tr>
		</c:forEach>
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
        
        <center>
        <c:if test="${startPage > 10 }">
        	<a href="/admin/search?pageNum=${startPage-10}&search=${search}&keyword=${keyword}">[이전]</a>
        </c:if>
        
        <c:forEach var="i" begin="${startPage}" end="${endPage}" step="1" >
        	<a href="/admin/search?pageNum=${i}&search=${search}&keyword=${keyword}">[${i}]</a>
		</c:forEach>
		
		<c:if test="${endPage < pageCount}">
        	<a href="/admin/search?pageNum=${startPage + 10}&search=${search}&keyword=${keyword}">[다음]</a>
		</c:if>
		</center>
    </c:if>
</body>
