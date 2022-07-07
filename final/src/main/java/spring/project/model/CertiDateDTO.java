package spring.project.model;


import lombok.Data;

@Data
public class CertiDateDTO implements CertiAccessible{
	private int datePK; //일정구분 위한 pk (일정 삭제, 수정시 필요)
	
	private String cnum; 
	private int cyear;
	private int cround;
	private String clevel;
	
	//占십깍옙占쏙옙占� 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙,占쏙옙占쏙옙
    private String docRegStart1;
    private String docRegEnd1;
    
    //占십깍옙占쏙옙占� 占쌩곤옙占쏙옙占쏙옙 占쏙옙占쏙옙,占쏙옙占쏙옙
    private String docRegStart2;
    private String docRegEnd2;
    
    //占십깍옙占쏙옙占� 占쏙옙占쏙옙, 占쏙옙占쏙옙
    private String docTestStart;
    private String docTestEnd;
    
    //占십깍옙占쏙옙占� 占쌌곤옙占쏙옙 占쏙옙표
    private String docResultStart;
    private String docResultEnd;
    
    //占쏙옙占쏙옙占쌘곤옙 占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙, 占쏙옙占쏙옙
    private String docSubmitStart;
    private String docSubmitEnd;
    
    //占실깍옙占쏙옙占� 占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙, 占쏙옙占쏙옙
    private String pracRegStart1;
    private String pracRegEnd1;
    
    //占실깍옙占쏙옙占� 占쌩곤옙占쏙옙占쏙옙 占쏙옙占쏙옙, 占쏙옙占쏙옙
    private String pracRegStart2 ;
    private String pracRegEnd2 ;
    
    //占실깍옙占쏙옙占� 占쏙옙占쏙옙, 占쏙옙占쏙옙
    private String pracTestStart ;
    private String pracTestEnd ;
    
    //占실깍옙占쏙옙占� 占쌌곤옙占쏙옙 占쏙옙표 占쏙옙占쏙옙, 占쏙옙占쏙옙
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
    
//    public String getDocRegStart1() {
//    	if(!docRegStart1.split(" ")[1].startsWith("00")) {
//    		return docRegStart1.split(" ")[0];
//    	}
//    	return docRegStart1.split(" ")[0]+"T"+docRegStart1.split(" ")[1];
//    }
    
}