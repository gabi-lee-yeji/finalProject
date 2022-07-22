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
		
		function checkDate(obj){
			var len = obj.value.length;
			
			var dateReg = RegExp(/^(20)\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$/);
			var timeReg = RegExp(/^(2[0-3]|[01][0-9]):[0-5][0-9]$/);
			
			if(len > 10 && len < 12){
				if(!dateReg.test(obj)){
					alert("올바른 날짜를 입력해주세요!");
					obj.value="";
					obj.focus();
				}
			}
			
			if(len > 15 ){
				if(!timeReg.test(obj.value.split(" ")[1])){
					alert("올바른 시간을 입력해주세요!");
					obj.value=obj.value.split(" ")[0];
					obj.focus();
				}
			}
		}
		
		
	</script>
</head>

<body>
	<jsp:include page="../adminNavBar.jsp"/>
	<h1>자격증 등록</h1>
	<form action="/admin/addCertiPro" method="post" >
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
						<option value="national">국가기술</option>
						<option value="private">공인민간</option>
						<option value="language">어학</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>자격증 등급</td>
				<td>
					<input type="text" name="clevel"/>
				</td>
			</tr>
			<tr>
				<td>시행기관(접수처)</td>
				<td><input type="text" name="company"/></td>
			</tr>
			<tr>
				<td>시행기관 웹사이트</td>
				<td><input type="text" name="website"/></td>
			</tr>
			<tr>
				<td>응시료</td>
				<td><input type="text" name="price"/></td>
			</tr>
			<tr>
				<td>공인 유효날짜</td>
				<td>
					<input type="text" name="docResultStart" onKeypress="dateFormat(this);" onChange="checkDate(this);" placeholder="yyyy-MM-dd" maxlength="10">
				</td>
			</tr>
		</table>
		<hr>
		<h2>시험 일정</h2>
		<table>
			<tr>
				<td>자격증 회차</td>
				<td><input type="text" name="cround"></td>
			</tr>
			<tr>
				<td>필기 원서접수</td>
				<td>
					<input type="text" name="docRegStart1" onKeypress="dateFormat(this);" onblur="checkDate(this);" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
					~ <input type="text" name="docRegEnd1" onKeypress="dateFormat(this);" onKeyUp="checkDate(this);" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
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
		<h2>응시자격</h2>
		<table>
			<tr>
				<td>학력</td>
				<td>
					<input type="radio" name="req_degree" value="고졸"/> 고졸 
					<input type="radio" name="req_degree" value="전문학사"/> 전문학사 
					<input type="radio" name="req_degree" value="학사"/> 학사 
					<input type="radio" name="req_degree" value="석사"/> 석사 
					<input type="radio" name="req_degree" value="박사"/> 박사 
					<input type="radio" name="req_degree" value="기타"/> 기타 
				</td>
			</tr>
			<tr>
				<td>나이</td>
				<td><input type="text" name="req_age"/></td>
			</tr>
			<tr>
				<td>경력</td>
				<td>
					<input type="text" name="req_exp"/><br>
					(eg) 관련분야 종사 N년 이상
				</td>
			</tr>
			<tr>
				<td>전제조건</td>
				<td>
					<input type="text" name="pre_requisite"/> <br>
					(eg) 1급위해 2급 자격증 필요한 경우 등
				</td>
			</tr>
			<tr>
				<td>참고사항</td>
				<td><textarea name="ref"></textarea></td>
			</tr>
		</table>
		<h2>상세정보</h2>
		<table>
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
