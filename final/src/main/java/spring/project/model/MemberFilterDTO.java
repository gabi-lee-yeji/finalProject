package spring.project.model;

import lombok.Data;

@Data
public class MemberFilterDTO {
	
	private String search;
	private String keyword;
	
//	private String memid;
//	private String mem_name;
//	private int lastPhoneN;
//	private String major;
//	private String mem_job;
//	private String mem_interest;
//	private String mem_cert;
	
	
	private String[] mem_degree;
	private String[] status;
	private String[] mem_level;
	
	private int mem_point1;
	private int mem_point2;
	
	private String regDate1;
	private String regDate2;
	
}
