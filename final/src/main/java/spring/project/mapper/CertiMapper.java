package spring.project.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import spring.project.model.*;

public interface CertiMapper {
	
	// 전체 자격증 목록
	public List<CertiInfoDTO> getCertiList
		(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("category") String category);
	
	// 어학 자격증 목록
	public List<CertiInfoDTO> getCertiLangList();
	
	
	// 자격증 개수
	public int getCertCnt();
	
	// 자격증 필터링
	public List<CertiRequirementDTO> getCertiFilter();
	
}
