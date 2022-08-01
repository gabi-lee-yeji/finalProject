<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
	<meta charset="UTF-8">
	<title>자격증 정보 - ${info.cname }</title>
</head>
<body>
	<jsp:include page="../adminNavBar.jsp"/>
	<section style="margin: 20px 5% 10% 5%">
		<h2>자격증 정보</h2>
		<input type="button" class="btn btn-primary" style="float:right" value="자격증 목록" onclick="window.location='/admin/certiList'">
		<form action="/admin/modCertiPro?cnum=${cnum }">
			<table class="table table-bordered">
				<tr>
					<td>자격증 번호</td>
					<td>
						${cnum }
						<input type="hidden" name="cnum" value="${cnum }">
					</td>
				</tr>
				<tr>
					<td>자격증 이름</td>
					<td><input type="text" name="cname" value="${info.cname }"></td>
				</tr>
				<tr>
					<td>자격증(시험) 종류</td>
					<td>
						<input type="text" name="category" value="${info.category }" readonly>
						<a href="/admin/cert/chgCate">수정 요청하기</a> 
					</td>
				</tr>
				<tr>
					<td>등급</td>
					<td>
						<input type="text" name="clevel" value="${info.clevel }" />
					</td>
				</tr>
				<tr>
					<td>시행기관</td>
					<td>
						<input type="text" name="company" value="${info.company }" />
					</td>
				</tr>
				<tr>
					<td>웹사이트</td>
					<td>
						<a href="${info.website}">${info.website }</a> <br>
						<input type="text" name="website" value="${info.website }"/>
					</td>
				</tr>
				<tr>
					<td>응시료</td>
					<td><input type="text" name="price" value="${info.price }"/></td>
				</tr>
				<tr>
					<td>상세일정확인</td>
					<td>
						<a href="/admin/certiDate?cnum=${info.cnum }&cname=${info.cname}">${info.cname } 상세일정</a>
					</td>
				</tr>
			</table>
			<details>
				<summary>상세정보 확인</summary>
				<table class="table table-bordered">
					<tr>
						<td>검정방법</td>
						<td>
							<input type="text" name="cmethod" value="${info.cmethod }" />
						</td>
					</tr>
					<tr>
						<td>검정과목</td>
						<td>
							<input type="text" name="subject" value="${info.subject }" />
						</td>
					</tr>
					<tr>
						<td>합격기준</td>
						<td><input type="text" name="cutline" value="${info.cutline }"/></td>
					</tr>
					<tr>
						<td>자격증 정보(개요)</td>
						<td><textarea name="cinfo" >${info.cinfo }</textarea></td>
					</tr>
					<tr>
						<td>관련직업 / 진로(전망)</td>
						<td><input type="text" name="cjob" value="${info.cjob }"/></td>
					</tr>
					
					<tr>
						<td>NCS코드</td>
						<td><input type="text" name="ncs_cat" value="${info.ncs_cat }"/></td>
					</tr>
					<tr>
						<td>비고</td>
						<td><textarea name="notice">${info.notice }</textarea></td>
					</tr>
				</table>
			</details>
			<input type="submit" class="btn btn-outline-primary" value="수정">
		</form>
		<details>
			<summary>응시자격 확인</summary>
			<table class="table table-bordered">
				<tr>
					<th>조건</th>
					<th>학력</th>
					<th>나이</th>
					<th>경력</th>
					<th>비고</th>
				</tr>
				<c:if test="${reqCnt > 0 }">
					<c:forEach var="dto" items="${reqList}" varStatus="status">
						<tr>
							<td>${status.count}</td>
							<td>${dto.req_degree }</td>
							<td>
								<c:if test="${dto.req_age == 0}">
									제한없음
								</c:if>
								<c:if test="${dto.req_age != 0}">
									${dto.req_age}
								</c:if>
							</td>
							<td>
								<c:if test="${dto.req_exp == 0}">
									제한없음
								</c:if>
								<c:if test="${dto.req_exp != 0}">
									${dto.req_exp }년 이상 
								</c:if>
							</td>
							<td>
								<c:if test="${dto.pre_requisite != null}">
									${dto.pre_requisite } 자격을 가진 자<br>
								</c:if>
								${dto.ref }
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${reqCnt <= 0 }">
					<tr><td colspan=5>응시자격 제한이 없습니다</td></tr>
				</c:if>
			</table>
		</details>
	<input type="button" class="btn btn-outline-danger" value="자격증 삭제" onclick="window.location='certi/deleteForm?cnum=${cnum}'">
</body>
