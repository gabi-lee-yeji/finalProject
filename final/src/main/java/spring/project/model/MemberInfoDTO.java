package spring.project.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


@Data
public class MemberInfoDTO {

	private String memid; //아이디 - Pk값
	private String passwd; //비밀번호
	private String mem_name; //이름
	private String email; //이메일 완성
	private String mail1; //이메일 왼쪽
	private String mail2; //이메일 오른쪽
	private String birthday; //생일
	private String gender; //성별
	private String postalCode; //우편번호
	private String address; //주소
	private String addr_detail; //상세주소
	private String extraAddress; //참고주소
	private String pC; //phone company 
	private String mobile; //핸드폰번호
	private String phone1; //핸드폰번호1
	private String phone2; //핸드폰번호2
	private String phone3; //핸드폰번호3
	private String mem_degree; //학력
	private String major; //전공
	private String mem_job; //직업
	private int status; //회원상태
	private int mem_point; //멤버 포인트
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date regdate; //가입 날짜
	
	private int cnt; //controller의 userCheck메서드에서 조인문으로 가져온 count를 dto로 받아옴
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date ref_date; //로그인 시 업데이트 되는 최신날짜
	
	//신고횟수 조회 기능 사용할때만 사용할 변수
	private int reportCnt;
	
}
