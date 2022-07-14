<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>일정 추가 : ${info.cname }</title>
</head>
<body>
	<jsp:include page="../adminNavBar.jsp"/>
	<table>	
		<tr>
			<td>자격증번호</td>
			<td>${info.cnum }</td>
		</tr>
		<tr>
			<td>자격증(시험) 이름 </td>
			<td>${info.cname }</td>
		</tr>
		<tr>
			<td>자격증(시험) 종류</td>
			<td>${info.category }</td>
		</tr>
		<tr>
			<td>자격증 등급</td>
			<td>${info.clevel }</td>
		</tr>
		<tr>
			<td>시행기관(접수처)</td>
			<td>${info.company }</td>
		</tr>
		<tr>
			<td>시행기관 웹사이트</td>
			<td><a href="${info.website }">${info.website }</a></td>
		</tr>
	</table>
	<hr>
	<h2>시험 일정</h2>
	<form action="/admin/certi/addDatePro" method="post">
		<input type="hidden" value="${info.cnum }" name="cnum">
		<input type="hidden" value="${info.clevel }" name="clevel">
		<table>
			<tr>
				<td>연도</td>
				<td><input type="text" name="cyear"></td>
			</tr>
			<tr>
				<td>자격증 회차</td>
				<td><input type="text" name="cround"></td>
			</tr>
			<tr>
				<td>필기 원서접수</td>
				<td>
					<input type="text" name="docRegStart1" onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
					~ <input type="text" name="docRegEnd1" onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
				</td>
			</tr>
			<tr>
				<td>필기원서 - 추가접수</td>
				<td>
					<input type="text" name="docRegStart2" onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
					~ <input type="text" name="docRegEnd2" onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
				</td>
			</tr>
			<tr>
				<td>필기시험</td>
				<td>
					<input type="text" name="docTestStart" onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
					~ <input type="text" name="docTestEnd" onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
				</td>
			</tr>
			<tr>
				<td>필기-합격자발표</td>
				<td>
					<input type="text" name="docResultStart" onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
					~ <input type="text" name="docResultEnd" onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
				</td>
			</tr>
			<tr>
				<td>응시자격 서류제출</td>
				<td>
					<input type="text" name="docSubmitStart" onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
					~ <input type="text" name="docSubmitEnd" onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
				</td>
			</tr>
			<tr>
				<td>실기시험 원서 접수</td>
				<td>
					<input type="text" name="pracRegStart1" onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
					~ <input type="text" name="pracRegEnd1" onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
				</td>
			</tr>
			<tr>
				<td>실기시험 추가 접수</td>
				<td>
					<input type="text" name="pracRegStart2" onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
					~ <input type="text" name="pracRegEnd2" onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
				</td>
			</tr>
			<tr>
				<td>실기시험</td>
				<td>
					<input type="text" name="pracTestStart" onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
					~ <input type="text" name="pracTestEnd" onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
				</td>
			</tr>
			<tr>
				<td>실기 합격자 발표</td>
				<td>
					<input type="text" name="pracResStart" onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
					~ <input type="text" name="pracResEnd" onKeypress="dateFormat(this)"placeholder="yyyy-MM-dd HH:mm" maxlength="16">
				</td>
			</tr>
		</table>
		<input type="submit" value="일정 등록">
	</form>
</body>
</html>