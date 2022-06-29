package spring.project.service;

import java.util.List;

import spring.project.model.CertiDetailDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.MemberFilterDTO;
import spring.project.model.MemberInfoDTO;
import spring.project.model.QnetDateDTO;
import spring.project.pagination.PagingDTO;

public interface AdminService {
	
	//�ڰ��� ���� �޼��� 
	//�ڰ��� ���
	public int addCerti(CertiInfoDTO info, CertiDetailDTO detail);
	
	//�ڰ��� ����
	public int modCerti(String cnum, CertiInfoDTO dto, CertiDetailDTO detail);
	
	//��ϵ� �ڰ��� ���� 
	public List<Object> getCertiInfo(String cnum);
	
	//��ϵ� �ڰ��� ��ü ���
	public List<CertiInfoDTO> getCertList(PagingDTO page, String sort, String order);
	//��ϵ� �ڰ��� ��ü ����
	public int getCertCnt();
	
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
	//�Ű�� ȸ�� ��� 
	
	//ȸ����� - ���͸� / �˻�
	public List<MemberInfoDTO> getSearchList(MemberFilterDTO filter, PagingDTO page);
}
