<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
	<meta charset="UTF-8">
	<title>자격증 정보 - ${info.cname }</title>
	<script async src="https://www.googletagmanager.com/gtag/js?id=UA-233548942-1"></script>
	<script language="JavaScript" src="/resources/js/gtag.js"></script>
</head>
<body>
	<h2>자격증 정보</h2>
	<input type="button" value="자격증 목록" onclick="window.location='/admin/certiList'">
	<form action="/admin/modCertiPro?cnum=${cnum }">
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
			<tr>
				<td>등급</td>
				<td>
					<input type="text" name="category" value="${info.clevel }" />
				</td>
			</tr>
			<tr>
				<td>시행기관</td>
				<td>
					<input type="text" name="company" value="${info.company }" />
				</td>
			</tr>
			<tr>
				<td></td>
				<td>
					<a href="${info.website}">${info.website }</a> <br>
					<input type="text" name="website" value="${info.website }"/>
				</td>
			</tr>
			<tr>
				<td>응시료</td>
				<td><input type="text" name="price" value="${info.price }"/></td>
			</tr>
			<tr>
				<td>상세일정확인</td>
				<td>
					<a href="/admin/certiDate?cnum=${info.cnum }&cname=${info.cname}">${info.cname } 상세일정</a>
				</td>
			</tr>
		</table>
		<details>
			<summary>상세정보 확인</summary>
			<table>
				<tr>
					<td>검정방법</td>
					<td>
						<input type="text" name="cmethod" value="${info.cmethod }" />
					</td>
				</tr>
				<tr>
					<td>검정과목</td>
					<td>
						<input type="text" name="subject" value="${info.subject }" />
					</td>
				</tr>
				<tr>
					<td>합격기준</td>
					<td><input type="text" name="cutline" value="${info.cutline }"/></td>
				</tr>
				<tr>
					<td>자격증 정보(개요)</td>
					<td><textarea name="cinfo" >${info.cinfo }</textarea></td>
				</tr>
				<tr>
					<td>관련직업 / 진로(전망)</td>
					<td><input type="text" name="cjob" value="${info.cjob }"/></td>
				</tr>
				
				<tr>
					<td>NCS코드</td>
					<td><input type="text" name="ncs_cat" value="${info.ncs_cat }"/></td>
				</tr>
				<tr>
					<td>비고</td>
					<td><textarea name="notice">${info.notice }</textarea></td>
				</tr>
			</table>
		</details>
		<details>
			<summary>응시자격 확인</summary>
			<table>
				<tr>
					<td>학력</td>
					<td>
						<select name="req_degree">
							<option value="${req.req_degree}">==${req.req_degree}==</option>
							<option value="고졸"> 고졸 </option>
							<option value="전문학사"> 전문학사 </option>
							<option value="학사"> 학사 </option>
							<option value="석사"> 석사 </option>
							<option value="박사"> 박사 </option>
							<option value="기타"> 기타 </option>
						</select>
					</td>
				</tr>
				<tr>
					<td>나이</td>
					<td><input type="text" name="req_age" value="${req.req_age }"/></td>
				</tr>
				<tr>
					<td>경력</td>
					<td>
						<input type="text" name="req_exp" value="${req.req_exp }"/><br>
						(eg) 관련분야 종사 N년 이상
					</td>
				</tr>
				<tr>
					<td>전제조건</td>
					<td>
						<input type="text" name="pre_requisite" value="${req.pre_requisite }"/> <br>
						(eg) 1급위해 2급 자격증 필요한 경우 등
					</td>
				</tr>
				<tr>
					<td>참고사항</td>
					<td><textarea name="ref" >${req.ref}</textarea></td>
				</tr>
			</table>
		</details>
		<input type="submit" value="수정">
	</form>
	<input type="button" value="자격증 삭제" onclick="window.location='certi/deleteForm?cnum=${cnum}'">
</body>
