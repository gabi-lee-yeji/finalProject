package spring.project.service;

import java.util.List;

import spring.project.model.CertiDateDTO;
import spring.project.model.CertiInfoDTO;

public interface UserMainService {
	
	//국가기술자격증 일정 조회
	List<CertiDateDTO> getNatSchedules();
	
	//인기자격증 순위
	//국가기술 top 10
	//공인민간 top 10
	//사용자 정보에 맞는 인기자격증 추천
	List<CertiInfoDTO> getClientTopCerti(String memid);
}