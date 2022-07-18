<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

	<table>
		<tr>
			<td>제목</td>
			<c:if test="${pnum == 0}" >
				<td><input type="text" name="subject" /></td>
			</c:if>
			<c:if test="${pnum != 0}" >
				<td>[re] ${board.subject}</td>
				<input type="hidden" name="subject" value="[re] ${board.subject}" />
			</c:if>
		</tr>
		<tr>
			<td>작성자</td>
			<td>asdasd
				<input type="hidden" name="writer" value="asdasd"/>
			</td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea name="post_content" rows="13" cols="40" ></textarea></td>
		</tr>
		<tr>
			<td>이미지</td>
			<td><input type="file" name="file" multiple="multiple"/></td>
		</tr>
		<tr>
			<td><input type="submit" value="등록" /></td>
		</tr>
	</table>
