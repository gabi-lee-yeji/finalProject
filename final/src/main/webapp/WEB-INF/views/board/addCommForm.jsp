<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>

<html>
<div>
<c:if test="${memid != null}">
<form role="form" action="/community/addComm" name="addComm">
	<table border=1>
	<h2>댓글 작성</h2>
		<tr>
			<td>작성자</td>
			<td>${memid}
				<input type="hidden" name="writer" value="${memid}" />
			</td>
		</tr>
		<tr>
			<td>댓글</td>
			<td><textarea name="comm_content" rows="5" cols="40" ></textarea></td>
		</tr>
	</table>
	
	<input type="hidden" name="pnum" value="${board.pnum}" />
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="submit" value="댓글작성"/>
</form>
</c:if>
</div>

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
		</tr>
	
	<c:forEach var="comm" items="${commList}">
		<tr>
			<td>${comm.comm_num}</td>
			<td>${comm.comm_content}</td>
			<td>${comm.writer}</td>
			<td>${comm.reg}</td>
			<td><button type="button" class="replyUpdateBtn" data-rno="${comm.comm_num}">수정</button></td>
  			<td><button type="button" class="replyDeleteBtn" data-rno="${comm.comm_num}">삭제</button></td>

			<td><input type="button" value="수정" onclick="window.location='/community/modComm?memid=안혜원&comm_num=${comm.comm_num}&writer=${comm.writer}&pnum=${comm.pnum}&pageNum=${pageNum}' "/></td>
			<td><input type="button" value="삭제" onclick="window.location='/community/delComm?memid=안혜원&comm_num=${comm.comm_num}&writer=${comm.writer}&pnum=${comm.pnum}&pageNum=${pageNum}' "/></td>
		</tr>
	</c:forEach>
	</table>
</c:if>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

$(document).ready(function(){
//댓글 수정 View
$(".replyUpdateBtn").on("click", function(){
	location.href = "/community/modComm?memid=안혜원
					+ "&comm_num=${comm.comm_num}"
					+ "&writer=${comm.writer}"
					+ "&pnum=${comm.pnum}"
					+ "&pageNum=${pageNum}"
					+ "&comm_num="+$(this).attr("data-rno");
});
		
//댓글 삭제 View
$(".replyDeleteBtn").on("click", function(){
	location.href = "/board/replyDeleteView?bno=${read.bno}"
		+ "&page=${scri.page}"
		+ "&perPageNum=${scri.perPageNum}"
		+ "&searchType=${scri.searchType}"
		+ "&keyword=${scri.keyword}"
		+ "&rno="+$(this).attr("data-rno");
});

}
// 삭제 버튼 클릭
$("#del_btn").click(function(){
 
 formObj.attr("action", "/community/delComm");
 formObj.attr("method", "get");  
 formObj.submit();
 
});

</script>
</html>