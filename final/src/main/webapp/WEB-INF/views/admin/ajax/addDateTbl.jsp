<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<hr>
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