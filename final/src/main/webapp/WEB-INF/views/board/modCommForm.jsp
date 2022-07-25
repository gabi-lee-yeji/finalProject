<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 수정 창</title>
<script>
// 예지님 질문 ㅠ ㅠㅠ ! 
	function check(){
		var rtn = true;
		var comm_content = document.getElementById("comm_content").value;
		
		//댓글내용 공백 확인
		if($("#comm_content").val() == ""){
			alert("댓글을 입력해주세요");
			$("#comm_content").focus();
			return false;
		}
		return rtn;
	}
</script>
</head>
<body>
<form role="form" action="/community/modCommPro" name="modComm" onsubmit="return check()">
	<h2>댓글 수정</h2>
	<table border=1>
		<tr>
			<td>작성자</td>
			<td>${comm.writer}</td>
		</tr>
		<tr>
			<td>댓글</td>
			<td><textarea name="comm_content" id="comm_content" rows="5" cols="40" >${comm.comm_content}</textarea></td>
		</tr>
	</table>
	
	<input type="hidden" name="comm_num" value="${comm.comm_num}"/>
	<input type="hidden" name="writer" value="${comm.writer}"/>
	<input type="hidden" name="pageNum" value="${pageNum}" />
	<input type="submit" value="댓글 수정" />
</form>
</body>
</html>