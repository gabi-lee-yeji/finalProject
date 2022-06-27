<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
	<title>등록된 자격증 목록</title>
</head>

	<h1>자격증 목록 [총 : ${count }]</h1>
	<input type="button" value="자격증 등록" onclick="window.location='/admin/addCerti'"/>
	<form action="/admin/search" method="post">
		<select	name="search">
			<option value="cname">종목명</option>
		</select>
		<input type="text" name="keyword">
		<input type="submit" value="검색">
	</form>
	<table>
		<tr>
			<th>번호</th>
			<th>종목명</th>
			<th>대분류</th>
			<th>소분류</th>
			<th>회차</th>
			<th>원서접수 시작일</th>
			<th>원서접수 마감일</th>
			<th>추가접수 시작일</th>
			<th>추가접수 마감일</th>
			<th>시험 응시일</th>
			<th>결과 발표일</th>
			<th></th>
		</tr>
		<c:forEach var="dto" items="${list}">
			<tr>
				<td>${dto.cnum }</td>
				<td>${dto.cname }</td>
				<td>${dto.category }</td>
				<td>${dto.ctype }</td>
				<td>${dto.cround }</td>
				<td>${dto.regStart } ${dto.regStartTime}</td>
				<td>${dto.regEnd } ${dto.regEndTime}</td>
				<td>${dto.reg_addStart }</td>
				<td>${dto.reg_addEnd }</td>
				<td>${dto.testDate }</td>
				<td>${dto.resDate }</td>
				<td>
					<input type="button" value="수정" onclick="window.location='/admin/modCerti?cnum=${dto.cnum }'">
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
        	<a href="/admin/certiList?pageNum=${startPage-10}">[이전]</a>
        </c:if>
        
        <c:forEach var="i" begin="${startPage}" end="${endPage}" step="1" >
        	<a href="/admin/certiList?pageNum=${i}">[${i}]</a>
		</c:forEach>
		
		<c:if test="${endPage < pageCount}">
        	<a href="/admin/certiList?pageNum=${startPage + 10}">[다음]</a>
		</c:if>
		</center>
    </c:if>
	
	
