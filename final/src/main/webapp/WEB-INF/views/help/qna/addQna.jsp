<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
	<meta charset="UTF-8">
	<title>1:1 문의</title>
</head>
<body>
	<h1>공지사항 등록</h1>
	<form action="/qna/addQnaPro" name="addQna" method="get" >
	<input type="hidden" name="board_type" value="1:1문의" />
		<table>
			<tr>
				<td>제목</td>
				<td><input type="text" name="subject" /></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>${memid}</td>
				<input type="hidden" name="memid" value="${memid}" />
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea name="post_content" rows="13" cols="40" ></textarea></td>
			</tr>
			<tr>
				<td>이미지</td>
				<td><input type="file" name="img"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="등록" /></td>
			</tr>
		</table>
	</form>
</body>