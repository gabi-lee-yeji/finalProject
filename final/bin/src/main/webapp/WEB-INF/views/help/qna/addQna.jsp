<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
	<meta charset="UTF-8">
	<title>1:1 문의</title>
</head>
<body>
<c:if test="${pnum == 0}" >
	<h1>1:1 문의 글 쓰기</h1>
	<form action="/help/qna/addQnaPro" name="addQna" method="get" >
	<input type="hidden" name="board_type" value="3" />
		<table>
			<tr>
				<td>제목</td>
				<td><input type="text" name="subject" /></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>${sessionScope.sid}</td>
					<input type="hidden" name="writer" value="${sessionScope.sid}" />
			</tr>
			<tr>
				<td>문의 내용</td>
				<td><textarea name="post_content" rows="13" cols="40" ></textarea></td>
			</tr>
			<tr>
				<td>첨부 이미지</td>
				<td><input type="file" name="img"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="등록" /></td>
			</tr>
		</table>
	</form>
	<input type="button" value="1:1문의 목록" onclick="window.location='/help/qna/qnaList?board_type=3' " />
</c:if>

<c:if test="${pnum != 0}" >
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

	<form action="/help/qna/addQnaPro" name="addQna" method="get" >
		<input type="hidden" name="board_type" value="3" />
		<input type="hidden" name="post_group" value="${dto.post_group}">
		<input type="hidden" name="post_level" value="${dto.post_level}">
		<h2> 고객문의 답글 </h2>
		<table>
			<tr>
				<td>제목</td>
				<td><input type="text" name="subject" value="[re] ${dto.subject}"/></td>
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
	<input type="button" value="1:1문의 목록" onclick="window.location='/help/qna/qnaList?board_type=1:1문의' " />
</c:if>
</body>
