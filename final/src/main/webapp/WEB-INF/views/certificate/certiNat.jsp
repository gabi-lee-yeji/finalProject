<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
	<title> ������� �ڰ���</title>
</head>

<a href="/certificate/ceriList?category=�������"> �����ڰ��� </a>
<table>
	<tr>
		<th>��ȣ</th>
		<th>�ڰݸ�</th>
		<th>�ڰݵ��</th>
		<th>ȸ��</th>
		<th>������</th>
		<th>������</th>
	</tr>
	<c:forEach var="dto" items="${nlist}">
		<tr>
			<td>${dto.cnum}</td>
			<td>${dto.cname}</td>
			<td>${dto.clevel}</td>
			<td>${dto.cround}</td>
			<td>${dto.regStart}</td>
			<td>${dto.testDate}</td>
		</tr>
	</c:forEach>
</table>