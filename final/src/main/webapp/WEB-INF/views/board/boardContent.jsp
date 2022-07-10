<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
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
		<td>${board.regdate}</td>
	</tr>
	<tr>
		<td>내용</td>
		<td>${board.post_content}</td>
	</tr>
	<tr>
	<td>첨부파일</td>
		<td>
			<c:if test="${boardAttach != null}">
			<c:forEach var="list" items="${boardAttach}">
				${list.fileName}<br/>
			</c:forEach>
			</c:if>
		</td>
	</tr>
</table>

</html>