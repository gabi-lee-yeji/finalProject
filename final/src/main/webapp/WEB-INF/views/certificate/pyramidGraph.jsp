<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://cdn.jsdelivr.net/npm/chart.js@3.5.1/dist/chart.min.js"></script>

<canvas id="pchart" width="100px" height="100px"></canvas>

<script>
	const tooltip = {
			yAlign: 'bottom',
			titleAlign: 'center',
			callbacks: {
				label: function(context){
					return `${context.dataset.label} ${context.raw}`;
				}
			}
		}
	const female = [${data.f60},${data.f50},${data.f40},${data.f30},${data.f20},${data.f10}];
	const femaleData = [];
	female.forEach(element => femaleData.push(element*-1));

	var pchartArea = document.getElementById('pchart').getContext('2d');
	var pchart = new Chart(pchartArea, {
		type: 'bar',
		data: {
			labels: ['60+', '50', '40','30','20','10'],
			datasets: [{
				label: '남',
				data: [
					${data.m60},${data.m50},${data.m40},${data.m30},${data.m20},${data.m10}
				],
				backgroundColor: 'rgba(54,162,235,1)',
				borderColor: 'rgba(54,162,235,1)',
				borderWidth: 1,
				borderRadius: 5
			},{
				label: '여',
				data: femaleData,
				backgroundColor: 'rgba(255,26,104,1)',
				borderColor: 'rgba(255,26,104,1)',
				borderWidth: 1,
				borderRadius: 5
			}]
		},
		options: {
			indexAxis: 'y',
			scales:{
				x:{
					stacked: true,
					ticks:{
						callback: function(value, index,values){
							return Math.abs(value);
						}
					}
				},
				y:{
					beginAtZero: true,
					stacked: true
				}
			},
			plugins:{
				tooltip,
			}
		}
	});
</script>


${data}<br>
${data.m10}