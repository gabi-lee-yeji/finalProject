package spring.project.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import spring.project.model.CertiDateDTO;
import spring.project.model.CertiFilterDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.CertiRequirementDTO;
import spring.project.model.Comm_BoardDTO;
import spring.project.model.Post_BoardDTO;
import spring.project.model.SearchAccessible;

public interface UserMainMapper {

	//국가기술 top 10
	List<CertiInfoDTO> getNatTopCerti();
	//공인민간 top 10 
	List<String> getPrvTopCerti();
	//사용자 맞춤 인기자격증 조회
	List<CertiInfoDTO> getClientTopCerti(String order);
	
	//자격증 종목명 검색
	List<SearchAccessible> getCertiSearchList(@Param("startRow")int startRow, @Param("endRow")int endRow,
										@Param("keyword")String keyword);
	int getCertiSearchCnt(String keyword);
	
	//전체사이트내 키워드 검색
	List<SearchAccessible> getHelpBoardSearchList(@Param("startRow")int startRow, @Param("endRow")int endRow,
										@Param("keyword")String keyword);
	int getHelpBoardSearchCnt(String keyword);
	
	List<SearchAccessible> getCommBoardSearchList(@Param("startRow")int startRow, @Param("endRow")int endRow,
										@Param("keyword")String keyword);
	int getCommBoardSearchCnt(String keyword);
	
	List<SearchAccessible> getCommentSearchList(@Param("startRow")int startRow, @Param("endRow")int endRow,
										@Param("keyword")String keyword);
	int getCommentSearchCnt(String keyword);
	
	//사용자메인 - 캘린더
	List<CertiDateDTO> getNatSchedules();
	List<CertiDateDTO> getCertiSchedules(List<String> cnum);
	//마이페이지 - 캘린더
	//사용자 관심 자격증 리스트 조회
	List<String> getMemberLike(String memid);
	List<CertiDateDTO> getMemberNatSchedules(List<String> cnum);
	//자격증 상세정보 - 캘린더
	//국가
	List<CertiDateDTO> getNatCertiDate(String cnum);
	//민간,어학
	List<CertiDateDTO> getCertiDate(String cnum);
	//자격증 상세정보 - 응시자격
	List<CertiRequirementDTO> getCertiRequirement(String cnum);
		//응시자격에 cnum이 없는 국가자격증인 경우
		String checkClevel(String cnum);
		List<CertiRequirementDTO> getNatCertiRequirement(String clevel);
		
	List<Map<String,Object>> getNcsCodeList();
	List<CertiInfoDTO> getCertiFilteredList(Map map);
	int getCertiFilteredCnt(CertiFilterDTO dto);
	List<String> getNcsName(CertiFilterDTO dto);
	
	CertiDateDTO getClosestNatSchedule();
	List<Map<String,Object>> getClosePrvTests();
	List<Map<String,Object>> getCloseNatTests();
	
	int getCloseTestCnt();
	
	List<String> getCloseNatCnumList();
	List<String> getClosePrvCnumList();
}
