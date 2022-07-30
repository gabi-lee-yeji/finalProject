<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
	<meta charset="UTF-8">
	<title>신고정보 - ${memid }</title>
	<script>
	
	</script>
</head>
<body>
	<jsp:include page="../adminNavBar.jsp"/>
	<section style="margin: 20px 10% 10% 10%">
		<h1>회원정보</h1>
		<form action="/admin/member/memReportPro" method="post">
			<table class="table table-bordered">
				<tr>
					<td>ID</td>
					<td>${memid }</td>
				</tr>
				<tr>
					<td>신고당한 횟수</td>
					<td>${reportCnt }</td>
				</tr>
				<tr>
					<td>회원상태</td>
					<td>
						<select name="status">
							<option value="${dto.status}">==${dto.status_name}==</option>
							<option value="0">일반</option>
							<option value="2">활동중지</option>
							<option value="4">강제탈퇴</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>상태변경일</td>
					<td><fmt:formatDate value="${dto.ref_date}" pattern="yyyy-MM-dd HH:mm"/></td>
				</tr>
				<tr>
					<td>보유포인트</td>
					<td>${dto.mem_point }</td>
				</tr>
				<tr>
					<td>가입일</td>
					<td><fmt:formatDate value="${dto.regdate}" pattern="yyyy-MM-dd HH:mm"/></td>
				</tr>
			</table>
			<input type="hidden" name="memid" value="${memid }">
			<input type="submit" class="btn btn-outline-primary" value="회원 상태 수정">
		</form>
		<div class="row">
			<div class="col">
				<h1>신고당한 글 목록 [${postingCnt}]</h1>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>글번호</th>
							<th>글제목</th>
							<th>게시판</th>
							<th>신고(회)</th>  
						</tr>
					</thead>
					<tbody>
						<c:forEach var="map" items="${postList }" >
							<tr>
								<td>${map.PNUM }</td>
								<td>
									<a href="/${map.BOARD_MAPPING }?pnum=${map.PNUM}"> 
										<c:if test="${map.SUBJECT == null }">(제목없음)</c:if>
										<c:if test="${map.SUBJECT != null }">${map.SUBJECT}</c:if>
									</a>
								</td>
								<td>${map.BOARD_NAME }</td>
								<td>${map.REPORTCNT }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="col">
				<h1>신고당한 댓글목록 [${commCnt}]</h1>
				<form action="/admin/member/delComment" method="post">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>게시글(댓글)</th>
								<th>댓글</th>
								<th>작성일</th>
								<th>신고(회)</th>
								<th>삭제여부</th>
								<th></th>
								<th>선택</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="map" items="${commList }">
								<tr>
									<td>${map.PNUM }(${map.COMM_NUM})</td>
									<td>${map.COMM_CONTENT }</td>
									<td><fmt:formatDate value="${map.REG }" pattern="yyyy-MM-dd HH:mm"/></td>
									<td>${map.REPORTCNT }</td>
									<td>
										<c:if test="${map.STATUS == 0 }">
											게시 
										</c:if>
										<c:if test="${map.STATUS == 1 }">
											삭제
										</c:if>
									</td>
									<td>
										<a href="javascript:getReasons(${map.COMM_NUM})">신고사유</a>
									</td>
									<td>
										<c:if test="${map.STATUS == 0 }">
											<input type="checkbox" value="${map.COMM_NUM}" name="comm_num">
										</c:if>
									</td>
								</tr>
								<script>
									function getReasons(val){
										var url = "/admin/member/commReport?memid=${memid }&pnum="+val
										window.open(url, "신고사유","width=500,height=500");
									}
								</script>
							</c:forEach>
						</tbody>
					</table>
					<input type="hidden" value="${reportCnt}" name="reportCnt">
					<input type="hidden" value="${memid}" name="memid">
					<input type="submit" value="선택한 댓글 삭제">
				</form>
			</div>
		</div>
	</section>
</body>
</html>