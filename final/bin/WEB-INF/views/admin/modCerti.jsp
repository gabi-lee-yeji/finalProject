<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
	<title>자격증 수정 : ${info.cname}</title>
</head>

<form action="/admin/modCertiPro" method="post">
	<table>
		<tr>
			<td>자격증 번호</td>
			<td>
				${cnum }
				<input type="hidden" name="cnum" value="${cnum }">
			</td>
		</tr>
		<tr>
			<td>자격증 이름</td>
			<td><input type="text" name="cname" value="${info.cname }"></td>
		</tr>
		<tr>
			<td>자격증(시험) 종류</td>
			<td>
				<input type="text" name="category" value="${info.category }" disabled/>
				*시험 종류 변경이 필요한 경우, 권한 필요 : 
				<a href="/admin/cert/chgCate">수정 요청하기</a> 
			</td>
		</tr>
	</table>
	<c:if test="${info.category == '국가기술'}">
		<table>
			<tr>
				<td>자격증 소분류</td>
				<td>
					<select name="ctype">
						<option value="${info.ctype}">==${info.ctype }==</option>
						<option value="기술사">기술사</option>
						<option value="기사">기사</option>
						<option value="산업기사">산업기사</option>
						<option value="기능사">기능사</option>
						<option value="기능장">기능장</option>
					</select>
				</td>		
			</tr>
			<tr>
				<td>시행연도</td>
				<td><input type="text" name="cyear" value="${info.cyear }"/></td>
			</tr>
			<tr>
				<td>자격증 회차 (국가기술)</td>
				<td><input type="text" name="cround" value="${info.cround }"/></td>
			</tr>
			<tr>
				<td>필기시험 접수기간</td>
				<td>
					<input type="text" name="docRegStart1" value="${qnet.docRegStart1 }" readonly/>
					~ <input type="text" name="docRegEnd1" value="${qnet.docRegEnd1 }" readonly/>
				</td>
			</tr>
			<tr>
				<td>필기시험 추가접수기간</td>
				<td>
					<input type="text" name="docRegStart2" value="${qnet.docRegStart2 }" readonly/>
					~ <input type="text" name="docRegEnd2" value="${qnet.docRegEnd2 }" readonly/>
				</td>
			</tr>
			<tr>
				<td>필기시험 응시기간</td>
				<td>
					<input type="text" name="docTestStart" value="${qnet.docTestStart }" readonly/>
					~ <input type="text" name="docTestEnd" value="${qnet.docTestEnd }" readonly/>
				</td>
			</tr>
			<tr>
				<td>필기시험 합격자 발표</td>
				<td>
					<input type="text" name="docResult" value="${qnet.docResult }" readonly/>
				</td>
			</tr>
			<tr>
				<td>응시자격 증명서류 제출기간</td>
				<td>
					<input type="text" name="docSubmitStart" value="${qnet.docSubmitStart }" readonly/>
					~ <input type="text" name="docSubmitEnd" value="${qnet.docSubmitEnd }" readonly/>
				</td>
			</tr>
			<tr>
				<td>실기시험 접수기간</td>
				<td>
					<input type="text" name="pracRegStart1" value="${qnet.pracRegStart1 }" readonly/>
					~ <input type="text" name="pracRegEnd1" value="${qnet.pracRegEnd1 }" readonly/>
				</td>
			</tr>
			<tr>
				<td>실기시험 추가접수기간</td>
				<td>
					<input type="text" name="pracRegStart2" value="${qnet.pracRegStart2 }" readonly/>
					~ <input type="text" name="pracRegEnd2" value="${qnet.pracRegEnd2 }" readonly/>
				</td>
			</tr>
			<tr>
				<td>실기시험 응시기간</td>
				<td>
					<input type="text" name="pracTestStart" value="${qnet.pracTestStart }" readonly/>
					~ <input type="text" name="pracTestEnd" value="${qnet.pracTestEnd }" readonly/>
				</td>
			</tr>
			<tr>
				<td>실기시험 합격자 발표기간</td>
				<td>
					<input type="text" name="pracResStart" value="${qnet.pracResStart }" readonly/>
					~ <input type="text" name="pracResEnd" value="${qnet.pracResEnd }" readonly/>
				</td>
			</tr>
		</table>
	</c:if>
	<c:if test="${info.category != '국가기술'}">
		<table>
			<tr>
				<td>원서접수기간</td>
				<td>
					<input type="text" name="regStart" value="${info.regStart }" /> 
					<c:if test="${info.regStartTime != null }">
						<input type="text" name="regStartTime" value="${info.regStartTime }">
					</c:if>
					~ 
					<input type="text" name="regEnd" value="${info.regEnd }" />
					<c:if test="${info.regStartTime != null }">
						<input type="text" name="regEndTime" value="${info.regEndTime }">
					</c:if>
				</td>
			</tr>
			<tr>
				<td>추가접수기간</td>
				<td>
					<input type="text" name="reg_addStart" value="${info.reg_addStart }" />
					~ <input type="text" name="reg_addEnd" value="${info.reg_addEnd }" />
				</td>
			</tr>
			<tr>
				<td>시험일자</td>
				<td>
					<input type="text" name="testDate" value="${info.testDate }" />
				</td>
			</tr>
			<tr>
				<td>결과 발표일</td>
				<td>
					<input type="text" name="resDate" value="${info.resDate }" />
				</td>
			</tr>
		</table>
	</c:if>
	<input type="submit" value="수정"/>
</form>
