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
			<div class="col-lg-6 col-md-12">
				<p>캘린더 들어갈 공간</p>
			</div>
			<div class="col-lg-6 col-md-12">
				<p>
					내가 보유한 자격증 
					<a href = "/mypage/memberCerti">
						[관리]
					</a>
				</p>
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
		<div class="row" style="min-height:100px">
			<div class="col-lg-6 col-md-12">
				<p>개인 추천 자격증</p>
			</div>
			<div class="col-lg-6 col-md-12">
				<p>내 1:1 문의</p>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../footer.jsp" />