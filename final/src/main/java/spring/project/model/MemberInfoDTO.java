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
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthday;
	
	private String gender;
	
	private int postalCode;
	private String address;
	private String addr_detail;
	
	private String pC; //phone company 추가할지 고민중 > 필요하다면 추가해도 OK
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

	//신고목록 가져올때만 사용하는 변수 
	private int reportCnt;
	
// 핸드폰 번호 쪼개서 사용할지 한번에 모바일로 할 지 결정해야하는데 쪼개서 유효성하는게 편할 듯?
	
	public String getEmail() {
		if(mail1 != null && mail2 != null) {
		return email = getMail1() + "@" +getMail2();
		}else {
			return email;
		}	
	}
}
