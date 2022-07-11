package spring.project.service;

import java.util.List;
import java.util.Map;

import spring.project.model.CertiAccessible;
import spring.project.model.CertiDateDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.CertiRequirementDTO;

public interface CertiService {
	
	// ��ü �ڰ��� ���
	public List<CertiInfoDTO> getCertiList(String cnum, int startRow, int endRow, String category);
	
	//��ϵ� �ڰ��� ��ü ����
	public int getCertCnt();
	
	// �ڰ��� ������
	public Map<String, CertiAccessible> getCertiInfo(String cnum);
	
	// �ڰ����� �������� ��� ��ȸ �� ���� �˻�
	public List<CertiDateDTO> searchPeriod(String cnum);
	public List<CertiDateDTO> searchNatPeriod(String cnum);
	
	// ���� �ڰ��� ���
	public List<CertiInfoDTO> getCertiLangList();
	
	// �ڰ��� ����
	public List<CertiRequirementDTO> getCertiFilter(String req_age, String req_degree, String req_exp);



}
