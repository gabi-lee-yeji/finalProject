package spring.project.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import spring.project.model.CertiAccessible;
import spring.project.model.CertiDateDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.CertiRequirementDTO;
import spring.project.model.LikeDTO;

public interface CertiService {
	
	// ��ü �ڰ��� ���
	public List<CertiInfoDTO> getCertiList(int startRow, int endRow);
	
	
	//��ϵ� �ڰ��� ��ü ����
	public int getCertCnt();
	
	// �ڰ��� ������
	public Map<String, CertiAccessible> getCertiInfo(String cnum);
	
	// �ڰ����� �������� ��� ��ȸ �� ���� �˻�
	public List<CertiDateDTO> searchPeriod(String cnum);
	public List<CertiDateDTO> searchNatPeriod(String cnum);
	
	// ���� �ڰ��� ���
	public List<CertiInfoDTO> getCertiLangList();
	
	//���͸� ���
	public List<CertiInfoDTO> getFilteredList(String[] clevel);
	public List<CertiInfoDTO> getreqList(String req_age,String req_degree,String req_exp);

	// ��Ȯ��
	public int count(String cnum, String memid);
	
	public List<String> getLikeList(String memid);
}
