<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>�� �ı� ��� (��ü ��:${count})</h1>
	<c:if test="${count == 0}">
		<table border=1>
			<tr>
				<td>�ı���� �����ϴ�.</td>
			</tr>
		</table>
	</c:if>
