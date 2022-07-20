<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
	<hr>
	<table>
		<tr>
			<td>연도</td>
			<td><input type="text" name="cyear${count}" id="cyear"></td>
		</tr>
		<tr>
			<td>자격증 회차</td>
			<td><input type="text" name="cround${count}" id="cround"></td>
		</tr>
		<tr>
			<td>필기 원서접수</td>
			<td>
				<input type="text" name="docRegStart1${count}" id="docRegStart1" onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
				~ <input type="text" name="docRegEnd1${count}" id="docRegEnd1" onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
			</td>
		</tr>
		<tr>
			<td>필기원서 - 추가접수</td>
			<td>
				<input type="text" name="docRegStart2${count}" id="docRegStart2" onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
				~ <input type="text" name="docRegEnd2${count}" id="docRegEnd2" onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
			</td>
		</tr>
		<tr>
			<td>필기시험</td>
			<td>
				<input type="text" name="docTestStart${count}" id="docTestStart" onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
				~ <input type="text" name="docTestEnd${count}" id="docTestEnd" onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
			</td>
		</tr>
		<tr>
			<td>필기-합격자발표</td>
			<td>
				<input type="text" name="docResultStart${count}" id="docResultStart" onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
				~ <input type="text" name="docResultEnd${count}" id="docResultEnd" onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
			</td>
		</tr>
		<tr>
			<td>응시자격 서류제출</td>
			<td>
				<input type="text" name="docSubmitStart${count}" id="docSubmitStart" onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
				~ <input type="text" name="docSubmitEnd${count}" id="docSubmitEnd" onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
			</td>
		</tr>
		<tr>
			<td>실기시험 원서 접수</td>
			<td>
				<input type="text" name="pracRegStart1${count}" id="pracRegStart1" onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
				~ <input type="text" name="pracRegEnd1${count}" id="pracRegEnd1" onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
			</td>
		</tr>
		<tr>
			<td>실기시험 추가 접수</td>
			<td>
				<input type="text" name="pracRegStart2${count}" id="pracRegStart2" onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
				~ <input type="text" name="pracRegEnd2${count}" id="pracRegEnd2" onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
			</td>
		</tr>
		<tr>
			<td>실기시험</td>
			<td>
				<input type="text" name="pracTestStart${count}" id="pracTestStart" onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
				~ <input type="text" name="pracTestEnd${count}" id="pracTestEnd" onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
			</td>
		</tr>
		<tr>
			<td>실기 합격자 발표</td>
			<td>
				<input type="text" name="pracResStart${count}" id="pracResStart" onKeypress="dateFormat(this)" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
				~ <input type="text" name="pracResEnd${count}" id="pracResEnd" onKeypress="dateFormat(this)"placeholder="yyyy-MM-dd HH:mm" maxlength="16">
			</td>
		</tr>
	</table>
</div>