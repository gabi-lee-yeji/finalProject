package spring.project.model;


import lombok.Data;

@Data
public class CertiDateDTO {
	
	private String cnum; 
	private int cyear;
	private int cround;
	
	//필기시험 원서접수 시작,마감
    private String docRegStart1;
    private String docRegEnd1;
    
    //필기시험 추가접수 시작,마감
    private String docRegStart2;
    private String docRegEnd2;
    
    //필기시험 시작, 마감
    private String docTestStart;
    private String docTestEnd;
    
    //필기시험 합격자 발표
    private String docResultStart;
    private String docResultEnd;
    
    //응시자격 서류 접수 시작, 마감
    private String docSubmitStart;
    private String docSubmitEnd;
    
    //실기시험 원서 접수 시작, 마감
    private String pracRegStart1;
    private String pracRegEnd1;
    
    //실기시험 추가접수 시작, 마감
    private String pracRegStart2 ;
    private String pracRegEnd2 ;
    
    //실기시험 시작, 종료
    private String pracTestStart ;
    private String pracTestEnd ;
    
    //실기시험 합격자 발표 시작, 마감
    private String pracResStart ;
    private String pracResEnd ;
    
    ////DATE
    //필기시험 원서접수 시작,마감
    private String docRegStart1_date;
    private String docRegEnd1_date;
    
    //필기시험 추가접수 시작,마감
    private String docRegStart2_date;
    private String docRegEnd2_date;
    
    //필기시험 시작, 마감
    private String docTestStart_date;
    private String docTestEnd_date;
    
    //필기시험 합격자 발표
    private String docResultStart_date;
    private String docResultEnd_date;
    
    //응시자격 서류 접수 시작, 마감
    private String docSubmitStart_date;
    private String docSubmitEnd_date;
    
    //실기시험 원서 접수 시작, 마감
    private String pracRegStart1_date;
    private String pracRegEnd1_date;
    
    //실기시험 추가접수 시작, 마감
    private String pracRegStart2_date;
    private String pracRegEnd2_date;
    
    //실기시험 시작, 종료
    private String pracTestStart_date;
    private String pracTestEnd_date;
    
    //실기시험 합격자 발표 시작, 마감
    private String pracResStart_date;
    private String pracResEnd_date;
    
    ////TIME
    //필기시험 원서접수 시작,마감
    private String docRegStart1_time;
    private String docRegEnd1_time;
    
    //필기시험 추가접수 시작,마감
    private String docRegStart2_time;
    private String docRegEnd2_time;
    
    //필기시험 시작, 마감
    private String docTestStart_time;
    private String docTestEnd_time;
    
    //필기시험 합격자 발표
    private String docResultStart_time;
    private String docResultEnd_time;
    
    //응시자격 서류 접수 시작, 마감
    private String docSubmitStart_time;
    private String docSubmitEnd_time;
    
    //실기시험 원서 접수 시작, 마감
    private String pracRegStart1_time;
    private String pracRegEnd1_time;
    
    //실기시험 추가접수 시작, 마감
    private String pracRegStart2_time;
    private String pracRegEnd2_time;
    
    //실기시험 시작, 종료
    private String pracTestStart_time;
    private String pracTestEnd_time;
    
    //실기시험 합격자 발표 시작, 마감
    private String pracResStart_time;
    private String pracResEnd_time;
    
}