<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<table>
	<tr><th>신규가입자</th></tr>
	<tr>
		<td>오늘 : </td>
		<td>${map.memberToday}</td>
	</tr>
	<tr>
		<td>지난 일주일 간 : </td>
		<td>${map.memberLastWeek}</td>
	</tr>
</table>