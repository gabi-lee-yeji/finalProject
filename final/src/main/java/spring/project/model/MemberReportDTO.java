package spring.project.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MemberReportDTO {
	private String memid;
	private String report_id;
	private String wr_option;
	private int pnum;
	private String reason;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date reg;
}
