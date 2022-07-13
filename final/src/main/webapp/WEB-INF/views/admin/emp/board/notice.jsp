<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
	<meta charset="UTF-8">
	<title>직원공지</title>
	<script>
		function confirmDel(){
			if(confirm("공지를 삭제하시겠습니까?")==true){
				window.location="/admin/emp/delNotice?ebnum=${dto.ebnum}";
			}else{
				return false;
			}
		}
	</script>
</head>

<body>
	<table>
		<tr>
			<th>제목</th>
			<td>${dto.subject }</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${dto.writer }</td>
			<td>조회수 : ${dto.readCnt }</td>
		</tr>
		<tr>
			<th>작성일</th>
			<td><fmt:formatDate pattern="yyyy.MM.dd" value="${dto.reg }"/></td>
		</tr>
		<tr>
			<th>내용</th>
			<td></td>
		</tr>
		<tr>
			<td colspan=2>${dto.post_content }</td>
		</tr>
	</table>
	<input type="button" value="목록으로 돌아가기" onclick="window.location='/admin/emp/noticeList'">
	<c:if test="${id eq dto.writer }">
		<input type="button" value="수정" onclick="window.location='/admin/emp/modNotice?ebnum=${dto.ebnum}'">
		<input type="button" value="삭제" onclick="confirmDel();">
	</c:if>
</body>
