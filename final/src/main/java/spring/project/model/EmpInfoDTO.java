package spring.project.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class EmpInfoDTO {
	private String empid;
	private String job;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date hiredate;
	private int sal;
	private String status;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date ref_date;
}
