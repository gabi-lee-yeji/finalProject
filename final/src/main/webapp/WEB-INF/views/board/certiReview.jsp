<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<c:if test="${fn:length(boardList)>0}" >
<h2>${info.cname} 관련 꿀팁/후기</h2>
<table border=1>
		<tr>
			<th>글번호</th>
			<th>제 목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
	<c:forEach var="board" items="${boardList}">
		<tr>
			<td>${board.pnum}</td>
			<td><a href="/${board.board_mapping}?pnum=${board.pnum}">${board.subject}</a></td> 
			<td>${board.writer}</td>
			<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${board.reg}"/></td>
			<td>${board.readCnt}</td>
		</tr>
	</c:forEach>
</table>
</c:if>