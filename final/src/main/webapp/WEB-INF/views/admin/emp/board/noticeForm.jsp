<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<head>
	<meta charset="UTF-8">
	<title>직원공지 등록</title>
</head>
<body>
	<form action="/admin/emp/addNoticePro" method="post">
		제목 : <input type="text" name="subject">
		<br>
		작성자 : ${id} 
		<input type="hidden" name="writer" value="${id}">
		<br>
		내용:
		<br>
		<textarea name="post_content" rows="13" cols="40" ></textarea>
		<br>
		<input type="submit" value="등록">
	</form>
</body>
