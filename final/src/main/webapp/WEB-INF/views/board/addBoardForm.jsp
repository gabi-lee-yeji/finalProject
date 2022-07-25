<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<script>
	function check(){
		var rtn = true;
		var subject = document.getElementById("subject").value;
		var post_content = document.getElementById("post_content").value;
		
		//제목 공백 확인
		if($("#subject").val() == ""){
			alert("글 제목을 입력해주세요");
			$("#subject").focus();
			return false;
		}
		
		//글 내용 공백 확인
		if($("#post_content").val() == ""){
			alert("글 내용을 입력해주세요");
			$("#post_content").focus();
		     return false;
		}
		return rtn;
	};
</script>

<table>
	<tr>
		<td>제목</td>
		<c:if test="${pnum == 0}" >
			<td><input type="text" id="subject" name="subject" /></td>
		</c:if>
		<c:if test="${pnum != 0}" >
			<td>[re] ${board.subject}</td>
			<input type="hidden" name="subject" value="[re] ${board.subject}" />
		</c:if>
	</tr>
	<tr>
		<td>작성자</td>
			<td>${sessionScope.sid}
				<input type="hidden" name="writer" value="${sessionScope.sid}"/>
			</td>
	</tr>
	<tr>
		<td>내용</td>
		<td>
			<textarea name="post_content" id="post_content" rows="13" cols="40" >* 관련자격증을 입력해주세요: </textarea>
		</td>
	</tr>
	<tr>
		<td>이미지</td>
		<td><input type="file" name="file" multiple="multiple"/></td>
	</tr>
	<tr>
		<td><input type="submit" value="등록" /></td>
	</tr>
</table>
