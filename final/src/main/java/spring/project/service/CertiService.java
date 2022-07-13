package spring.project.service;

import java.util.List;

import spring.project.model.CertiInfoDTO;
import spring.project.model.CertiRequirementDTO;

public interface CertiService {
	
	// ��ü �ڰ��� ���
	public List<CertiInfoDTO> getCertiList(int startRow, int endRow, String category);
	
	//��ϵ� �ڰ��� ��ü ����
	public int getCertCnt();
	
	// ���� �ڰ��� ���
	public List<CertiInfoDTO> getCertiLangList();
	
	// �ڰ��� ����
	public List<CertiRequirementDTO> getCertiFilter();


}
