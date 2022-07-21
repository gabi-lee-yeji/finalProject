package spring.project.model;


import java.util.List;

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
    
    
}