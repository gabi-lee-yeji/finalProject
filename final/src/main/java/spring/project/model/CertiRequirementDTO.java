package spring.project.model;

import lombok.Data;

@Data
public class CertiRequirementDTO implements CertiAccessible{
	private String cnum;
	private String clevel;
	private String req_degree; 
	private int req_age;		
	private int req_exp;		
	private String pre_requisite;
	private String ref;
}
