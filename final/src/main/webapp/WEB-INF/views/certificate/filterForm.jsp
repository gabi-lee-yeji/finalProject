<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<form name="frm" action="/certificate/certiMain" method="post" >
	<h3>분야</h3>
		<input type="checkbox" name="NCS_CAT" value=""> 기계ㆍ금속ㆍ화공 <br/>
		<input type="checkbox" name="NCS_CAT" value=""> 전기ㆍ전자ㆍ통신 <br/>
		<input type="checkbox" name="NCS_CAT" value=""> 조선ㆍ항공 <br/>
		<input type="checkbox" name="NCS_CAT" value=""> 토목ㆍ건축 <br/>
		<input type="checkbox" name="NCS_CAT" value=""> 에너지ㆍ환경ㆍ안전 <br/>
		<input type="checkbox" name="NCS_CAT" value=""> 산업디자인ㆍ응용ㆍ전문사무 <br/>
		<input type="checkbox" name="NCS_CAT" value=""> 금속ㆍ화공 <br/>
		<input type="checkbox" name="NCS_CAT" value=""> 섬유ㆍ해양ㆍ음식료품 <br/>
		<input type="checkbox" name="NCS_CAT" value=""> 의약 <br/>
	<h3>자격등급</h3>	
		<input type="checkbox" name="clevel" value="기술사"> 기술사 <br/>
		<input type="checkbox" name="clevel" value="기능장"> 기능장 <br/>
		<input type="checkbox" name="clevel" value="기사"> 기사 <br/>
		<input type="checkbox" name="clevel" value="산업기사"> 산업기사 <br/>
		<input type="checkbox" name="clevel" value="기능사"> 기능사 <br/>	

<h2>응시자격</h2>
	<h3>나이</h3>
		<input type="checkbox" name="req_age" value="">30대 이상<br/>
		<input type="checkbox" name="req_age" value="">20대<br/>
		<input type="checkbox" name="req_age" value="">10대<br/>
	<h3>학력</h3>
		<input type="checkbox" name="req_degree" value="대졸">4년제 졸업 이상<br/>
		<input type="checkbox" name="req_degree" value="초대졸">2년제 졸업 이상<br/>
		<input type="checkbox" name="req_degree" value="고졸">고등학교 졸업 이상<br/>
		<input type="checkbox" name="req_degree" value="학력무관">학력 무관<br/>
	<h3>경력</h3>
		<input type="checkbox" name="req_exp" value="4년이상">4년 이상<br/>
		<input type="checkbox" name="req_exp" value="2년이상">2년 이상<br/>
		<input type="checkbox" name="req_exp" value="1년이상">1년 이상<br/>
		<input type="checkbox" name="req_exp" value="경력무관">경력 무관<br/>
		
<h2>기간</h2>	
	<input type="checkbox" name="date" value=""> <br/>
	<input type="checkbox" name="date" value=""> <br/>
	<input type="checkbox" name="date" value=""> <br/>

<input type="submit" value="적용"/>	
</form>