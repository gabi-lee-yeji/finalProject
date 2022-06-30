<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<head>
	<meta charset="UTF-8">
	<title>자격증 정보 등록</title>
	<script>
		function inputDate(obj){
			if(event.keyCode != 8){
				if(obj.value.replace(/[0-9\-]/g,"").length==0){
					let number = obj.value.replace(/[^0-9]/g,"");
					let ymd = "";
					
					if(number.length < 4) {
		            	return number;
	                }else if(number.length < 6){
	                    ymd += number.substr(0, 4);
	                    ymd += "-";
	                    ymd += number.substr(4);
	                } else {
	                    ymd += number.substr(0, 4);
	                    ymd += "-";
	                    ymd += number.substr(4, 2);
	                    ymd += "-";
	                    ymd += number.substr(6);
	                }
					
	                obj.value = ymd;
	            } else {
                	alert("숫자 이외의 값은 입력하실 수 없습니다.");
		            obj.value = obj.value.replace(/[^0-9 ^\-]/g,"");
	                return false;
	            }
	        } else {
            	return false;
		    }
		}
		
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
	<h1>자격증 등록</h1>
	<form action="/admin/addCertiPro" method="post">
		<h2>필수정보</h2>
		<table>
			<tr>
				<td>자격증(시험) 이름 </td>
				<td><input type="text" name="cname"></td>
			</tr>
			<tr>
				<td>자격증(시험) 종류</td>
				<td>
					<select name="category">
						<option value="국가기술">국가기술</option>
						<option value="공인민간">공인민간</option>
						<option value="어학">어학</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>자격증 등급 (민간/어학)</td>
				<td>
					<input type="text" name="clevel"/>
				</td>
			</tr>
			<tr>
				<td>자격증 타입 (국가기술)</td>
				<td>
					<select name="ctype">
						<option value="">==선택==</option>
						<option value="기능장">기능장</option>
						<option value="기능사">기능사</option>
						<option value="기사">기사</option>
						<option value="산업기사">산업기사</option>
						<option value="기술사">기술사</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>자격증 회차 (국가기술)</td>
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
		<hr>
		<h2>상세정보</h2>
		<table>
			<tr>
				<td>시행기관(접수처)</td>
				<td><input type="text" name="company"/></td>
			</tr>
			<tr>
				<td>응시자격</td>
				<td><input type="text" name="requirement"/></td>
			</tr>
			<tr>
				<td>응시료</td>
				<td><input type="text" name="price"/></td>
			</tr>
			<tr>
				<td>시험방식</td>
				<td>
					<input type="text" name="cmethod"/>
					<div>(예시) 객관식, 주관식, 서술형 etc</div>
				</td>
			</tr>
			<tr>
				<td>시험과목</td>
				<td><input type="text" name="subject"/></td>
			</tr>
			<tr>
				<td>합격기준</td>
				<td><input type="text" name="cutline"/></td>
			</tr>
			<tr>
				<td>자격증 정보(개요)</td>
				<td><textarea name="cinfo"></textarea></td>
			</tr>
			<tr>
				<td>관련직업 / 진로(전망)</td>
				<td><input type="text" name="cjob"/></td>
			</tr>
			<tr>
				<td>시행기관 웹사이트</td>
				<td><input type="text" name="website"/></td>
			</tr>
			<tr>
				<td>NCS코드</td>
				<td><input type="text" name="ncs_cat"/></td>
			</tr>
			<tr>
				<td>비고</td>
				<td><textarea name="notice"></textarea></td>
			</tr>
		</table>
		<input type="submit" value="등록"/>
	</form>
</body>
