<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	function check(){
		//글 내용 공백 확인
		if($("#post_content").val() == ""){
			alert("글 내용을 입력해주세요");
			$("#post_content").focus();
		     return false;
		}
		return true;
	};
</script>

  <table>
    <tr>
      <td>제목</td>
      <td>${board.subject}</td>
    </tr>
    <tr>
      <td>작성자</td>
      <td>${board.writer}</td>
    </tr>
    <tr>
      <td>내용</td>
      <td>
        <textarea id="post_content" name="post_content" rows="13" cols="40">
${board.post_content}</textarea>
      </td>
    </tr>
	<c:if test="${fn:length(boardAttach) > 0}">
		<tr>
			<td>첨부파일</td>
			<td>
				<c:forEach var="list" items="${boardAttach}">
					${list.fileName}<br />
				</c:forEach>
	      	</td>
      	</tr>
    </c:if>
    <tr>
      <td>비밀번호</td>
      <td><input type="password" name="passwd" /></td>
    </tr>
    <tr>
    	<td><input type="submit" value="수정 완료" /></td>
    </tr>
  </table>
  <input type="hidden" name="pnum" value="${board.pnum}" />
  <input type="hidden" name="memid" value="${sessionScope.sid}" />
  <input type="hidden" name="pageNum" value="${pageNum}" />
</html>
