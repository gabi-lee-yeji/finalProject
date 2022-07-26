<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<table border=1>
	<tr>
		<td>제목</td>
		<td>${board.subject}</td>
	</tr>
	<tr>
		<td>작성자</td>
		<td>${board.writer}</td>
	</tr>
	<tr>
		<td>작성일</td>
		<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${board.reg}"/></td>
	</tr>
	<tr>
		<td>내용</td>
		<td>${board.post_content}</td>
	</tr>
	<c:if test="${boardAttach != null}">
	<tr>
	<td>첨부파일</td>
		<td>
			<c:forEach var="list" items="${boardAttach}">
				<img width="100" height="100" src="/resources/image/upload/${list.uuid}_${list.fileName}">
				${list.fileName}
				<br/>
			</c:forEach>
		</td>
	</tr>
	</c:if>
</table>
