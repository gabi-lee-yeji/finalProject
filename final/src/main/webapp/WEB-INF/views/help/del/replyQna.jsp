<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1:1문의 답글</title>
</head>
<body>
	<h1>1:1문의 답글</h1>
	<input type="hidden" name="board_type" value="1:1문의" />
	<h2> 고객문의 원글 </h2>
	<table>
		<tr>
			<td>제목</td>
			<td>${dto.subject}</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${dto.writer}</td>		
		</tr>
		<tr>
			<td>문의 내용</td>
			<td>${post_content}</td>
		</tr>
		<tr>
			<td>이미지</td>
			<td>${dto.img}</td>
		</tr>
	</table>
<h1></h1>
	<form action="/help/qna/addQnaPro" name="addQna" method="get" >
	<input type="hidden" name="board_type" value="1:1문의" />
	<input type="hidden" name="post_group" value="${dto.post_group}">
	<input type="hidden" name="post_level" value="${dto.post_level}">
		<h2> 고객문의 답글 </h2>
		<table>
			<tr>
				<td>제목</td>
				<td><input type="text" name="subject" /></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>관리자</td>
				<input type="hidden" name="writer" value="관리자" />
			</tr>
			<tr>
				<td>답변</td>
				<td><textarea name="post_content" rows="13" cols="40" ></textarea></td>
			</tr>
			<tr>
				<td><input type="submit" value="등록" /></td>
			</tr>
		</table>
	</form>
	<input type="button" value="1:1문의 목록" onclick="location='help/qna/qnaList' " />
	
</body>
</html>
