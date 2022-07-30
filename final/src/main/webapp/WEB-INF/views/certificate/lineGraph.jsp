<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<script src="https://cdn.jsdelivr.net/npm/chart.js@3.5.1/dist/chart.min.js"></script>

<c:if test="${fn:length(data) > 0 }">
	<canvas id="lchart" width="100px" height="100px"></canvas>
	
	<script>
		var lchartArea = document.getElementById('lchart').getContext('2d');
		var lchart = new Chart(lchartArea, {
			type: 'line',
			data: {
				labels: [
					<c:forEach var="d" items="${data}">
						${d.cyear},
					</c:forEach>
				],
				datasets: [{
					label: '${data[0].cname}'
						<c:if test="${cnum.charAt(0) == 78}">
							+ " 필기"
						</c:if>
					,
					data: [
						<c:forEach var="d" items="${data}">
							<c:if test="${cnum.charAt(0) == 78}">
								${d.doc_pass/d.doc_apply*100},
							</c:if>
							<c:if test="${cnum.charAt(0) != 78}">
								${d.passed/d.tested*100},
							</c:if>
						</c:forEach>
					],
					borderColor: 'green'
				},
					<c:if test="${cnum.charAt(0) == 78}">
						{
							label: '${data[0].cname}' + " 실기",
							data:[
								<c:forEach var="d" items="${data}">
									${d.prac_pass/d.prac_apply*100},
								</c:forEach>
							],
							borderColor: 'blue'
						}
					</c:if>
				]
			},
			options:{
				scales:{
					y:{
						beginAtZero: true,
						max: 100,
						ticks:{
							callback: function(value,index,values){
								return value + '%';
							}
						}
					}
				}
			}
		});
	</script>
</c:if>