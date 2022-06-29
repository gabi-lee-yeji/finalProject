package spring.project.model;

import lombok.Data;

@Data
public class CertiScheduleDTO {
	private String cnum; //certiinfo 조인할 때 사용
	private int cyear; //시행년도 
	private int cround; //국가기술자격증 시험 회차 eg)126
	private String ctype; //eg) 기술사, 기사, 기능사 etc
	private String registDate; // DB등록날짜 (sysdate)
}
