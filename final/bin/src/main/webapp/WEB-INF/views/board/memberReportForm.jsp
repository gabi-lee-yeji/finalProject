<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>글/댓글 신고</title>
  </head>
  <body>
    <form action="/community/memberReportPro" method="get">
      <table border="1">
        <c:if test="${board != null}">
          <input type="hidden" name="wr_option" value="P" />
          <input type="hidden" name="pnum" value="${board.pnum}" />
          <input type="hidden" name="memid" value="${board.writer}" />
          <tr>
            <td>신고받는자(글작성자)</td>
            <td>${board.writer}</td>
          </tr>
          <tr>
            <td>글 내용</td>
            <td>${board.post_content}</td>
          </tr>
        </c:if>
        <c:if test="${comm != null}">
          <input type="hidden" name="wr_option" value="C" />
          <input type="hidden" name="pnum" value="${comm.comm_num}" />
          <input type="hidden" name="memid" value="${comm.writer}" />
          <tr>
            <td>신고받는자(글작성자)</td>
            <td>${comm.writer}</td>
          </tr>
          <tr>
            <td>글 내용</td>
            <td>${comm.comm_content}</td>
          </tr>
        </c:if>
        <tr>
          <td>신고사유</td>
          <td>
            <select name="reason">
              <option>욕설, 불쾌한 표현, 혐오, 차별적 표현</option>
              <option>광고, 도배</option>
              <option>음란물</option>
              <option>개인정보 노출</option>
              <option>운영자 사칭</option>
            </select>
          </td>
        </tr>
      </table>
      <input type="hidden" name="report_id" value="${sessionScope.sid}" />
      <input type="submit" value="신고하기" />
    </form>
  </body>
</html>
