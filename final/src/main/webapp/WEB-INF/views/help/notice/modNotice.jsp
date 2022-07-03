<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
</head>
<body>
	<h1>공지사항 글 수정</h1>
	<form action="/help/notice/modNoticePro" method="get" >
	<input type="hidden" name="pnum" value="${dto.pnum}" />
	<input type="hidden" name="pageNum" value="${pageNum}" />
		<table border=1>
			<tr>
				<td>제목</td>
				<td><input type="text" name="subject" value="${dto.subject}" /></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>${dto.writer}</td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea name="post_content" rows="13" cols="40">${dto.post_content}</textarea></td>
			</tr>
		</table>
		<input type="submit" value="수정 완료" />
	</form>
</body>



