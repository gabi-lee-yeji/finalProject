<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
	<title> �ΰ� �ڰ��� </title>
</head>

<table>
	<tr>
		<th>��ȣ</th>
		<th>�ڰݸ�</th>
		<th>�ڰݰ������</th>
		<th>������</th>
		<th>������</th>
	</tr>
	<c:forEach var="dto" items="${plist}">
		<tr>
			<td>${dto.cnum}</td>
			<td>${dto.cname}</td>
		</tr>
	</c:forEach>
</table>