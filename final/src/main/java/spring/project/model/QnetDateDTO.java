package spring.project.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class QnetDateDTO {

	private int cyear;
	private int cround;
	private String ctype; // eg) 기술사, 기사, 산업기사 etc
	
	// 필기 원서 접수 시작일, 마감일, 필기시험일
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date regStart1;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date regEnd1;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date test1;
	
	//응시자격 원서 제출 시작일, 마감일
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date submitStart;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date submitEnd;
	
	
	// 실기 원서 접수 시작일, 마감일, 실기시험일
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date regStart2;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date regEnd2;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date test2;
	
	//합격발표 시작일, 마감일
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date resStart;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date resEnd;
	
}
