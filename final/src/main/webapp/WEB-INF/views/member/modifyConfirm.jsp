<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="../userNavBar.jsp"></jsp:include>
<jsp:include page="../mypage/sidebar.jsp"></jsp:include>

<script>
function Check(){
		if(document.frm.passwd.value == ""){
			alert("비밀번호를 입력하세요")
			return false
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

<div style="margin-left:230px">
	<h1>수정 본인 확인</h1>
	<form action="modifyForm" name="frm" method="post" onSubmit="return Check();">
		아이디 : <input type="text" name="memid" value="${sessionScope.sid}" readonly/><br/>
		비밀번호 : <input type="password" name="passwd" onkeyup="noSpaceForm(this);"/><br/>
		<input type="submit" value="확인"/>
	</form>
</div>
<jsp:include page="../footer.jsp"></jsp:include>