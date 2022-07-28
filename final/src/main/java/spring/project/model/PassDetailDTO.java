package spring.project.model;

import lombok.Data;

@Data
public class PassDetailDTO {
	private String cname;	//자격증 이름
	private int m10;		//남성 10대
	private int m20;
	private int m30;
	private int m40;
	private int m50;
	private int m60;		//남성 60대 이상
	private int f10;		//여성 10대
	private int f20;
	private int f30;
	private int f40;
	private int f50;
	private int f60;
	private int total;		//합계
}
