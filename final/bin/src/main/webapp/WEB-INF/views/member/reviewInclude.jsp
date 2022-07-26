<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>내 후기 목록 (전체 글:${count})</h1>
	<c:if test="${count == 0}">
		<table border=1>
			<tr>
				<td>후기글이 없습니다.</td>
			</tr>
		</table>
	</c:if>
