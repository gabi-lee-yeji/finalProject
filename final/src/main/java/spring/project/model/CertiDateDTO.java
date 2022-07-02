package spring.project.model;


import lombok.Data;

@Data
public class CertiDateDTO implements CertiAccessible{
	private int datePK; //ÀÏÁ¤±¸ºÐ À§ÇÑ pk (ÀÏÁ¤ »èÁ¦, ¼öÁ¤ ÇÊ¿ä)
	
	private String cnum; 
	private int cyear;
	private int cround;
	private String clevel;
	
	//ï¿½Ê±ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½,ï¿½ï¿½ï¿½ï¿½
    private String docRegStart1;
    private String docRegEnd1;
    
    //ï¿½Ê±ï¿½ï¿½ï¿½ï¿½ ï¿½ß°ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½,ï¿½ï¿½ï¿½ï¿½
    private String docRegStart2;
    private String docRegEnd2;
    
    //ï¿½Ê±ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½, ï¿½ï¿½ï¿½ï¿½
    private String docTestStart;
    private String docTestEnd;
    
    //ï¿½Ê±ï¿½ï¿½ï¿½ï¿½ ï¿½Õ°ï¿½ï¿½ï¿½ ï¿½ï¿½Ç¥
    private String docResultStart;
    private String docResultEnd;
    
    //ï¿½ï¿½ï¿½ï¿½ï¿½Ú°ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½, ï¿½ï¿½ï¿½ï¿½
    private String docSubmitStart;
    private String docSubmitEnd;
    
    //ï¿½Ç±ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½, ï¿½ï¿½ï¿½ï¿½
    private String pracRegStart1;
    private String pracRegEnd1;
    
    //ï¿½Ç±ï¿½ï¿½ï¿½ï¿½ ï¿½ß°ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½, ï¿½ï¿½ï¿½ï¿½
    private String pracRegStart2 ;
    private String pracRegEnd2 ;
    
    //ï¿½Ç±ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½, ï¿½ï¿½ï¿½ï¿½
    private String pracTestStart ;
    private String pracTestEnd ;
    
    //ï¿½Ç±ï¿½ï¿½ï¿½ï¿½ ï¿½Õ°ï¿½ï¿½ï¿½ ï¿½ï¿½Ç¥ ï¿½ï¿½ï¿½ï¿½, ï¿½ï¿½ï¿½ï¿½
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