<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
	<title> 자격증 </title>
</head>

<script language="javascript">
	function addLike(){
		var sid = '<%=(String)session.getAttribute("sid")%>';
	/*	if(sid == "null"){	그냥 null이면 오류가난다..why
			alert("로그인이 필요한 서비스입니다.");
			window.location='/member/loginForm';
		}else*/ if(document.getElementById("like").value != null){
			alert("관심자격증에 추가되었습니다.");
			 window.location='/like/add';
		}
	}
</script>

<body>

<c:import url="/certificate/filterForm" />

	<table width=800 cellpadding="10" cellspacing="0" border=1">
	<c:if test="${count > 0}">
		<a href="/certificate/certiMain?category=national" >국가기술자격</a>
		<a href="/certificate/certiMain?category=private">공인민간자격</a>
		<tr>
			<th>NO</th>
			<th>자격명</th>
			<th>자격등급</th>
			<th>분류</th>
			<th>회차</th>
			<th><a href="">접수일</a></th>
			<th><a href="">시험일</a></th>
		</tr>
	</c:if>

		<c:forEach var="board" items="${clist}">
			<tr>
				<td><c:out value="${board.cnum}" /></td>
				<td><a href="/certificate/certiContent?cnum=${board.cnum}&pageNum=${currentPage}">${board.cname}</a>				
				<input type="button" value="♡" id="like" onClick="addLike(this.form)"/></td>
				<td><c:out value="${board.clevel}"/></td>
				<td><c:out value="${board.category}"/></td>
			</tr>
		 <c:forEach var="date" items="${dateList}">	
				<td><c:out value="${date.cround}"/></td>
				<td>${date.docRegStart1}~${date.docRegEnd1}</td>
				<td>${date.docTestStart } 
			<c:if test="${date.docTestEnd != null }">
				~ ${date.docTestEnd} </c:if></td>
		</c:forEach>
		</c:forEach>
		</table>
	
	
	
	<c:if test="${count > 0}">
		<c:set var="pageCount" value="${count / pageSize + (count % pageSize == 0 ? 0 : 1)}"/>
		<fmt:parseNumber var="result" value="${(currentPage/10)}" integerOnly="true" />
		<c:set var="startPage" value="${result*10+1}" />
		<c:set var="pageBlock" value="${10}" />	
		<c:set var="endPage" value="${startPage + pageBlock - 1}" />
		<c:set var="currentPage" value="${currentPage}"/>
		<c:if test="${endPage > pageCount}">
			<c:set var="endPage" value="${pageCount}" />
		</c:if>
	</c:if>
	
	<c:if test="${startPage > 10 }" >
		<a href="/certificate/certiMain?pageNum=${startPage - 10}">[이전]</a>
	</c:if>
	
	<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1" >
		<a href="/certificate/certiMain?pageNum=${i}">[${i}]</a>
	</c:forEach>>
	
	<c:if test="${endPage < pageCount}" >
		<a href="/certificate/certiMain?pageNum=${startPage + 10}">[다음]</a>
	</c:if>
</div>
</section>
</body>
