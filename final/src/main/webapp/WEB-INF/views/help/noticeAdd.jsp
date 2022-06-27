<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
	<meta charset="UTF-8">
	<title>공지사항</title>
</head>
<body>
	<h1>공지사항 등록</h1>
	<form action="/help/noticeAddPro" name="noticeAdd" method="get">
	<input type="hidden" name="board_type" value="공지사항" />
	post_group <input type="number" name="post_group" />
	post_level <input type="number" name="post_level" />
	img <input type="text" name="img" />
	readcnt: <input type="number" name="readCnt" />	
		<table>
			<tr>
				<td>제목</td>
				<td><input type="text" name="subject" /></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><input type="text" name="writer" value="관리자"/></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea name="post_content" rows="13" cols="40" ></textarea></td>
			</tr>
			<tr>
				<td><input type="submit" value="등록" /></td>
			</tr>
			<!-- 카테고리(board_type)는 자동으로 입력 (controller)
			reg 자동 입력 > xml -->		
		</table>
	</form>
</body>
