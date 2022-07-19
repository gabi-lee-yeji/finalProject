<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<table>
	<tr><th colspan=3>신규 1:1 문의 (new +${count})</th></tr>
	<tr>
		<th>제목</th>
		<th>작성자</th>
		<th>등록일</th>
	</tr>
	<c:if test="${count > 0}">
		<c:forEach var="dto" items="${reqList}">
			<tr>
				<td><a href="/help/qna/addQna?pnum=${dto.pnum }">${dto.subject }</a></td>
				<td>${dto.writer }</td>
				<td><fmt:formatDate pattern="yy-MM-dd" value="${dto.reg }"/></td>
			</tr>
		</c:forEach>
	</c:if>
	<c:if test="${count == 0 or count < 0}">
		<tr>
			<td colspan=3>신규문의가 없습니다.</td>
		</tr>
	</c:if>
</table>