package spring.admin.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class CertificateDTO {
	private String name;
	private String category; // 국가기술, 민간, 어학
	private String subCategory; //eg) 기술사, 기사, 기능사 etc
	private int round; //국가기술자격증 시험 회차 eg)126
	
	//원서 접수 시작일 , 마감일
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date regStart;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date regEnd;
	
	//추가 원서 접수 시작일 , 마감일
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date reg_addStart;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date reg_addEnd;
	
	//원서 접수 시작시간 , 마감시간
	@DateTimeFormat(pattern="hh:mm")
	private Date regStartTime;
	@DateTimeFormat(pattern="hh:mm")
	private Date regEndTime;
	
	//시험일자
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date testDate;
	
	//결과 발표일 
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date resDate;
	
}
