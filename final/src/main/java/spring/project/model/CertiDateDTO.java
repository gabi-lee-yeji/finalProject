package spring.project.model;


import lombok.Data;

@Data
public class CertiDateDTO implements CertiAccessible{
	private int datePK; //일정구분 위한 pk (일정 삭제, 수정시 필요)
	
	private String cnum; 
	private int cyear;
	private int cround;
	private String clevel;
	
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
    
    @Override
	public String toString() {
		return "CertiDateDTO [datePK=" + datePK + ", cnum=" + cnum + ", cyear=" + cyear + ", cround=" + cround
				+ ", clevel=" + clevel + ", docRegStart1=" + docRegStart1 + ", docRegEnd1=" + docRegEnd1
				+ ", docRegStart2=" + docRegStart2 + ", docRegEnd2=" + docRegEnd2 + ", docTestStart=" + docTestStart
				+ ", docTestEnd=" + docTestEnd + ", docResultStart=" + docResultStart + ", docResultEnd=" + docResultEnd
				+ ", docSubmitStart=" + docSubmitStart + ", docSubmitEnd=" + docSubmitEnd + ", pracRegStart1="
				+ pracRegStart1 + ", pracRegEnd1=" + pracRegEnd1 + ", pracRegStart2=" + pracRegStart2 + ", pracRegEnd2="
				+ pracRegEnd2 + ", pracTestStart=" + pracTestStart + ", pracTestEnd=" + pracTestEnd + ", pracResStart="
				+ pracResStart + ", pracResEnd=" + pracResEnd + "]";
	}
    
}