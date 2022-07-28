<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<head>
	<meta charset="UTF-8">
	<title>자격증 정보 등록</title>
	
	<script>
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
	<h1 style="margin-left:50px">자격증 등록</h1>
	<form action="/admin/addCertiPro" method="post" >
		<div class="row">
			<div class="col" style="margin-left:50px">
				<h2>필수정보</h2>
				<table>
					<tr>
						<td>자격증(시험) 이름 </td>
						<td><input type="text" name="cname" class="form-control"></td>
					</tr>
					<tr>
						<td>자격증(시험) 종류</td>
						<td>
							<select name="category" class="form-control">
								<option value="national">국가기술</option>
								<option value="private">공인민간</option>
								<option value="language">어학</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>자격증 등급</td>
						<td>
							<input type="text" name="clevel" class="form-control"/>
						</td>
					</tr>
					<tr>
						<td>시행기관(접수처)</td>
						<td><input type="text" name="company" class="form-control"/></td>
					</tr>
					<tr>
						<td>시행기관 웹사이트</td>
						<td><input type="text" name="website" class="form-control"/></td>
					</tr>
					<tr>
						<td>응시료</td>
						<td><input type="text" name="price" class="form-control"/></td>
					</tr>
					<tr>
						<td>공인 유효날짜</td>
						<td>
							<input type="text" name="docResultStart" class="form-control" onKeypress="dateFormat(this);" onChange="checkDate(this);" placeholder="yyyy-MM-dd" maxlength="10">
						</td>
					</tr>
				</table>
			</div>
			<div class="col" style="margin-right:50px">
				<h2>응시자격</h2>
				<table>
					<tr>
						<td>학력</td>
						<td>
							<input type="radio" name="req_degree" value="고졸" /> 고졸 
							<input type="radio" name="req_degree" value="전문학사"/> 전문학사 
							<input type="radio" name="req_degree" value="학사"/> 학사 <br>
							<input type="radio" name="req_degree" value="석사"/> 석사 
							<input type="radio" name="req_degree" value="박사"/> 박사 
							<input type="radio" name="req_degree" value="기타"/> 기타 
						</td>
					</tr>
					<tr>
						<td>나이</td>
						<td><input type="text" name="req_age" class="form-control"/></td>
					</tr>
					<tr>
						<td>경력</td>
						<td>
							<input type="text" name="req_exp" class="form-control"/>
						</td>
					</tr>
					<tr>
						<td></td>
						<td>
							(eg) 관련분야 종사 N년 이상
						</td>
					</tr>
					<tr>
						<td>전제조건</td>
						<td>
							<input type="text" name="pre_requisite" class="form-control"/>
						</td>
					</tr>
					<tr>
						<td></td>
						<td>
							(eg) 1급 위해 2급 자격증 필요한 경우 등
						</td>
					</tr>
					<tr>
						<td>참고사항</td>
						<td><textarea name="ref" class="form-control"></textarea></td>
					</tr>
				</table>
			</div>
		</div>
		<hr>
		<div class="row">
			<div class="col" style="margin-left:50px">
				<h2>상세정보</h2>
				<table>
					<tr>
						<td>시험방식</td>
						<td>
							<input type="text" name="cmethod" class="form-control"/>
							(예시) 객관식, 주관식, 서술형 etc
						</td>
					</tr>
					<tr>
						<td>시험과목</td>
						<td><input type="text" name="subject" class="form-control"/></td>
					</tr>
					<tr>
						<td>합격기준</td>
						<td><input type="text" name="cutline" class="form-control"/></td>
					</tr>
					<tr>
						<td>자격증 정보(개요)</td>
						<td><textarea class="form-control" rows="5" name="cinfo"></textarea></td>
					</tr>
					<tr>
						<td>관련직업 / 진로(전망)</td>
						<td><input type="text" name="cjob" class="form-control"/></td>
					</tr>
					
					<tr>
						<td>NCS코드</td>
						<td><input type="text" name="ncs_cat" class="form-control"/></td>
					</tr>
					<tr>
						<td>비고</td>
						<td><textarea class="form-control" rows="5" name="notice"></textarea></td>
					</tr>
				</table>
			</div>
			<div class="col" style="margin-right:50px">
				<details>
					<summary>시험일정 추가</summary>
					<h2>시험 일정</h2>
					<table>
						<tr>
							<td>자격증 회차</td>
							<td><input type="text" name="cround" class="form-control"></td>
						</tr>
						<tr>
							<td>필기 원서접수</td>
							<td>
								<input type="text" name="docRegStart1" class="form-control" onKeypress="dateFormat(this);" onblur="checkDate(this);" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
								~ <input type="text" name="docRegEnd1" class="form-control" onKeypress="dateFormat(this);" onblur="checkDate(this);" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
							</td>
						</tr>
						<tr>
							<td>필기원서 - 추가접수</td>
							<td>
								<input type="text" name="docRegStart2" class="form-control" onKeypress="dateFormat(this)" onblur="checkDate(this);" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
								~ <input type="text" name="docRegEnd2" class="form-control" onKeypress="dateFormat(this)" onblur="checkDate(this);" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
							</td>
						</tr>
						<tr>
							<td>필기시험</td>
							<td>
								<input type="text" name="docTestStart" class="form-control" onKeypress="dateFormat(this)" onblur="checkDate(this);" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
								~ <input type="text" name="docTestEnd" class="form-control" onKeypress="dateFormat(this)" onblur="checkDate(this);" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
							</td>
						</tr>
						<tr>
							<td>필기-합격자발표</td>
							<td>
								<input type="text" name="docResultStart" class="form-control" onKeypress="dateFormat(this)" onblur="checkDate(this);" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
								~ <input type="text" name="docResultEnd" class="form-control" onKeypress="dateFormat(this)" onblur="checkDate(this);" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
							</td>
						</tr>
						<tr>
							<td>응시자격 서류제출</td>
							<td>
								<input type="text" name="docSubmitStart" class="form-control" onKeypress="dateFormat(this)" onblur="checkDate(this);" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
								~ <input type="text" name="docSubmitEnd" class="form-control"onKeypress="dateFormat(this)" onblur="checkDate(this);" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
							</td>
						</tr>
						<tr>
							<td>실기시험 원서 접수</td>
							<td>
								<input type="text" name="pracRegStart1" class="form-control" onKeypress="dateFormat(this)" onblur="checkDate(this);" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
								~ <input type="text" name="pracRegEnd1" class="form-control" onKeypress="dateFormat(this)" onblur="checkDate(this);" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
							</td>
						</tr>
						<tr>
							<td>실기시험 추가 접수</td>
							<td>
								<input type="text" name="pracRegStart2" class="form-control" onKeypress="dateFormat(this)" onblur="checkDate(this);" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
								~ <input type="text" name="pracRegEnd2" class="form-control" onKeypress="dateFormat(this)" onblur="checkDate(this);" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
							</td>
						</tr>
						<tr>
							<td>실기시험</td>
							<td>
								<input type="text" name="pracTestStart" class="form-control" onKeypress="dateFormat(this)" onblur="checkDate(this);" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
								~ <input type="text" name="pracTestEnd" class="form-control" onKeypress="dateFormat(this)" onblur="checkDate(this);" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
							</td>
						</tr>
						<tr>
							<td>실기 합격자 발표</td>
							<td>
								<input type="text" name="pracResStart" class="form-control" onKeypress="dateFormat(this)" onblur="checkDate(this);" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
								~ <input type="text" name="pracResEnd" class="form-control" onKeypress="dateFormat(this)" onblur="checkDate(this);" placeholder="yyyy-MM-dd HH:mm" maxlength="16">
							</td>
						</tr>
					</table>
				</details>
			</div>
		</div>
		<input type="submit" value="등록" class="btn btn-primary btn-lg" style="margin-right:50%; margin-top:20px; float:right"/>
	</form>
</body>
