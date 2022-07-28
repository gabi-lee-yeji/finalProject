package spring.project.model;

import lombok.Data;

@Data
//연관자격증 DTO
public class CertiMatchDTO {
	//from자격증에서 to자격증으로 관련이 있다
	private String cfrom;	
	private String cto;
}
