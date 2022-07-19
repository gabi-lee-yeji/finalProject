package spring.project.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import spring.project.model.*;

public interface CertiMapper {
	
	// 전체 자격증 목록
	public List<CertiInfoDTO> getCertiList (@Param("cnum") String cnum, @Param("startRow") int startRow,
										@Param("endRow") int endRow, @Param("category") String category,
										@Param("req_degree") String req_degree,@Param("req_age") String req_age,
										@Param("req_exp") String req_exp,@Param("clevel") String clevel);
	
	// 어학 자격증 목록
	public List<CertiInfoDTO> getCertiLangList();
	
	// 자격증 상세정보
	public CertiInfoDTO getCertiInfo(String cnum);
	public CertiRequirementDTO getCertiReqInfo(String cnum);
	
	public List<CertiDateDTO> searchPeriod(String cnum);
	public List<CertiScheduleDTO> getQnetDateInfo(String cnum);
	public List<CertiDateDTO> searchNatPeriod(@Param("clevel")String clevel,
											@Param("cyear_list")List<Integer> cyear_list,
											@Param("cround_list")List<Integer> cround_list);
	
	// 자격증 개수
	public int getCertCnt();
	
/*	// 해당 자격증 찜 여부 확인용 데이터 가져오기
	public MemberLikeDTO getLike(String memid);

	// 상세 페이지 찜하기
	public void setLike(MemberLikeDTO cnum);

	// 상세 페이지 찜 취소 기능
	public int deleteLike(MemberLikeDTO cnum); */		
	
}
