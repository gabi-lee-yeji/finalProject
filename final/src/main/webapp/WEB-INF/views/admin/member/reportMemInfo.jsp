<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<meta charset="UTF-8">
	<title>신고정보 - ${memid }</title>
</head>
<body>
	<h1>회원정보</h1>
	<form action="/admin/member/memReportPro" method="post">
		<table>
			<tr>
				<td>ID</td>
				<td>${memid }</td>
			</tr>
			<tr>
				<td>신고당한 횟수</td>
				<td>${reportCnt }</td>
			</tr>
			<tr>
				<td>회원상태</td>
				<td>
					<select name="status">
						<option value="${dto.status }">==${dto.status }==</option>
						<option value="일반">일반</option>
						<option value="휴면">휴면</option>
						<option value="탈퇴">탈퇴</option>
						<option value="활동중지">활동중지</option>
						<option value="강제탈퇴">강제탈퇴</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>회원등급</td>
				<td>${dto.mem_level }</td>
			</tr>
			<tr>
				<td>보유포인트</td>
				<td>${dto.mem_point }</td>
			</tr>
		</table>
		<input type="hidden" name="memid" value="${memid }">
		<input type="submit" value="회원 상태 수정">
	</form>
	<h1>신고당한 글 목록</h1>
	<table>
		<tr>
			<th>글번호</th>
			<th>게시판</th>
			<th>글제목</th>
			<th>신고(회)</th>  <!-- 누르면 신고한 회원 목록 볼수있게? -->
		</tr>
		<c:forEach var="map" items="${list }" >
			<tr>
				<td>${map.PNUM }</td>
				<td>${map.BOARD_TYPE }</td>
				<td>
					<a href> 
						<if test="${map.SUBJECT == null }">(제목없음)</if>
						<if test="${map.SUBJECT != null }">${map.SUBJECT }</if>
					</a>
				</td>
				<td>${map.REPORTCNT }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>