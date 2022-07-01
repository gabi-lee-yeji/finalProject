package spring.project.model;


import lombok.Data;

@Data
public class CertiDateDTO {
	
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
    
}