package spring.project.service;

import java.util.List;
import java.util.Map;

import spring.project.model.CertiAccessible;
import spring.project.model.CertiDateDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.CertiRequirementDTO;
import spring.project.model.CertiScheduleDTO;
import spring.project.model.MemberFilterDTO;
import spring.project.model.MemberInfoDTO;
import spring.project.pagination.PagingDTO;

public interface AdminService {
	
	//�ڰ��� ���� �޼��� 
	//�ڰ��� ���
	public int addCertiInfo(CertiInfoDTO info, CertiScheduleDTO schedule, 
							CertiDateDTO date, CertiRequirementDTO requirement);
	
	//��ϵ� �ڰ��� ��ü ���
	public List<CertiInfoDTO> getCertList(PagingDTO page, String sort, String order, String category);
	//��ϵ� �ڰ��� ��ü ����
	public int getCertCnt();
	
	//��ϵ� �ڰ��� ���� - ������
	public Map<String, CertiAccessible> getCertiInfo(String cnum);
	
	//�ڰ����� �������� ��� ��ȸ �� ���� �˻�
	public List<CertiDateDTO> searchPeriod(Map<String, String> map);
	public List<CertiDateDTO> searchNatPeriod(String cnum);
	
	//�ڰ��� ����
	//public int modCerti(String cnum, CertiInfoDTO dto, CertiDetailDTO detail);
	
	
	
	
	
	//�ڰ��� �˻� 
	public List<CertiInfoDTO> getSearchList(PagingDTO page, String search, String keyword);
	//�˻� ��� ��ü ����
	public int getSearchCnt(String search, String keyword);
	
	//����Ȯ�� ������ - ������ �ڰ��� ���� ���
	public List<CertiInfoDTO> getDelList(String[] cnumList);
	//�ڰ��� ���� 
	public int delCerti(String[] cnumList);
	
	
	
	//ȸ�� ���� �޼���
	//ȸ�� ��ü ��� 
	public List<MemberInfoDTO> getMemberList(PagingDTO page, String sort, String order);
	//ȸ�� ��ü �� ��ȸ
	public int getMemberCnt();
	
	//ȸ�� �� ����
	public MemberInfoDTO getMemberInfo(String memid);
	
	//�Ű�� ȸ�� ���
	public List<MemberInfoDTO> getMemberReport(String status);
	//�Ű�� ȸ���� �� ���� - �Ű���� ��/��� ���, �Ű��� ȸ��
	public List<Map<String,Object>> getreportMemInfo(String memid);
	//�Ű�� ȸ���� ���� ����
	public int updateRepMemStatus(String memid, String status);
	
	
	//ȸ����� - ���͸� / �˻�
	public List<MemberInfoDTO> getMemberFilter(MemberFilterDTO filter, PagingDTO page);
}
