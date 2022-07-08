<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
	<title> 어학 자격증 </title>
</head>

<a href="/certificate/certiLang?=">영어</a>
<a href="/certificate/certiLang?=">일본어</a>
<a href="/certificate/certiLang?=">중국어</a>
<a href="/certificate/certiLang?=">유럽</a>
<a href="/certificate/certiLang?=">아시아</a>
<table>
	<tr>
		<th>NO</th>
		<th>자격명</th>
		<th>자격관리기관</th>
		<th>접수일</th>
		<th>시험일</th>
	</tr>
	
	<c:forEach var="board" items="${llist}">
		<tr>
			<td><c:out value="${board.cnum}"/></td>
		</tr>
	</c:forEach>
</table>