package spring.project.model;

import lombok.Data;

@Data
public class PassRateDTO {
	private String cname;	//자격증 이름
	private String clevel;	//자격증 급수
	private int cyear;		//연도
	private int applied;	//접수자 수
	private int tested;		//응시자 수
	private int passed;		//합격자 수
}
