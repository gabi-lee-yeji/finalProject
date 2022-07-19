<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<table>
	<tr>
		<td>제목</td>
		<td><input type="text" name="subject" value="${board.subject}" /></td>
	</tr>
	<tr>
		<td>작성자</td>
		<td>${board.writer}</td>
	</tr>
	<tr>
		<td>내용</td>
		<td><textarea name="post_content" rows="13" cols="40">${board.post_content}</textarea></td>
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
	<tr>
		<td>비밀번호</td>
		<td><input type="password" name="passwd" /></td>
	</tr>
</table>
<input type="hidden" name="pnum" value="${board.pnum}" />
<input type="hidden" name="memid" value="${sessionScope.sid}" />
<input type="hidden" name="pageNum" value="${pageNum}" />
</html>