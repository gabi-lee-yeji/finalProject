<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
<meta charset="UTF-8">
<title>notice 글 보기</title>
</head>
<body>
	<table border=1>
		<tr>
			<td>제목</td>
			<td>${dto.subject}</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${dto.writer}</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>${dto.post_content}</td>
		</tr>
	</table>
	<input type="button" value="수정" onclick="location='/help/modNotice?pnum=${dto.pnum}&pageNum=${pageNum}'" />
	<input type="button" value="삭제" onclick="location='/help/delNotice?pnum=${dto.pnum}&pageNum=${pageNum}&memid=test4'" />
	<input type="button" value="글목록" onclick="location='/help/noticeList?pnum=${dto.pnum}&pageNum=${pageNum}'" />
</body>



