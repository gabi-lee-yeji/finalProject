<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<table class="table table-bordered">
		<c:forEach var="dto" items="${list }">
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
		</c:forEach>
	</table>