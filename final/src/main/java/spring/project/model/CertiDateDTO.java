package spring.project.model;


import lombok.Data;

@Data
public class CertiDateDTO implements CertiAccessible{
	private int datePK; //�������� ���� pk (���� ����, ���� �ʿ�)
	
	private String cnum; 
	private int cyear;
	private int cround;
	private String clevel;
	
	//�ʱ���� �������� ����,����
    private String docRegStart1;
    private String docRegEnd1;
    
    //�ʱ���� �߰����� ����,����
    private String docRegStart2;
    private String docRegEnd2;
    
    //�ʱ���� ����, ����
    private String docTestStart;
    private String docTestEnd;
    
    //�ʱ���� �հ��� ��ǥ
    private String docResultStart;
    private String docResultEnd;
    
    //�����ڰ� ���� ���� ����, ����
    private String docSubmitStart;
    private String docSubmitEnd;
    
    //�Ǳ���� ���� ���� ����, ����
    private String pracRegStart1;
    private String pracRegEnd1;
    
    //�Ǳ���� �߰����� ����, ����
    private String pracRegStart2 ;
    private String pracRegEnd2 ;
    
    //�Ǳ���� ����, ����
    private String pracTestStart ;
    private String pracTestEnd ;
    
    //�Ǳ���� �հ��� ��ǥ ����, ����
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