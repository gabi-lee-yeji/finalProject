package spring.project.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import spring.project.model.*;

public interface CertiMapper {
	
	// ��ü �ڰ��� ���
	public List<CertiInfoDTO> getCertiList
		(@Param("startRow") int startRow, @Param("endRow") int endRow, @Param("category") String category);
	
	// ���� �ڰ��� ���
	public List<CertiInfoDTO> getCertiLangList();
	
	
	// �ڰ��� ����
	public int getCertCnt();
	
	// �ڰ��� ���͸�
	public List<CertiRequirementDTO> getCertiFilter();
	
}
