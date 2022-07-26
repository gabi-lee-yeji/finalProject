<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h1>검색 결과 (총 ${count}개)</h1>
			<table class="table">
				<thead class="thead-dark">
					<tr><th colspan=${ncs_length} style="text-align: center;font-size: 20px">검색된 항목</th></tr>
				</thead>
				<c:if test="${dto.ncs_cat != null }">
					<tr>
						<th style="text-align: center;font-size: 15px">대분류 :</th>
						<c:forEach var="ncs_name" items="${ncsName}">
							<td style="font-size: 15px">"${ncs_name}"</td>
						</c:forEach> 
					</tr>
				</c:if>
				<c:if test="${dto.clevel != null }">
					<tr>
						<th style="text-align: center;font-size: 15px">국가자격증 등급 :</th> 
						<c:forEach var="clevel" items="${dto.clevel}">
							<td style="font-size: 15px">"${clevel}"</td>
						</c:forEach>
					</tr>
				</c:if>
			</table>