<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
	<title> ���� �ڰ��� </title>
</head>

<a href="/certificate/certiLang?=">����</a>
<a href="/certificate/certiLang?=">�Ϻ���</a>
<a href="/certificate/certiLang?=">�߱���</a>
<a href="/certificate/certiLang?=">����</a>
<a href="/certificate/certiLang?=">�ƽþ�</a>
<table>
	<tr>
		<th>NO</th>
		<th>�ڰݸ�</th>
		<th>�ڰݰ������</th>
		<th>������</th>
		<th>������</th>
	</tr>
	
	<c:forEach var="board" items="${llist}">
		<tr>
			<td><c:out value="${board.cnum}"/></td>
		</tr>
	</c:forEach>
</table>