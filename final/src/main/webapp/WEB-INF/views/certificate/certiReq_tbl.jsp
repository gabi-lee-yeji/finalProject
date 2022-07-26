<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
  
<h4>응시자격</h4>
* 이하 조건 중 해당하는 것이 있다면 응시가능합니다.
<table class="table table-bordered">
	<tr>
		<th>조건</th>
		<th>학력</th>
		<th>나이</th>
		<th>경력</th>
		<th>비고</th>
	</tr>
	<c:forEach var="dto" items="${list}" varStatus="status">
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
</table>