package spring.project.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import spring.project.model.*;

public interface CertiMapper {
	
	// 전체 자격증 목록
	public List<CertiInfoDTO> getCertiList (@Param("startRow") int startRow, @Param("endRow") int endRow,@Param("category")String category);
	
	
	// 어학 자격증 목록
	public List<CertiInfoDTO> getCertiLangList();
	
	// 자격증 상세페이지
	public CertiInfoDTO getCertiInfo(String cnum);
	public CertiRequirementDTO getCertiReqInfo(String cnum);
	
	public List<CertiDateDTO> searchPeriod(String cnum);
	public List<CertiScheduleDTO> getQnetDateInfo(String cnum);
	public List<CertiDateDTO> searchNatPeriod(@Param("clevel")String clevel,
											@Param("cyear_list")List<Integer> cyear_list,
											@Param("cround_list")List<Integer> cround_list);
	
	// 자격증 개수
	public int getCertCnt();
	
	// 자격증 필터
	public List<CertiInfoDTO> getreqList(@Param("req_age")String req_age,@Param("req_degree")String req_degree,@Param("req_exp")String req_exp);
	public List<CertiInfoDTO> getNcsList();
	List<Map<String,Object>> getNcsCodeList();
	List<CertiInfoDTO> getCertiFilteredList(Map map);
	int getCertiFilteredCnt(CertiFilterDTO dto);
	List<String> getNcsName(CertiFilterDTO dto);
	
	// 찜 목록
	public LikeDTO checkLike(LikeDTO like);
	public int likeCheck(@Param("cnum")String cnum, @Param("memid")String memid);
	public List<String> getLikeList(String memid);
	
	
	
}
