<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   

<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>

<form name="frm" action="/main/search" onsubmit="getCnum()" method="post">
	<table border="1">
		<tr>
			<th>자격증 이름</th>
			<td>
				<input type="text" id="certiSearch" list="searchList" name="keyword"/>
				<datalist id="searchList" >
					<c:forEach var="dto" items="${list }">
						<c:if test="${dto.cnum.charAt(0) == 80 }">
							<option data-value="${dto.cnum }">${dto.cname.concat(dto.clevel) }</option>
						</c:if>
						<c:if test="${dto.cnum.charAt(0) != 80 }">
							<option data-value="${dto.cnum }">${dto.cname}</option>
						</c:if>
					</c:forEach>
				</datalist>
			</td>
		</tr>
	</table>
	<input type="hidden" name="cnum" />
	<input type="submit" value="검색" />
</form>
<script>
	function getCnum(){
		var cname = $('#certiSearch').val();
		var cn = $('#searchList option').filter(function(){
			return this.value == cname;
		}).data('value');
		
		if(typeof cn !== 'undefined'){
			document.frm.cnum.value = cn;
		}else{
			document.frm.cnum.value = "";
		}
	}
</script>
