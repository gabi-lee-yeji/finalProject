package spring.project.model;

import lombok.Data;

@Data
public class QnetDateDTO {

	private int cyear;
	private int cround;
	private String ctype; // eg) 기술사, 기사, 산업기사 etc
	
	//필기시험 원서접수 시작,마감
    private String docRegStart1;
    private String docRegEnd1 ;
    
    //필기시험 추가접수 시작,마감
    private String docRegStart2 ;
    private String docRegEnd2 ;
    
    //필기시험 시작, 마감
    private String docTestStart ;
    private String docTestEnd ;
    
    //필기시험 합격자 발표
    private String docResult ;
    
    //응시자격 서류 접수 시작, 마감
    private String docSubmitStart ;
    private String docSubmitEnd ;
    
    //실기시험 원서 접수 시작, 마감
    private String pracRegStart1 ;
    private String pracRegEnd1 ;
    
    //실기시험 추가접수 시작, 마감
    private String pracRegStart2 ;
    private String pracRegEnd2 ;
    
    //실기시험 시작, 종료
    private String pracTestStart ;
    private String pracTestEnd ;
    
    //실기시험 합격자 발표 시작, 마감
    private String pracResStart ;
    private String pracResEnd ;
	
}
