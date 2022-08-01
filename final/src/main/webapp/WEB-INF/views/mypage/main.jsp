<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>

<c:import url="/navbar"/>
<jsp:include page="sidebar.jsp" />
<div class="w3-main" style="margin-left:200px">
	<div class="container">
		<div class="row" style="min-height:100px">
			<div class="col-lg-6 col-md-12" style="">
				<h3 style="display:inline;">관심자격증 시험일정</h3>
				<c:import url="/calendar/myPage">
					<c:param name="memid" value="${sessionScope.sid }"></c:param>
				</c:import>
			</div>
			<div class="col-lg-6 col-md-12">
				<h3 style="display:inline;">내가 보유한 자격증</h3> 
				<a href = "/mypage/memberCerti">
					[관리]
				</a>
				<table class="table">
					<tr align="center">
						<th>자격증명</th>
						<th>만료일자</th>
					</tr>
					<c:forEach var="memberCertiDTO" items="${memberCertiList }">
						<tr align="center">
							<td>
								<c:if test="${memberCertiDTO.cnum != null }">
									<a href="/certificate/certiContent?cnum=${memberCertiDTO.cnum }">
								</c:if>
									${memberCertiDTO.cert_name }
								<c:if test="${memberCertiDTO.cnum != null }">
									</a>
								</c:if>
							</td>
							<td
								<jsp:useBean id="today" class="java.util.Date" scope="page"/>
								<c:if test="${memberCertiDTO.expirydate != null and ((memberCertiDTO.expirydate.getTime() - today.getTime()) <= 1000*60*60*24*30) }">
									style="color:red"
								</c:if>
							>
								<fmt:formatDate value="${memberCertiDTO.expirydate }" type="date" />
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<hr>
		<div class="row" style="min-height:100px">
			<div class="col-lg-6 col-md-12">
			<h3 style="display:inline;">사용자 맞춤 인기자격증</h3> 
			<table class="table table-hover">
				<c:forEach var="dto" items="${clientList }" varStatus="status">
					<tr>
						<th>${status.count}</th>			
						<td><a href="/certificate/certiContent?cnum=${dto.cnum }">${dto.cname }</a></td>
					</tr>
				</c:forEach>
			</table>
			</div>
			<div class="col-lg-6 col-md-12">
				<h3 style="display:inline;">나의 1:1 문의</h3> 
				<a href = "/member/myList?writer=${sessionScope.sid }&board_type=3">
					[더보기]
				</a>
				<table class="table">
					<tr align="center">
						<th>글번호</th>
						<th>제 목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>조회수</th>
					</tr>
					<c:forEach var="board" items="${boardList}">
						<tr align="center">
							<td>${board.pnum}</td>
							<c:if test="${board.board_type == 3}">
							<td><a href="/help/qna/qnaContent?pnum=${board.pnum}&pageNum=${currentPage}">${board.subject}</a></td>
							</c:if>
							<td>${board.writer}</td>
							<td>
								<fmt:formatDate value="${board.reg}" type="date" />
							</td>
							<td>${board.readCnt}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../footer.jsp" />