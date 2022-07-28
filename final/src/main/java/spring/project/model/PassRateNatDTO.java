package spring.project.model;

import lombok.Data;

@Data
public class PassRateNatDTO implements PassRateAccessible{
	private String cname;	//자격증명
	private int cyear;		//연도
	private int doc_apply;	//필기접수
	private int doc_pass;	//필기합격
	private int prac_apply;	//실기접수
	private int prac_pass;	//실기합격
}
