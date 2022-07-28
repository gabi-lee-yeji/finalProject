<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>

<style>

.page{
	text-align: center;
	width:90%;
}
.w3-btn {width:150px;}

</style>


<head>
	<title> 자격증 </title>
</head>
	<jsp:include page="../userNavBar.jsp"/>
	<div class="row" >
		<div class="col-2" style="margin-left:20px">
			<c:import url="/certificate/filterForm"/>
		</div>
		<div class="col-8" >
			<table class="table table-hover">
				<thead>
				<div class="w3-container">
					<button type="button" class="w3-button w3-black w3-round-large" onclick="location.href='/certificate/certiMain?category=national'">국가기술자격</button>
					<button type="button" class="w3-button w3-black w3-round-large" onclick="location.href='/certificate/certiMain?category=private'" >공인민간자격</button>
				</div>
					<tr>
						<th>NO</th>
						<th>자격명</th>
						<th>자격등급</th>
						<th>분류</th>
						<th>자격관리기관</th>
					</tr>
					</thead>
					<tbody>
						<c:forEach var="board" items="${clist}"> <!-- 전체 자격증 -->
							<tr>
								<td>
									${board.cnum}
								</td>
								<td><a href="/certificate/certiContent?cnum=${board.cnum}&pageNum=${currentPage}">${board.cname}</a>	
								<c:if test="${sessionScope.sid != null}">
										<c:if test="${check > 0}">
											<c:if test="${fn:contains( mlist, board.cnum )}">
												<input type="image"src="/resources/img/좋아요후.png" alt="제출" height="25" width="20" onclick="location.href='/like/delete?cnum=${board.cnum}&memid=${sessionScope.sid}'"/>
											</c:if>
											<c:if test="${!fn:contains( mlist, board.cnum )}">
												<input type="image"src="/resources/img/좋아요전.png" alt="제출" height="20" width="20" onclick="location.href='/like/add?cnum=${board.cnum}&memid=${sessionScope.sid}'"/>
											</c:if>
										</c:if>
									</c:if>
								<td><c:out value="${board.clevel}"/></td>
								<td>
									<c:set var="catArr" value="${fn:replace(fn:replace(board.category,'private','공인민간'),'national','국가기술')}"></c:set>
										<c:out value="${catArr}"/>
								</td>
								<td>${board.company}</td>
							</tr>
						</c:forEach>
					</tbody>
			</table>
		</div>
	</div>

	<div class="page">
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
