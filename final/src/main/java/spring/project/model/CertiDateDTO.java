package spring.project.model;


import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
//자격증 일정 DTO
public class CertiDateDTO implements CertiAccessible{
	private int datePK; //일정구분 위한 pk (일정 삭제, 수정시 필요)
	
	private String cnum; // certiinfo 관련 key
	private int cyear;	//시행연도
	private Integer cround;	//시행회차
	private String clevel;	//자격증 등급
    private String docRegStart1;	//필기원서접수시작
    private String docRegEnd1;		//필기원서접수마감
    
    private String docRegStart2;	//필기원서추가접수시작
    private String docRegEnd2;		//필기원서추가접수마감
    
    private String docTestStart;	//필기시험시작
    private String docTestEnd;		//필기시험종료
    
    private String docResultStart;	//필기결과발표시작
    private String docResultEnd;	//필기결과발표마감
    
    private String docSubmitStart;	//서류제출시작
    private String docSubmitEnd;	//서류제출마감
    
    private String pracRegStart1;	//실기원서접수시작
    private String pracRegEnd1;		//실기원서접수마감
    
    private String pracRegStart2 ;	//실기원서추가접수시작
    private String pracRegEnd2 ;	//실기원서추가접수마감
    
    private String pracTestStart ;	//실기시험시작
    private String pracTestEnd ;	//실기시험종료
    
    private String pracResStart ;	//실기결과발표시작
    private String pracResEnd ;		//실기결과발표마감
    
    
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
    
}