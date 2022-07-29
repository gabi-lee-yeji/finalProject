<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
	function checkYes(){
	const checkAll = document.getElementById("checkAll");
	const ckb = document.getElementsByName("ckb");
	if(checkAll.checked == true){
		for(var i=0;i<ckb.length;i++){
			ckb[i].checked = true
		}
	}else{
		for(var i=0;i<ckb.length;i++){
			ckb[i].checked = false
		}	
	}
}
</script>
<jsp:include page="../userNavBar.jsp"></jsp:include>
<div align="center">
<form action="signUpForm" name="frm" id="frm" method="post">
		<input type="checkbox" name="checkAll" id="checkAll" onclick="checkYes();"/>
		<b><label for="checkAll">전체 동의</label></b><br/>
		<hr align="center" style="border: solid 2px green; width:500px;">
		
		<div style="overflow:scroll;width:500px;height:200px">
<b>㈜자격증모두모아</b> 서비스를 이용하시거나 서비스 회원으로 가입하실 경우 여러분은 본 약관 및 관련 운영 정책을 확인하거나 동의하게 되므로, 잠시 시간을 내시어 주의 깊게 살펴봐 주시기 바랍니다.		
여러분은 본 약관을 읽고 동의하신 후 회원 가입을 신청하실 수 있으며, <b>㈜자격증모두모아</b>는 이에 대한 승낙을 통해 회원 가입 절차를 완료하고 여러분께 <b>㈜자격증모두모아</b> 서비스 이용 계정(이하 ‘계정’)을 부여합니다. 계정이란 회원이 서비스에 로그인한 이후 이용하는 각종 서비스 이용 이력을 회원 별로 관리하기 위해 설정한 회원 식별 단위를 말합니다. 회원은 자신의 계정을 통해 좀더 다양한 서비스를 보다 편리하게 이용할 수 있습니다
		
회원은 언제든지 서비스 이용계약 해지를 신청하여 회원에서 탈퇴할 수 있으며, 이 경우 관련 법령 등이 정하는 바에 따라 이를 지체 없이 처리하겠습니다.

서비스 이용계약이 해지되면, 관련 법령 및 개인정보처리방침에 따라 회사가 해당 회원의 정보를 보유할 수 있는 경우를 제외하고, 해당 회원 계정에 부속된 게시물 일체를 포함한 회원의 모든 데이터는 소멸됨과 동시에 복구할 수 없게 됩니다. 다만, 이 경우에도 다른 회원이 별도로 담아갔거나 스크랩한 게시물과 공용 게시판에 등록한 댓글 등의 게시물은 삭제되지 않으므로 반드시 해지 신청 이전에 삭제하신 후 탈퇴하시기 바랍니다.

여러분의 본의 아닌 불편이나 부담이 최소화될 수 있는 방법에 대해 항상 고민하고 개선해 나가겠습니다.

주요 사항을 잘 안내하고 여러분의 소중한 의견에 귀 기울이겠습니다.
여러분은 언제든지 고객센터를 통해 서비스 이용과 관련된 의견이나 개선사항을 전달할 수 있으며, 합리적 범위 내에서 가능한 그 처리과정 및 결과를 여러분께 전달할 수 있도록 하겠습니다.
		</div><br/>
		<input type="checkbox" name="ckb" id="ckb1" required/>
		<b><label for="ckb1">이용약관 동의(필수)</label></b>
		<br/><br/><br/>
		
		<hr align="center" style="border: solid 2px green; width:500px;">
		<div style="overflow:scroll;width:500px;height:200px">
		수집하는 개인정보
이용자는 회원가입을 하지 않아도 자격증 정보 검색, 자격증 시험 일정 등 대부분의 서비스를 회원과 동일하게 이용할 수 있습니다. 이용자가 회원 서비스를 이용하기 위해 회원가입을 할 경우, ㈜자격증모두모아(이하"회사")는 서비스 이용을 위해 필요한 최소한의 개인정보를 수집합니다.

회원가입 시점에 회사가 이용자로부터 수집하는 개인정보는 아래와 같습니다.
- 회원 가입 시에 ‘아이디, 비밀번호, 이름, 생년월일, 성별, 휴대전화번호 ,이메일주소,주소,학위’를 필수항목으로 수집합니다. 
		</div><br/>
		<input type="checkbox" name="ckb" id="ckb2" required/>
		<b><label for="ckb2">개인정보 수집 및 이용 동의(필수)</label></b><br/>
		<br/><input type="submit" value="회원가입으로"/>
</form>
</div>
<jsp:include page="../footer.jsp"></jsp:include>