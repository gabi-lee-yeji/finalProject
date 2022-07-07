package spring.project.service;

import java.util.List;

import spring.project.model.CertiDateDTO;

public interface UserMainService {
	
	//국가기술자격증 
	List<CertiDateDTO> getNatSchedules();
	
}
