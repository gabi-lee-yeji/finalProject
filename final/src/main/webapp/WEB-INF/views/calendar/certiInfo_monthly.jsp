<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset='utf-8' />
	<link href='/resources/fullcalendar/main.css' rel='stylesheet' />
	<script src='/resources/fullcalendar/main.js'></script>
	<script>
	
	  document.addEventListener('DOMContentLoaded', function() {
	    var calendarEl = document.getElementById('calendar');
	
	    var calendar = new FullCalendar.Calendar(calendarEl, {
	      initialDate: new Date(),
	      editable: false,
	      selectable: true,
	      businessHours: true,
	      dayMaxEvents: true, // allow "more" link when too many events
	      events: [
	    	  <c:forEach var="dto" items="${certiList}">
				{
		          title: '${dto.cround}회차 원서접수(필기)',
		          start: '${dto.docRegStart1}',
		          end: '${dto.docRegEnd1}',
		        },
		        {
		          title: '${dto.cround}회차 추가접수(필기)',
		          start: '${dto.docRegStart2}',
		          end: '${dto.docRegEnd2}',
			    },
			    {
		          title: '${dto.cround}회차 시험일(필기)',
		          start: '${dto.docTestStart}',
		          end: '${dto.docTestEnd}',
			    },
			    {
		          title: '${dto.cround}회차 합격자발표(필기)',
		          start: '${dto.docResultStart}',
		          end: '${dto.docResultEnd}',
			    },
			    {
		          title: '${dto.cround}회차 응시자격서류제출',
		          start: '${dto.docSubmitStart}',
		          end: '${dto.docSubmitEnd}',
			    },
			    {
		          title: '${dto.cround}회차 원서접수(실기)',
		          start: '${dto.pracRegStart1}',
		          end: '${dto.pracRegEnd1}',
				},
				{
		          title: '${dto.cround}회차 추가접수(실기)',
		          start: '${dto.pracRegStart2}', 
		          end: '${dto.pracRegEnd2}',
				},
				{
		          title: '${dto.cround}회차 시험일(실기)',
		          start: '${dto.pracTestStart}',
		          end: '${dto.pracTestEnd}',
				},
				{
		          title: '${dto.cround}회차 합격자발표(실기)',
		          start: '${dto.pracResStart}',
		          end: '${dto.pracResEnd}',
				},
			</c:forEach>
	      ]
	    });
	
	    calendar.render();
	  });
	
	</script>
	<style>
	
	  body {
	    margin: 0;
	    padding: 0;
	    font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
	    font-size: 14px;
	  }
	
	  #calendar {
	    max-width:700px;
	    margin: 0 auto;
	  }
	
	</style>
</head>
<body>

  <div id='calendar'></div>

</body>
</html>
