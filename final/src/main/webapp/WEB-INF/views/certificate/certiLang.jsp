<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
	<title> 어학 자격증 </title>
</head>
<body>
	<c:import url="/navbar"/>
	<section style="margin: 20px 5% 10% 5%">
		<div class="row" >
			<div class="col-2" style="margin-left:50px">
				<c:import url="/certificate/langFilterForm"/>
			</div>
			<div class="col-8" >
				<h2>어학시험</h2>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>NO</th>
							<th>시험명</th>
							<th>자격등급</th>
							<th>시행기관</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="dto" items="${list}">
							<tr>
								<td>${dto.cnum}</td>
								<td>
									<a href="/certificate/certiContent?cnum=${dto.cnum}">${dto.cname}</a>
									<c:if test="${sessionScope.sid != null}">
										<c:if test="${fn:contains( mlist, dto.cnum )}">
											<input type="image"src="/resources/img/좋아요후.png" alt="제출" height="25" width="20" onclick="location.href='/like/delete?cnum=${dto.cnum}&memid=${sessionScope.sid}'"/>
										</c:if>
										<c:if test="${!fn:contains( mlist, dto.cnum )}">
											<input type="image"src="/resources/img/좋아요전.png" alt="제출" height="20" width="20" onclick="location.href='/like/add?cnum=${dto.cnum}&memid=${sessionScope.sid}'"/>
										</c:if>
									</c:if>
								</td>
								<td>${dto.clevel}</td>
								<td><a href="${dto.website}">${dto.company}</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<c:if test="${count > 0}">
			<c:set var="pageCount" value="${count / page.pageSize + ( count % page.pageSize == 0 ? 0 : 1)}"/>
			
			<!-- int만 나올 수 있도록 fmt 통해서 포맷팅해줌 -->
			<fmt:parseNumber var="result" value="${page.currentPage/10}" integerOnly="true"/>
			<c:set var="startPage" value="${result*10+1}"/>
			
			<c:set var="pageBlock" value="${10}"/>
			<c:set var="endPage" value="${startPage + pageBlock-1}"/>
			
			<c:if test="${endPage > pageCount}">
				<c:set var="endPage" value="${pageCount}" />
			</c:if>
			
			<ul class="pagination justify-content-center">
		        <c:if test="${startPage > 10 }">
		        	<li class="page-item">
		        		<a class="page-link" href="/certificate/certiLang?pageNum=${startPage-10}">
		        			이전
		        		</a>
		        	</li>
		        </c:if>
		        <c:forEach var="i" begin="${startPage}" end="${endPage}" step="1" >
		        	<li class="page-item">
			        	<a class="page-link" href="/certificate/certiLang?pageNum=${i}" >
			        		${i}
			        	</a>
			        </li>
				</c:forEach>
				<c:if test="${endPage < pageCount}">
					<li class="page-item">
			        	<a class="page-link" href="/certificate/certiLang?pageNum=${startPage + 10}">
			        		다음
			        	</a>
			        </li>
				</c:if>
			</ul>
	   	</c:if>
	</section>
	<jsp:include page="/WEB-INF/views/footer.jsp" />
</body>