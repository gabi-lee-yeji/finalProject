package spring.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import spring.project.model.CertiDateDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.Comm_BoardDTO;
import spring.project.model.Post_BoardDTO;
import spring.project.model.SearchAccessible;

public interface UserMainMapper {

	List<CertiDateDTO> getNatSchedules();
	
	//국가기술 top 10
	List<CertiInfoDTO> getNatTopCerti();
	//공인민간 top 10 
	List<String> getPrvTopCerti();
	//사용자 맞춤 인기자격증 조회
	List<CertiInfoDTO> getClientTopCerti(String order);
	
	//자격증 종목명 검색
	List<CertiInfoDTO> getCertiSearchList(@Param("startRow")int startRow, @Param("endRow")int endRow,
										@Param("keyword")String keyword);
	//전체사이트내 키워드 검색
	List<Post_BoardDTO> getHelpBoardSearchList(@Param("startRow")int startRow, @Param("endRow")int endRow,
										@Param("keyword")String keyword);
	List<Post_BoardDTO> getCommBoardSearchList(@Param("startRow")int startRow, @Param("endRow")int endRow,
										@Param("keyword")String keyword);
	List<Comm_BoardDTO> getCommSearchList(@Param("startRow")int startRow, @Param("endRow")int endRow,
										@Param("keyword")String keyword);
	
}
