<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${sessionScope.sid != null}">
<form role="form" action="/community/addComm" name="addComm">
	<h2>댓글 작성</h2>
	<table border=1>
		<tr>
			<td>작성자</td>
			<td>${sessionScope.sid}
				<input type="hidden" name="writer" value="${sessionScope.sid}" />
			</td>
		</tr>
		<tr>
			<td>댓글</td>
			<td><textarea name="comm_content" rows="5" cols="40" ></textarea></td>
		</tr>
	</table>
	
	<input type="hidden" name="pnum" value="${board.pnum}" />
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="submit" value="댓글작성" />
</form>
</c:if>

<c:if test="${comm_BoardCount > 0}">
	<h2>댓글 목록</h2>
	<table border=1>
		<tr>
			<th>댓글번호</th>
			<th>글내용</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>수정</th>
			<th>삭제</th>
			<th>신고</th>
		</tr>
	
	<c:forEach var="comm" items="${commList}">
		<tr> 
			<td>${comm.comm_num}</td>
			<td>${comm.comm_content}</td>
			<td>${comm.writer}</td>
			<td>${comm.reg}</td>
			<td><input type="button" value="수정" onclick="modComm(${comm.comm_num});" ></td>
			<td><input type="button" value="삭제" onclick="window.location='/community/delComm?comm_num=${comm.comm_num}&writer=${sessionScope.sid}&pnum=${comm.pnum}&pageNum=${pageNum}' "/></td>
			<td><input type="button" value="신고" onclick="addReport(${comm.comm_num});" ></td>
		</tr>
	</c:forEach>
	</table>
</c:if>

<script>

	function modComm(comm_num){
		window.open("/community/modComm?comm_num="+comm_num , 
			"댓글 수정", "width=400, height=300, left=100, top=50"); 
	}
	
	// 변수로 대입하는 방법 위와 같음
	function addReport(comm_num){
	     var url = "/community/memberReportForm?comm_num="+comm_num ;
         var name = "댓글 신고";
         var option = "width=400, height=300, left=100, top=50"
         window.open(url, name, option);
	}
</script>
