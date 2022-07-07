
const dday = new Date("2022-08-02:00:00:00+0000").getTime();
		
		setInterval(function() {
		  const today = new Date().getTime();
		  const gap = Math.abs(dday - today);
		  
		  const day = Math.floor(gap / (1000 * 60 * 60 * 24));
		  const dayS = day.toString().padStart(2,'0');
		  
		  const hour = Math.floor((gap % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
		  const hourS = hour.toString().padStart(2,'0');
		  
		  const min = Math.floor((gap % (1000 * 60 * 60)) / (1000 * 60));
		  const minS = min.toString().padStart(2,'0');
		  
		  const sec = Math.floor((gap % (1000 * 60)) / 1000);
		  const secS = sec.toString().padStart(2,'0');
		
		  if((dday - today) > 0 ){
			  document.getElementById("count").innerHTML = "D - " + dayS + "일 " + hourS + ":" + minS + ":" + secS ; 
		  }else{
			  document.getElementById("count").innerHTML = "D + " + dayS + "일 " + hourS + ":" + minS + ":" + secS ;
		  }
		}, 1000);