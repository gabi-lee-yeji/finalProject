<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<title>signUpForm</title>
<h1>회원가입</h1>

<script>
	function fn_submit(){
		var text = document.getElementById('phone').value;
		var regPhone = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
		if(regPhone.test(phone)===true){
			alert("입력된 값은 휴대전화번호입니다")
		}
	}
	
function noSpaceForm(obj){
	var str_space = /\s/;
	if(str_space.exec(obj.value)){
		alert("공백을 사용할 수 없습니다");
		obj.focus();
		obj.value = obj.value.replace(' ','');
		return false
	}
}	
	
	
</script>

<form action="/member/signUpPro" method="post" name="memberInput">
			아이디 : <input type="text" name="memid" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/> <br/>
			비밀번호 : <input type="password" name="passwd" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/><br/>
			이름 : <input type="text" name="mem_name"  onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/>
			이메일 : <input type="text" name="mail1" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/>@
			<input type="text" name="mail2" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/><br/>
			생일 : <input type="date" name="birthday" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/><br/>
			성별 <br/>
			남<input type="radio" name="gender" value="남" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/>
			여<input type="radio" name="gender" value="여" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/><br/>
			우편번호 : <input type="text" name="postalCode" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/><br/>
			주소 : <input type="address" name="address" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/><br/>
			상세주소 : <input type="text" name="addr_detail" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/><br/>
			전화번호 : <select name="pC">
				<option>SKT</option>
				<option>KT</option>
				<option>LG</option>
				<option>알뜰폰</option>
			</select>
			<input type="textbox" name="mobile" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);" /><br/>
			학위 : <input type="text" name="mem_degree" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/><br/>
			전공 : <input type="text" name="major" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/><br/>
			직업 : <input type="text" name="mem_job" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/><br/>
			<input type="submit" value="로그인"/>
	
</form>