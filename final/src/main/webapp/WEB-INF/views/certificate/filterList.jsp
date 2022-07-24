<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="../userNavBar.jsp"></jsp:include>
<jsp:include page="filterForm.jsp" />
<title>필터링 결과</title>
</head>
<body>
	<table width=800 cellpadding="10" cellspacing="0" border=1" align="center">
		<tr>
			<th>NO</th>
			<th>자격명</th>
			<th>자격등급</th>
			<th>분류</th>
			<th> </th>
		</tr>
		<c:forEach var="board" items="${list}">
			<tr>
				<td>${board.cnum }</td>
			<td><a href="/certificate/certiContent?cnum=${board.cnum}&pageNum=${currentPage}">${board.cname}</a>
			<td><c:out value="${board.clevel}"/></td>
			<td>
					<c:set var="catArr" value="${fn:replace(fn:replace(board.category,'private','공인민간'),'national','국가기술')}"></c:set>
						<c:out value="${catArr}"/>
				</td>
				<td>
					<input type="button" class="addlike" value="+관심자격증" onclick="location.href='/like/add?cnum=${board.cnum}&memid=${sessionScope.sid}'"/>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	
	
</body>