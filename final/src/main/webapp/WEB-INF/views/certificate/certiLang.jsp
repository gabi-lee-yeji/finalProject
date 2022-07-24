<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
	<title> 어학 자격증 </title>
</head>

<jsp:include page="../userNavBar.jsp"></jsp:include>
<jsp:include page="langSideBar.jsp" />
<table width=800 cellpadding="10" cellspacing="0" border=1" align="center">
	<tr>
		<th>NO</th>
		<th>자격명</th>
		<th>자격등급</th>
		<th>자격관리기관</th>
		<th> </th>
	</tr>
	
	<c:forEach var="board" items="${list}">
		<tr>
			<td>
				<c:out value="${board.cnum}"/>
			</td>
			<td><a href="/certificate/certiContent?cnum=${board.cnum}">${board.cname}</a>	
			<td><c:out value="${board.clevel}"/></td>
			<td><c:out value="${board.company}"/></td>
			<td>
              	 <input type="button" class="addlike" value="+관심자격증" onclick="location.href='/like/add?cnum=${board.cnum}&memid=${sessionScope.sid}'"/>    	
            </td>
			</tr>
		</tr>
		</c:forEach>
</table>