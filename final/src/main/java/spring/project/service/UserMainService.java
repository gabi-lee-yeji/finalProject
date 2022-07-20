package spring.project.service;

import java.util.List;
import java.util.Map;

import spring.project.model.CertiDateDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.SearchAccessible;
import spring.project.pagination.PagingDTO;

public interface UserMainService {
	
	//국가기술자격증 일정 조회
	List<CertiDateDTO> getNatSchedules();
	
	//인기자격증 순위
	//국가기술 top 10
	List<CertiInfoDTO> getNatTopCerti();
	//공인민간 top 10
	List<String> getPrvTopCerti();
	//사용자 정보에 맞는 인기자격증 추천
	List<CertiInfoDTO> getClientTopCerti(String memid);
	
	//자격증 검색 기능 + 공민민간자격증top10 눌렀을 경우 결과 페이지로 이동 - UserMain에서 사용되는 검색
	List<CertiInfoDTO> getCertiSearchList(PagingDTO page, String keyword);
	//전체사이트 검색 기능 - NavBar에 따라다니는 검색
	Map<String, List<SearchAccessible>> getSearchList(PagingDTO page, String keyword);
}