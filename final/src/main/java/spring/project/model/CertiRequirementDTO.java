package spring.project.model;

import lombok.Data;

@Data
public class CertiRequirementDTO implements CertiAccessible{
	private String cnum;	//certiinfo 연결 key(nullable)
	private String clevel;	//자격증 레벨
	private String req_degree;	//학력
	private Integer req_age;		//나이
	private Integer req_exp;		//경력
	private String pre_requisite;	//전제조건(하위자격증 필요 등)
	private String ref;	//기타 조건
}
