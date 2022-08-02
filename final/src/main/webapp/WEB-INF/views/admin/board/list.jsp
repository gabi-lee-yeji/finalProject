<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
	<meta charset="UTF-8">
	<title>전체 게시글 조회</title>
</head>
<body>
	<jsp:include page="sidebar.jsp"></jsp:include>
	<div class="w3-main" style="margin-left:200px">
		<div class="bg-dark">
		  <button class="w3-button bg-dark w3-xlarge w3-hide-large" onclick="w3_open()">&#9776;</button>
		  <div class="w3-container">
		    <jsp:include page="../adminNavBar.jsp"></jsp:include>
		  </div>
		</div>
		<div style="margin-left:50px;margin-right:50px; margin-top:20px">
			<h1>전체 게시글 목록 [총 : ${count}개]</h1>
			<form action="/admin/board/search" method="post">
				<div class="row">
					<select class="form-control" style="max-width:8%" name="board_type">
						<option value="">전체</option>
						<option value="1">공지사항</option>
						<option value="2">FAQ</option>
						<option value="3">1:1 문의</option>
						<option value="4">꿀팁/후기</option>
						<option value="5">질문</option>
						<option value="6">정보</option>
						<option value="7">취준생</option>
					</select>
					<select class="form-control" style="max-width:10%" name="search">
						<option value="both">제목+내용</option>
						<option value="writer">작성자</option>
					</select>	
					<input class="form-control" style="max-width:10%" type="text" name="keyword">
					<input class="btn btn-primary" type="submit" value="검색">
				</div>
			</form>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>게시판</th>
						<th>작성일</th>
						<th>조회수</th>
						<th>
							<select name="status" onchange="location.href=this.value">
								<c:if test="${status eq 1 }">
									<option value="">==삭제==</option>
								</c:if>
								<c:if test="${status eq 0 }">
									<option value="">==일반==</option>
								</c:if>
								<c:if test="${status == null }">
									<option value="">==전체글==</option>
								</c:if>
								<option value="/admin/board/list?board_type=${board_type }">전체글</option>
								<option value="/admin/board/list?board_type=${board_type }&status=1">삭제</option>
								<option value="/admin/board/list?board_type=${board_type }&status=0">일반</option>
							</select>
						</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="dto" items="${list }">
						<tr>
							<td>${dto.pnum }</td>
							<td>
								<a href="/${dto.board_mapping }?pnum=${dto.pnum}">${dto.subject}</a>
							</td>
							<td>${dto.writer }</td>
							<td>${dto.board_name }</td>
							<td>
								<fmt:formatDate pattern="yy/MM/dd" value="${dto.reg }"/>
							</td>
							<td>${dto.readCnt }</td>
							<td>
								<c:if test="${dto.status eq 1 }">
									삭제됨
								</c:if>
								<c:if test="${dto.status eq 0 }">
									일반
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
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
		        		<a class="page-link" href="/admin/board/list?pageNum=${startPage-10}&board_type=${board_type}&status=${status}">이전</a>
		        	</li>
		        </c:if>
		        
		        <c:forEach var="i" begin="${startPage}" end="${endPage}" step="1" >
		        	<li class="page-item">
		        		<a class="page-link" href="/admin/board/list?pageNum=${i}&board_type=${board_type}&status=${status}">${i}</a>
		        	</li>
				</c:forEach>
				
				<c:if test="${endPage < pageCount}">
					<li class="page-item">
		        		<a class="page-link" href="/admin/board/list?pageNum=${startPage + 10}&board_type=${board_type}&status=${status}">다음</a>
		        	</li>
				</c:if>
			</ul>
	    </c:if>
    </div>
    
    <jsp:include page="/WEB-INF/views/footer.jsp" />
</body>
