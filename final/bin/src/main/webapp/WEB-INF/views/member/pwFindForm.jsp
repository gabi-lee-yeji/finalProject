<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h1>비밀번호찾기</h1>
<script>
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

<form action="pwFindPro" method="post">
	아이디 : <input type="text" name="memid"/><br/>
	문제를 선택 후 답을 입력하세요.<br/> 
				<select name="quiz1">
				<option value="1">어린시절 가장 친했던 친구의 이름은?</option>
				<option value="2">다시 만나고 싶은 친구의 이름은?</option>
			</select>
			<input type="text" name="findPw1" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/><br/>
			<select name="quiz2">
				<option value="3">기억에 남는 추억의 장소는?</option>
				<option value="4">어린시절 나의 별명은?</option>
			</select>
			<input type="text" name="findPw2" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/><br/>
			<input type="submit" value="확인"/>
</form>

<a href="/member/signUpForm">회원 가입</a>
	<a href="/member/idFindForm">아이디 찾기</a>
