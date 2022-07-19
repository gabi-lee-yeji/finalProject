package spring.project.mapper;

import java.util.List;

import spring.project.model.CertiDateDTO;
import spring.project.model.CertiInfoDTO;

public interface UserMainMapper {

	List<CertiDateDTO> getNatSchedules();
	
	//사용자 맞춤 인기자격증 조회
	List<CertiInfoDTO> getClientTopCerti(String order);
}
