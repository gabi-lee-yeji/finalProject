<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="dto" items="${list }">
	<div style="width:80%;">
		<table border="1" style="width:100%;">
			<tr>
				<td>
					${dto.press }
				</td>
				<td>
					${dto.info }
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<a href="${dto.link }" target="_blank" rel="noopener noreferrer">
						${dto.title }
					</a>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<a href="${dto.link }" target="_blank" rel="noopener noreferrer">
						${dto.content }
					</a>
				</td>
			</tr>
			
		</table>
	</div>
</c:forEach>