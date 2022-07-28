<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<html>
<head>
	<meta charset="UTF-8">
	<title>사이트 내 검색결과 : ${keyword }</title>
</head>
<body>
	<jsp:include page="userNavBar.jsp"/>
	<div>
		<h1>키워드 : "${keyword }"</h1>
		<c:if test="${count > 0 }">
			<c:if test="${certiCnt > 0 }">
				<h2>자격증 검색 결과 (${certiCnt})</h2>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>번호</th>
							<th>종목명</th>
							<th>자격증종류</th>
							<th>등급</th>
							<th>시행기관</th>		
						</tr>
					</thead>
					<tbody>
						<c:forEach var="dto" items="${map.certi}">
							<tr>
								<td>${dto.cnum }</td>
								<td><a href="/certificate/certiContent?cnum=${dto.cnum }"> ${dto.cname } </a></td>
								<td>${dto.category }</td>
								<td>${dto.clevel }</td>
								<td><a href="${dto.website }">${dto.company }</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<c:if test="${certiCnt > 10 }">
					<button type="button" class="btn btn-outline-primary" onclick="window.location='/searchCerti?keyword=${keyword}'">검색결과 더보기</button>
				</c:if>
				<hr>
			</c:if>
			<c:if test="${helpCnt > 0 }">
				<h2>공지게시판 검색 결과 (${helpCnt})</h2>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>글번호</th>
							<th>제 목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>조회수</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="dto" items="${map.help }">
							<tr>
								<td>${dto.pnum}</td>
								<td><a href="/${dto.board_mapping}?pnum=${dto.pnum}">${dto.subject}</a></td>
								<td>${dto.writer}</td>
								<td><fmt:formatDate value="${dto.reg}" pattern="yyyy.MM.dd"/></td>
								<td>${dto.readCnt }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<hr>
			</c:if>
			<c:if test="${communityCnt > 0 }">
				<h2>커뮤니티 검색 결과 (${communityCnt})</h2>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>글번호</th>
							<th>제 목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>조회수</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="dto" items="${map.community }">
							<tr>
								<td>${dto.pnum}</td>
								<td><a href="/${dto.board_mapping}?pnum=${dto.pnum}">${dto.subject}</a></td>
								<td>${dto.writer}</td>
								<td><fmt:formatDate value="${dto.reg}" pattern="yyyy.MM.dd"/></td>
								<td>${dto.readCnt }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<hr>
			</c:if>
			<c:if test="${commCnt > 0 }">
				<h2>전체댓글 검색 결과 (${commCnt})</h2>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>댓글내용</th>
						</tr>
						<tr>
							<td>게시글제목</td><td>작성일</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="dto" items="${map.comment}">
							<tr><th>${dto.commBoard.comm_content }</th></tr>
							<tr>
								<td><a href="/${dto.board_mapping}?pnum=${dto.pnum}">${dto.subject}</a></td>
								<td><fmt:formatDate value="${dto.reg}" pattern="yyyy.MM.dd"/></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
		</c:if>
		<c:if test="${count <= 0 }">
			<h2>검색 결과가 없습니다.</h2>
		</c:if>
	</div>
</body>
</html>