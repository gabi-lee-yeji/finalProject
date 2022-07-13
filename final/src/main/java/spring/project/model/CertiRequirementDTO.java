package spring.project.model;

import lombok.Data;

@Data
public class CertiRequirementDTO implements CertiAccessible{
	private String cnum;
	private String req_degree; //학력
	private String req_age;		//나이
	private String req_exp;		//경력
	private String pre_requisite;
	private String ref;
}
