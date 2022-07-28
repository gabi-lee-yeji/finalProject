package spring.project.model;

import lombok.Data;

@Data
public class CertiFilterDTO {
	
	String category;
	
	private int[] ncs_cat;
	private String[] clevel;
	
	String company;
	
}
