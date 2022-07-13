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
	private String birthday;
	private String gender;
	private String postalCode;
	private String address;
	private String addr_detail;
<<<<<<< HEAD
	private String pC; //phone company ï¿½ß°ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ > ï¿½Ê¿ï¿½ï¿½Ï´Ù¸ï¿½ ï¿½ß°ï¿½ï¿½Øµï¿½ OK
=======
	private String extraAddress;
	private String pC; //phone company Ãß°¡ÇÒÁö °í¹ÎÁß > ÇÊ¿äÇÏ´Ù¸é Ãß°¡ÇØµµ OK
>>>>>>> branch 'main' of https://github.com/gabi-lee-yeji/finalProject.git
	private String mobile;
	private String phone1;
	private String phone2;
	private String phone3;
	private String mem_degree;
	private String major;
	private String mem_job;
	private int status;
	private String mem_level;
	private int mem_point;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date regdate;
	
	//ï¿½Å°ï¿½È½ï¿½ï¿½ ï¿½ï¿½È¸ ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½Ò¶ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
	private int reportCnt;
// ï¿½Úµï¿½ï¿½ï¿½ ï¿½ï¿½È£ ï¿½É°ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½Ñ¹ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½Ï·ï¿½ ï¿½ï¿½ ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½Ø¾ï¿½ï¿½Ï´Âµï¿½ ï¿½É°ï¿½ï¿½ï¿½ ï¿½ï¿½È¿ï¿½ï¿½ï¿½Ï´Â°ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½?
	
	private String status_name;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date ref_date;
	
}
