package spring.project.model;


import lombok.Data;

@Data
public class CertiInfoDTO {
	private String cnum; //자격증 고유번호 (PK)
	private String cname;
	private String category; // 국가기술, 민간, 어학
	private String clevel; // 자격증 등급
	
	private String company; //접수처/시행기관(자격관리자)
	private String website; // 시행기관 웹사이트
	
	private String requirement; //응시자격
	
	private String cmethod; //검정방법 eg) 객관식 / 서술형...
	private String subject; // 검정과목
	
	private String cutline; // 합격기준
	private String cinfo; // 정보, 개요
	
	private String cjob; // 관련 직업, 진로, 전망
	
	private String status; //시행현황 
	
	private String price; //시험 응시료
	
	private String ncs_cat;
	
	private String notice; //비고사항
	
	private String registDate; //DB에 등록된 날짜	
}
