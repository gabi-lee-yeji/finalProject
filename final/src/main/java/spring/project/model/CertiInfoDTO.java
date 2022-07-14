package spring.project.model;


import java.util.Date;

import lombok.Data;

@Data
public class CertiInfoDTO implements CertiAccessible{
	private String cnum; //�ڰ��� ������ȣ (PK)
	private String cname;
	private String category; // �������?, �ΰ�, ����
	private String clevel; // �ڰ��� ���?
	
	private String company; //����ó/������(�ڰݰ�����)
	private String website; // ������ ������Ʈ
	
	private String requirement; //�����ڰ�
	
	private String cmethod; //�������? eg) ������ / ������...
	private String subject; // ��������
	
	private String cutline; // �հݱ���
	private String cinfo; // ����, ����
	
	private String cjob; // ���� ����, ����, ����
	
	private String expiry;
	private String status; //������Ȳ 
	
	private String price; //���� ���÷�
	
	private Integer ncs_cat;
	
	private String notice; //������
	
	private String registDate; //DB�� ��ϵ�? ��¥	
	
	//보유자격증 정보를 가져오기 위한 변수 추가 
	//resultMap을 쓰기에 겹치는 변수가 많고, 2개만 추가하면되므로 같은 DTO 사용
	private String cert_name;    //member_cert 테이블의 자격증명 (cnum 존재시 필요 x)
	private Date expiryDate;	//보유자격증의 만료일자
}
