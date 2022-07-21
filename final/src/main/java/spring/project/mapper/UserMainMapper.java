package spring.project.mapper;

import java.util.List;

import spring.project.model.CertiDateDTO;
import spring.project.model.CertiInfoDTO;

public interface UserMainMapper {

	List<CertiDateDTO> getNatSchedules();
	
	//국가기술 top 10
	List<CertiInfoDTO> getNatTopCerti();
	//공인민간 top 10 
	List<String> getPrvTopCerti();
	//사용자 맞춤 인기자격증 조회
	List<CertiInfoDTO> getClientTopCerti(String order);
}
