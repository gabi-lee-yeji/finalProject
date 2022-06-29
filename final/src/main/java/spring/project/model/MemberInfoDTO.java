package spring.project.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MemberInfoDTO {

	private String memid;
	private String passwd;
	private String mem_name;
	
	private String email;
	private String mail1;
	private String mail2;
	
	private int postalCode;
	private String address;
	private String addr_detail;
	
	private String pc; //phone company 추가할지 고민중
	private String mobile;
	private String phone1;
	private String phone2;
	private String phone3;
	
	private String mem_degree;
	private String major;
	private String mem_job;
	
	private String status;
	private String mem_level;
	private int mem_point;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date regdate;
	
	
}
