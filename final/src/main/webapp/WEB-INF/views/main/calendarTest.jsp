<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta charset='utf-8' />
<link href='/resources/fullcalendar/main.css' rel='stylesheet' />
<script src='/resources/fullcalendar/main.js'></script>
<script>
  document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
      headerToolbar: {
        left: 'prev,next today',
        center: 'title',
        right: 'dayGridMonth,timeGridWeek,timeGridDay,listMonth'
      },
      locale : "ko",
      navLinks: true, // can click day/week names to navigate views
      businessHours: true, // display business hours
      editable: true,
      selectable: true,
      events: [
    	  <c:forEach var="dto" items="${natList}">
	        {
	          title: '${dto.cround}회차 ${dto.clevel} 원서접수(필기)',
	          start: '${dto.docRegStart1}',
	          end: '${dto.docRegEnd1}',
	          constraint: 'businessHours'
	        },
	        {
	          title: '${dto.cround}회차 ${dto.clevel} 추가접수(필기)',
	          start: '${dto.docRegStart2}',
	          end: '${dto.docRegEnd2}',
	          constraint: 'businessHours'
		    },
		    {
	          title: '${dto.cround}회차 ${dto.clevel} 시험일(필기)',
	          start: '${dto.docTestStart}',
	          end: '${dto.docTestEnd}',
	          constraint: 'businessHours'
		    },
		    {
	          title: '${dto.cround}회차 ${dto.clevel} 합격자발표(필기)',
	          start: '${dto.docResultStart}',
	          end: '${dto.docResultEnd}',
	          constraint: 'businessHours'
		    },
		    {
	          title: '${dto.cround}회차 ${dto.clevel} 응시자격서류제출',
	          start: '${dto.docSubmitStart}',
	          end: '${dto.docSubmitEnd}',
	          constraint: 'businessHours'
		    },
		    {
	          title: '${dto.cround}회차 ${dto.clevel} 원서접수(실기)',
	          start: '${dto.pracRegStart1}',
	          end: '${dto.pracRegEnd1}',
	          constraint: 'businessHours'
			},
			{
	          title: '${dto.cround}회차 ${dto.clevel} 추가접수(실기)',
	          start: '${dto.pracRegStart2}', 
	          end: '${dto.pracRegEnd2}',
	          constraint: 'businessHours'
			},
			{
	          title: '${dto.cround}회차 ${dto.clevel} 시험일(실기)',
	          start: '${dto.pracTestStart}',
	          end: '${dto.pracTestEnd}',
	          constraint: 'businessHours'
			},
			{
	          title: '${dto.cround}회차 ${dto.clevel} 합격자발표(실기)',
	          start: '${dto.pracResStart}',
	          end: '${dto.pracResEnd}',
	          constraint: 'businessHours',
	          color: '#257e4a'
			},
  		</c:forEach>
		<c:forEach var="dto" items="${certiList}">
		</c:forEach>
      ]
    });

    calendar.render();
  });

</script>
<style>

  body {
    margin: 40px 10px;
    padding: 0;
    font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
    font-size: 14px;
  }

  #calendar {
    max-width: 1100px;
    margin: 0 auto;
  }

</style>
</head>
<body>

  <div id='calendar'></div>
  
</body>
</html>
