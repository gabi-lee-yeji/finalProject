package spring.project.service;

import java.util.List;
import java.util.Map;

import spring.project.model.CertiAccessible;
import spring.project.model.CertiDateDTO;
import spring.project.model.CertiFilterDTO;
import spring.project.model.CertiInfoDTO;


public interface CertiService {
	
	// 전체 자격증 목록
	public List<CertiInfoDTO> getCertiList(int startRow, int endRow,String category);
	
	
	// 자격증 개수
	public int getCertCnt();
	
	// �ڰ��� ������
	public Map<String, CertiAccessible> getCertiInfo(String cnum);
	
	// �ڰ����� �������� ��� ��ȸ �� ���� �˻�
	public List<CertiDateDTO> searchPeriod(String cnum);
	public List<CertiDateDTO> searchNatPeriod(String cnum);
	
	// 어학 자격증 목록
	public List<CertiInfoDTO> getCertiLangList();
	
	// 자격증 필터
	public List<CertiInfoDTO> getFilteredList(CertiFilterDTO dto);
	public List<CertiInfoDTO> getreqList(String req_age,String req_degree,String req_exp);

	public List<Map<String,Object>> getNcsCodeList();
	public int getCertiFilteredCnt(CertiFilterDTO dto);
	public List<String> getNcsName(CertiFilterDTO dto);
	
	// 찜 목록
	public int count(String cnum, String memid);
	
	public List<String> getLikeList(String memid);
	
}
