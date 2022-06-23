package spring.project.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class CertiInfoDTO {
	private String cnum; //자격증 고유번호 
	private String cname;
	private String category; // 국가기술, 민간, 어학
	
	//QNETDATE 테이블과 조인 시 필요한 변수 
	private int cyear; //시행년도 
	private int cround; //국가기술자격증 시험 회차 eg)126
	private String ctype; //eg) 기술사, 기사, 기능사 etc
	
	//원서 접수 시작일 , 마감일1
	private String regStart;
	private String regEnd;
	
	//추가 원서 접수 시작일 , 마감일
	private String reg_addStart;
	private String reg_addEnd;
	
	//원서 접수 시작시간 , 마감시간
	//@DateTimeFormat(pattern="HH:mm")
	private String regStartTime;
	//@DateTimeFormat(pattern="HH:mm")
	private String regEndTime;
	
	//시험일자
	//@DateTimeFormat(pattern="yyyy-MM-dd")
	private String testDate;
	
	//결과 발표일
	//@DateTimeFormat(pattern="yyyy-MM-dd")
	private String resDate;
	
}
