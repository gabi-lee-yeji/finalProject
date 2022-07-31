<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <input type="hidden" name="pnum" value="${pnum}" />
  <input type="hidden" name="pageNum" value="${pageNum}" />
  <input type="hidden" name="memid" value="${sessionScope.sid}" />
  <table>
    <tr>
      <td><h4 style="font: bold">비밀번호를 입력해주세요</h4></td>
    </tr>
    <tr>
    	<td><input type="password" name="passwd" /></td>
    </tr>
    <tr>
      <td><input class="btn btn-primary" type="submit" value="삭제" /></td>
    </tr>
  </table>
</html>
