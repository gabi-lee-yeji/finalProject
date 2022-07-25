<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://cdn.jsdelivr.net/npm/chart.js@3.5.1/dist/chart.min.js"></script>

<canvas id="chart" width="100px" height="100px"></canvas>

<script>
	var chartArea = document.getElementById('chart').getContext('2d');
	var chart = new Chart(chartArea, {
		type: 'line',
		data: {
			labels: [
				<c:forEach var="d" items="${data}">
					${d.cyear},
				</c:forEach>
			],
			datasets: [{
				label: '${data[0].cname}',
				data: [
					<c:forEach var="d" items="${data}">
						<c:if test="${cnum.charAt(0) == 78}">
							${d.prac_pass/d.prac_apply},
						</c:if>
						<c:if test="${cnum.charAt(0) != 78}">
							${d.passed/d.tested},
						</c:if>
					</c:forEach>
				]
			}]
		},
		options:{
			scales:{
				y:{
					beginAtZero: true
				}
			}
		}
	});
</script>

${data}