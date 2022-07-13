<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>자격증 상세일정 수정</title>
	<script>
		function dateFormat(obj){
			var len = obj.value.length;
			if(len == 4) obj.value += "-";
			if(len == 7) obj.value += "-";
			if(len == 10) obj.value += " ";
			if(len == 13) obj.value += ":";
		}
	</script>
</head>
<body>
	<jsp:include page="../adminNavBar.jsp">
	<h2>시험 일정</h2>
	<h3>자격증 번호 : ${dto.cnum }</h3>
	<form action="/admin/certi/modDatePro" method="post">
		<table>
			<tr>
				<td>시행 연도</td>
				<td><input type="text" name="cyear" value="${dto.cyear }"></td>
			</tr>
			<tr>
				<td>자격증 회차</td>
				<td><input type="text" name="cround" value="${dto.cround }"></td>
			</tr>
			<tr>
				<td>필기 원서접수</td>
				<td>
					<input type="text" name="docRegStart1" value="${dto.docRegStart1 }"
						onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
					~ 
					<input type="text" name="docRegEnd1" value="${dto.docRegEnd1 }"
						onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
				</td>
			</tr>
			<tr>
				<td>필기원서 - 추가접수</td>
				<td>
					<input type="text" name="docRegStart2" value="${dto.docRegStart2 }"
						onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
					~ 
					<input type="text" name="docRegEnd2" value="${dto.docRegEnd2 }"
						onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
				</td>
			</tr>
			<tr>
				<td>필기시험</td>
				<td>
					<input type="text" name="docTestStart" value="${dto.docTestStart }"
						onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
					~ 
					<input type="text" name="docTestEnd" value="${dto.docTestEnd }"
						onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
				</td>
			</tr>
			<tr>
				<td>필기-합격자발표</td>
				<td>
					<input type="text" name="docResultStart" value="${dto.docResultStart }"
						onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
					~ 
					<input type="text" name="docResultEnd" value="${dto.docResultEnd }"
						onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
				</td>
			</tr>
			<tr>
				<td>응시자격 서류제출</td>
				<td>
					<input type="text" name="docSubmitStart" value="${dto.docSubmitStart }"
						onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
					~ 
					<input type="text" name="docSubmitEnd" value="${dto.docSubmitEnd }"
						onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
				</td>
			</tr>
			<tr>
				<td>실기시험 원서 접수</td>
				<td>
					<input type="text" name="pracRegStart1" value="${dto.pracRegStart1 }"
						onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
					~ 
					<input type="text" name="pracRegEnd1" value="${dto.pracRegEnd1 }"
						onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
				</td>
			</tr>
			<tr>
				<td>실기시험 추가 접수</td>
				<td>
					<input type="text" name="pracRegStart2" value="${dto.pracRegStart2 }"
						onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
					~ 
					<input type="text" name="pracRegEnd2" value="${dto.pracRegEnd2 }"
						onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
				</td>
			</tr>
			<tr>
				<td>실기시험</td>
				<td>
					<input type="text" name="pracTestStart" value="${dto.pracTestStart }"
						onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
					~ 
					<input type="text" name="pracTestEnd" value="${dto.pracTestEnd }"
						onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
				</td>
			</tr>
			<tr>
				<td>실기 합격자 발표</td>
				<td>
					<input type="text" name="pracResStart" value="${dto.pracResStart }"
						onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
					~ 
					<input type="text" name="pracResEnd" value="${dto.pracResEnd }"
						onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
				</td>
			</tr>
		</table>
		<input type="hidden" name="datePK" value="${dto.datePK }">		
		<input type="hidden" name="cnum" value="${dto.cnum }">		
		
		<input type="submit" value="수정">
	</form>
</body>
</html>