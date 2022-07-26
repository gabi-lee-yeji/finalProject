package spring.project.model;

import lombok.Data;

@Data
public class NcsDTO {
	private int code;	//NCS코드(4자리 숫자)
	private String lname; //대분류명
	private String mname; //중분류명
}
