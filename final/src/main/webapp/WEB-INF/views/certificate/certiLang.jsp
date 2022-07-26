<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
	<title> ���� �ڰ��� </title>
</head>

<jsp:include page="../userNavBar.jsp"></jsp:include>
<jsp:include page="mainFilter.jsp" />
<table width=800 cellpadding="10" cellspacing="0" border=1" align="center">
	<tr>
		<th>NO</th>
		<th>�ڰݸ�</th>
		<th>�ڰݵ��</th>
		<th>�ڰݰ������</th>
	</tr>
	
	<c:forEach var="board" items="${list}">
		<tr>
			<td>
				<c:out value="${board.cnum}"/>
			</td>
			<td><a href="/certificate/certiContent?cnum=${board.cnum}">${board.cname}</a>	
			<td><c:out value="${board.clevel}"/></td>
			<td><c:out value="${board.company}"/></td>
			</tr>
		</c:forEach>
</table>