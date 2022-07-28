<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<canvas id="myChart"></canvas>

<script>
	// data convert
	const maleData = [${data.m60},${data.m50},${data.m40},${data.m30},${data.m20},${data.m10}];
	const female = [${data.f60},${data.f50},${data.f40},${data.f30},${data.f20},${data.f10}];
	const femaleData = [];
	female.forEach(element => femaleData.push(element*-1));

	// setup 
	const data = {
		labels: ['60대+', '50대', '40대', '30대', '20대', '10대'],
		datasets: [{
			label: '남성',
			data: maleData,
			backgroundColor:'rgba(54, 162, 235, 1)',
			borderColor: 'rgba(54, 162, 235, 1)',
			borderWidth: 1
		},{
			label: '여성',
			data: femaleData,
			backgroundColor: 'rgba(255, 26, 104, 1)',
			borderColor: 'rgba(255, 26, 104, 1)',
			borderWidth: 1
		}]
	};

	// block tooltip
	const tooltip = {
		yAlign: 'bottom',
		titleAlign: 'center',
		callbacks:{
			label: function(context){
				return Math.abs(context.raw);
			}
		}
	};
	
	
	// config 
	const config = {
		type: 'bar',
		data,
		options: {
 			indexAxis: 'y',
			scales: {
				x: {
					stacked: true,
					ticks:{
						callback: function(value,index,values){
							if(Math.abs(value) > xmax)
								var xmax = Math.abs(value);
							return Math.abs(value);
						}
					},
					max: Math.ceil(Math.max(...maleData, ...femaleData)/Math.pow(10,Math.floor(Math.log10(Math.max(...maleData, ...femaleData)))))*Math.pow(10,Math.floor(Math.log10(Math.max(...maleData, ...femaleData)))),
					min: -Math.ceil(Math.max(...maleData, ...femaleData)/Math.pow(10,Math.floor(Math.log10(Math.max(...maleData, ...femaleData)))))*Math.pow(10,Math.floor(Math.log10(Math.max(...maleData, ...femaleData))))
				},
				y: {
					beginAtZero: true,
					stacked: true
				}
			},
			plugins:{
				tooltip: tooltip
			}
		}
	};

	// render init block
	const myChart = new Chart(
		document.getElementById('myChart'),
		config
	);
</script>
