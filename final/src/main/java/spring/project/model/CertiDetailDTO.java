package spring.project.model;

import lombok.Data;

@Data
public class CertiDetailDTO {
	private String cnum;
	private String company; //접수처/시행기관(자격관리자)
	private String clevel; //자격등급
	private String requirement; //응시자격
	private String cmethod; //검정방법 eg) 객관식 / 서술형...
	private String subject; // 검정과목
	private String cutline; // 합격기준
	private String cinfo; // 정보, 개요
	private String cjob; // 관련 직업, 진로, 전망
	private String website; // 시행기관 웹사이트
}
