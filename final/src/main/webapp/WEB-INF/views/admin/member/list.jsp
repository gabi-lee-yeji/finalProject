<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
	<meta charset="UTF-8">
	<title>전체회원 목록</title>
</head>
	<h1>회원목록 [총:${count}명]</h1>
	<table>
		<tr>
			<th>ID</th>	
			<th>이름</th>	
			<th>이메일</th>	
			<th>우편번호</th>
			<th>주소</th>	
			<th>핸드폰번호</th>	
			<th>회원상태</th>	
			<th>회원등급</th>	
			<th>포인트</th>	
			<th>가입일</th>		
		</tr>
		<c:forEach var="dto" items="${list }">
			<tr>
				<td>${dto.memid }</td>
				<td>${dto.mem_name }</td>
				<td>${dto.email }</td>
				<td>${dto.postalCode }</td>
				<td>${dto.address }</td>
				<td>${dto.mobile }</td>
				<td>${dto.status }</td>
				<td>${dto.mem_level }</td>
				<td>${dto.mem_point }</td>
				<td>
					<fmt:formatDate pattern="yyyy-MM-dd" value="${dto.regdate }"/>
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
        	<a href="/admin/member/list?pageNum=${startPage-10}">[이전]</a>
        </c:if>
        
        <c:forEach var="i" begin="${startPage}" end="${endPage}" step="1" >
        	<a href="/admin/member/list?pageNum=${i}">[${i}]</a>
		</c:forEach>
		
		<c:if test="${endPage < pageCount}">
        	<a href="/admin/member/list?pageNum=${startPage + 10}">[다음]</a>
		</c:if>
		</center>
    </c:if>