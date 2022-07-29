<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<title>modifyForm</title>
<c:if test="${result == 1}">
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>
	
function execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("extraAddress").value = extraAddr;
            
            } else {
                document.getElementById("extraAddress").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('postcode').value = data.zonecode;
            document.getElementById("Address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("detailAddress").focus();
        }
    }).open();
}
function Check(){
    var email = RegExp(/^[A-Za-z0-9]+$/)
    var id= RegExp(/^[a-zA-Z0-9]+$/)
    var pass= RegExp(/^[a-zA-Z0-9]+$/)
    var named= RegExp(/^[가-힣]+$/)
    var fmt = RegExp(/^\d{6}[1234]\d{6}$/)  //포멧 설정
    var phone1 = RegExp(/^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})$/)
    var phone2 = RegExp(/^[0-9]{3,4}$/)
    var phone3 = RegExp(/^[0-9]{4}$/)
    var memid = document.getElementById("memid").value;

    //아이디 비밀번호 같음 확인
       if($("#memid").val() == $("#passwd").val()){
         alert("아이디와 비밀번호를 같게 할 수 없습니다.");
         $("#passwd").val("");
         $("#passwd2").val("");
         $("#passwd").focus();
          return false;
    }
     if($("#passwd").val() != ""){
    	 
	     //비밀번호 유효성검사
	     if(!pass.test($("#passwd").val())){
	         alert("형식에 맞게 입력해주세요");
	         $("#passwd").val("");
	         $("#passwd").focus();
	          return false;
	    }
    	 if($("#passwd").val().length < 4 || $("passwd").val().length > 12){
    		alert("비밀번호는 4~12자 이내로 해주세요")
    		$("#passwd").val("");
	        $("#passwd2").val("");
	        $("#passwd").focus();
    		return false;
    	 }
     
	    //비밀번호 서로확인
	     if($("#passwd").val() != $("#passwd2").val()){
	         alert("비밀번호가 일치하지 않습니다.");
	         $("#passwd").val("");
	         $("#passwd2").val("");
	         $("#passwd").focus();
	         return false;
	    }
    }
   //이름 공백 검사
     if($("#mem_name").val() == ""){
          alert("이름을 입력해주세요");
          $("#mem_name").focus();
          return false;
     }
     //이름 유효성 검사
     if(!named.test($("#mem_name").val())){
          alert("이름은 한글만 사용하실 수 있습니다.")
          $("#mem_name").val("");
          $("#mem_name").focus();
          return false;
     }
    //이메일 공백 확인
     if($("#userEmail1").val() == ""){
         alert("이메일을 입력하시기 바랍니다");
         $("#userEmail1").focus();
         return false;
    }
    //이메일 유효성 검사
    if(!email.test($("#userEmail1").val())){
         alert("이메일형식에 맞게 입력해주세요")
         $("#userEmail1").val("");
         $("#userEmail1").focus();
         return false;
    }//우편번호 유효성 검사
    if($("#postcode").val() == ""){
    	alert("우편번호를 입력해주세요.")
    	return false;
    }//주소 유효성 검사
    if($("#Address").val() == ""){
    	alert("주소를 입력해주세요.")
    	return false;
    }
    if($("#detailAddress").val() == ""){
    	alert("상세주소를 입력해주세요.")
    	return false;
    }
    if(!email.test($("#userEmail1").val())){
        alert("이메일형식에 맞게 입력해주세요")
        $("#userEmail1").val("");
        $("#userEmail1").focus();
        return false;
    }
    //번호 유효성 검사
    if($("#phone1").val() == ""){
    	alert("전화번호는 비워둘 수 없습니다.")
    	$("#phone1").focus();
    	return false;
    }
    if(!phone1.test($("#phone1").val())){
    	alert("전화번호를 정확히 입력해주세요.")
    	$("#phone1").val("");
    	$("#phone1").focus();
    	return false;
    }
    if($("#phone2").val() == ""){
    	alert("전화번호는 비워둘 수 없습니다.")
    	$("#phone2").focus();
    	return false;
    }
    if(!phone2.test($("#phone2").val())){
    	alert("전화번호를 정확히 입력해주세요.")
    	$("#phone2").val("");
    	$("#phone2").focus();
    	return false;
    }
    if($("#phone3").val() == ""){
    	alert("전화번호는 비워둘 수 없습니다.")
    	$("#phone3").focus();
    	return false;
    }
   	if(!phone3.test($("#phone3").val())){
   		alert("전화번호를 정확히 입력해주세요");
   		$("#phone3").val("");
   		$("#phone3").focus();
   		return false;
   	}
};
function noSpaceForm(obj){
	var str_space = /\s/;
	if(str_space.exec(obj.value)){
		alert("공백을 사용할 수 없습니다");
		obj.focus();
		obj.value = obj.value.replace(' ','');
		return false;
	}
}	
</script>

<jsp:include page="../userNavBar.jsp"></jsp:include>
<jsp:include page="../mypage/sidebar.jsp"></jsp:include>
<div style="margin-left:230px">
	
	<h1>수정폼</h1>
	<form action="/member/modifyPro" method="post" name="frm" onSubmit="return Check()">
				아이디 : ${dto.memid} <input type="hidden" name="memid" id="memid" value="${dto.memid}"/><br/>
				새 비밀번호 : <input type="password" name="passwd" id="passwd" placeholder="새 비밀번호" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/><br/>
				비밀번호 확인 :	<input type="password" name="passwd2" id="passwd2" placeholder="비밀번호 확인"/><br/>
				이름 : <input type="text" name="mem_name" id="mem_name" value="${dto.mem_name}" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/><br/>
				이메일 : <input type="text" name="mail1" id="userEmail1" value="${dto.mail1}" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/>@
				<input type="text" name="mail2" id="userEmail2" value="${dto.mail2}" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/><br/>
				생일 : ${dto.birthday} <br/>
				성별 <br/>
				남<input type="radio" name="gender" value="M" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);" checked/>
				여<input type="radio" name="gender" value="F" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/><br/>
				<input type="text" id="postcode" name="postalCode" value="${dto.postalCode}" placeholder="우편번호">
				<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기"><br>
				<input type="text" id="Address" name="address" value="${dto.address}" placeholder="주소"><br>
				<input type="text" id="detailAddress" name="addr_detail" value="${dto.addr_detail}" placeholder="상세주소"><br/>
				<input type="hidden" id="extraAddress" name="extraAddress" placeholder="참고항목"><br/> <!-- 일단 히든처리해놨음 -->
				전화번호 : <select name="pC">
					<option value="${pC}">${pC}</option>
						</select>
				<input type="text" name="phone1" id="phone1" value="${dto.phone1}" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);" />-
				<input type="text" name="phone2" id="phone2" value="${dto.phone2}" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);" />-
				<input type="text" name="phone3" id="phone3" value="${dto.phone3}" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);" /><br/>
				<input type="submit" value="수정완료"/>
		
	</form>
</div>
</c:if>
<c:if test="${result != 1}">
	<c:redirect url="/member/loginForm"/>
</c:if>
<jsp:include page="../footer.jsp"></jsp:include>