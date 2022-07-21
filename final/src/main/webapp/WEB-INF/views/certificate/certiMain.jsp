<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>

<head>
	<title> 자격증 </title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>


<script>

 function getCnum(){
	var memid = '<%=(String)session.getAttribute("sid")%>';
	var cn = $('.addlike').filter(function(){
		return this.value == memid;
	}).data('value');
	
	if(typeof cn !== 'undefined'){
		document.frm.cnum.value = memid;
		return true;
	}else{
		alert(cn);
		return false;
	}
	
}
	
		//	 window.location.href='/like/add?cnum=+${board.cnum}&memid=+${memid}';
		
	
/*	$(".selected_td").click(function(){
		
		var str=""
		var tdArr = new Array();
		
		var selected_td = $(this);
		
		var tr = selected_td.parent().parent();
		var td = tr.children();
		
		console.log("클릭한 Row의 모든 데이터 : "+tr.text());  
		var cnum = td.eq(0).text();
		var memid = td.eq(1).text(); 	 
	}); 
	*/

</script>
</head>
<body>

<c:import url="filterForm.jsp" />

<form name="frm" action="/certificate/addLikePro"  onsubmit="return getCnum()" method="post">
	<table width=800 cellpadding="10" cellspacing="0" border=1">
	<c:if test="${count > 0}">
		<a href="/certificate/certiMain?category=national" >국가기술자격</a>
		<a href="/certificate/certiMain?category=private">공인민간자격</a>
		<tr>
			<th>NO</th>
			<th>자격명</th>
			<th>자격등급</th>
			<th>분류</th>
			<th> </th>
		</tr>
	</c:if>

		<c:forEach var="board" items="${clist}">
			<tr>
				<td>
					<c:out value="${board.cnum}"/>
				</td>
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
</form>	
	
	
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

</body>
