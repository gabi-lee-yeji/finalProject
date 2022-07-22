<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h1>id찾기</h1>

<form action="/member/idFindPro" method="post">
	이  름: 	<input type="text" name="mem_name" /> <br />
	전화번호:	<select name="pC">
				<option>SKT</option>
				<option>KT</option>
				<option>LGU+</option>
				<option>알뜰폰</option>
			</select>
			<input type="text" name="phone1" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/>
			-
			<input type="text" name="phone2" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/>
			-
			<input type="text" name="phone3" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/><br/>
			<input type="submit" value="아이디 찾기" />
</form>