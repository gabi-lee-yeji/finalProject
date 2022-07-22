<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<table class="table">
	<thead class="thead-dark">
		<tr><th colspan=2>신규가입자</th></tr>
	</thead>
	<tr>
		<td>오늘 : </td>
		<td>${map.memberToday}</td>
	</tr>
	<tr>
		<td>지난 일주일 간 : </td>
		<td>${map.memberLastWeek}</td>
	</tr>
</table>