package spring.project.model;


import java.util.List;

import lombok.Data;

@Data
public class CertiDateDTO implements CertiAccessible{
	private int datePK; //일정구분 위한 pk (일정 삭제, 수정시 필요)
	
	private String cnum; 
	private int cyear;
	private Integer cround;
	private String clevel;
	
    private String docRegStart1;
    private String docRegEnd1;
    
    private String docRegStart2;
    private String docRegEnd2;
    
    private String docTestStart;
    private String docTestEnd;
    
    private String docResultStart;
    private String docResultEnd;
    
    private String docSubmitStart;
    private String docSubmitEnd;
    
    private String pracRegStart1;
    private String pracRegEnd1;
    
    private String pracRegStart2 ;
    private String pracRegEnd2 ;
    
    private String pracTestStart ;
    private String pracTestEnd ;
    
    private String pracResStart ;
    private String pracResEnd ;
    
    
    //자격증 일정 가져올때 자격증 종목명 담을 변수
    private String cname;
    
    
    public CertiDateDTO() {};
	public CertiDateDTO(String cnum, int cyear, Integer cround, String clevel, String docRegStart1,
			String docRegEnd1, String docRegStart2, String docRegEnd2, String docTestStart, String docTestEnd,
			String docResultStart, String docResultEnd, String docSubmitStart, String docSubmitEnd,
			String pracRegStart1, String pracRegEnd1, String pracRegStart2, String pracRegEnd2, String pracTestStart,
			String pracTestEnd, String pracResStart, String pracResEnd) {
		super();
		this.cnum = cnum;
		this.cyear = cyear;
		this.cround = cround;
		this.clevel = clevel;
		this.docRegStart1 = docRegStart1;
		this.docRegEnd1 = docRegEnd1;
		this.docRegStart2 = docRegStart2;
		this.docRegEnd2 = docRegEnd2;
		this.docTestStart = docTestStart;
		this.docTestEnd = docTestEnd;
		this.docResultStart = docResultStart;
		this.docResultEnd = docResultEnd;
		this.docSubmitStart = docSubmitStart;
		this.docSubmitEnd = docSubmitEnd;
		this.pracRegStart1 = pracRegStart1;
		this.pracRegEnd1 = pracRegEnd1;
		this.pracRegStart2 = pracRegStart2;
		this.pracRegEnd2 = pracRegEnd2;
		this.pracTestStart = pracTestStart;
		this.pracTestEnd = pracTestEnd;
		this.pracResStart = pracResStart;
		this.pracResEnd = pracResEnd;
	}
    
//    public String getDocRegStart1() {
//    	if(!docRegStart1.split(" ")[1].startsWith("00")) {
//    		return docRegStart1.split(" ")[0];
//    	}
//    	return docRegStart1.split(" ")[0]+"T"+docRegStart1.split(" ")[1];
//    }
    
}