<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>

<script>
	$(document).ready(function(){
		$(".btnDelete").click(function(){
			var confirmDelete = confirm("정말로 삭제하시겠습니까?");
			if(confirmDelete){
				window.location = "/mypage/deleteMemberCertiPro?mcnum=" + $(this).attr("id");
			}
		});
	});

</script>

<h1>보유 자격증 목록</h1>
<table>
	<tr>
		<th>자격증명</th>
		<th>만료일자</th>
		<th>수정</th>
		<th>삭제</th>
	</tr>
	
	<c:forEach var="dto" items="${list }">
		<tr>
			<td>${dto.cert_name }</td>
			<td
				<jsp:useBean id="today" class="java.util.Date" scope="page"/>
				<c:if test="${dto.expirydate != null and ((dto.expirydate.getTime() - today.getTime()) <= 1000*60*60*24*7) }">
					style="color:red"
				</c:if>
			>
				<fmt:formatDate value="${dto.expirydate }" type="date" /><br> 
			</td>
			<td>
				<button type="button" onclick="window.open('/mypage/updateMemberCertiForm?mcnum=${dto.mcnum}', '수정', 'width=300, height=170')">
					수정
				</button>
			</td>
			<td>
				<button type="button" class="btnDelete" id="${dto.mcnum }">
					삭제
				</button>
			</td>
		</tr>
	</c:forEach>


</table>