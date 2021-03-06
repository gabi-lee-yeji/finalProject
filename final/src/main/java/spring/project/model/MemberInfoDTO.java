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
	private String extraAddress;
	private String pC; //phone company �߰����� ����� > �ʿ��ϴٸ� �߰��ص� OK
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
	private String quiz1;
	private String quiz2;
	private String findPw1;
	private String findPw2;
	private String passwdQ1;
	private String passwdQ2;
	private String ref_date;
	
	//신고횟수 조회 기능 사용할때만 사용할 변수
	private int reportCnt;
// 핸드폰 번호 쪼개서 사용할지 한번에 모바일로 할 지 결정해야하는데 쪼개서 유효성하는게 편할 듯?
	
}
