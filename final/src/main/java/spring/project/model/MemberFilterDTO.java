package spring.project.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MemberFilterDTO {
	
	private String search;
	private String keyword;
	
	private String[] status;
	private String[] mem_level;
	
	private int mem_point1;
	private int mem_point2;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date regDate1;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date regDate2;
	
}
