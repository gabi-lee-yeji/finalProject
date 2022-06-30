<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
	<meta charset="UTF-8">
	<title>공지사항</title>
</head>

<body>
	<h1>공지사항 등록</h1>
	<form action="/help/notice/addNoticePro" name="addNotice" method="post" encType="multipart/form-data" >
	<input type="hidden" name="board_type" value="공지사항" />
		<table>
			<tr>
				<td>제목</td>
				<td><input type="text" name="subject" /></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>관리자</td>
				<input type="hidden" name="writer" value="관리자"/>
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
