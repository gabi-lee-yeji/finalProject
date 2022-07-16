package spring.project.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MemberCertiDTO {
	private String memid;
	private String cnum;
	private String cert_name;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date expirydate;
	private String ctype;
	private String clevel;
}
