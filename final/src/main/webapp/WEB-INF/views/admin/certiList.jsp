<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
	<title>등록된 자격증 목록</title>
</head>

	<h1>자격증 목록 [총 : ${count }]</h1>
	<input type="button" value="자격증 등록" onclick="window.location='/admin/addCerti'"/>
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
	
	
