<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
	<meta charset="UTF-8">
	<title>신고된 회원 목록 </title>
</head>
<body>
	<jsp:include page="../adminNavBar.jsp">
	<h1>신고된 회원 목록</h1>
	<table>
		<tr>
			<th>ID</th>
			<th>이름</th>
			<th>신고(회)</th>
			<th>Email</th>
			<th>전화번호</th>
			<th>
				<select name="status" onchange="location.href=this.value">
					<c:if test="${status == null }">
						<option value="reportList">==전체회원==</option>
					</c:if>
					<c:if test="${status != null }">
						<option>==${status_name }==</option>
					</c:if>
					<option value="reportList">전체회원</option>
					<option value="reportList?status=0">일반</option>
					<option value="reportList?status=2">활동중지</option>
					<option value="reportList?status=3">탈퇴</option>
					<option value="reportList?status=4">강제탈퇴</option>
				</select>
			</th>
			<th>회원등급</th>
			<th>보유포인트</th>
			<th>가입일</th>
		</tr>
		<c:forEach var="dto" items="${list }">
			<tr>
				<td><a href="/admin/member/reportMemInfo?memid=${dto.memid }&reportCnt=${dto.reportCnt}">${dto.memid }</a></td>
				<td>${dto.mem_name }</td>
				<td>${dto.reportCnt }</td>
				<td>${dto.email }</td>
				<td>${dto.mobile }</td>
				<td>${dto.status_name }</td>
				<td>${dto.mem_level }</td>
				<td>${dto.mem_point }</td>
				<td><fmt:formatDate pattern="yyyy-MM-dd" value="${dto.regdate }"/></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>