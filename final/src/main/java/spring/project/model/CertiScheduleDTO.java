package spring.project.model;

import lombok.Data;

@Data
//국가기술자격 일정정보 DTO
public class CertiScheduleDTO implements CertiAccessible{
	private String cnum; // certiinfo와 참조하는 key
	private int cyear; // 시행연도
	private int cround; // 자격시험 시행회차 eg)126
	private String clevel; //시험레벨(1급2급 or 기사,기술사 .. )
	private String registDate; // DB에 등록된 날(sysdate)
	
	//생성자 오버로딩
	public CertiScheduleDTO() {};
	public CertiScheduleDTO (String cnum, int cyear, int cround, String clevel) {
		this.cnum=cnum;
		this.cyear=cyear;
		this.cround=cround;
		this.clevel=clevel;
	}
}
