package spring.project.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import spring.project.model.CertiDetailDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.MemberInfoDTO;
import spring.project.model.PagingDTO;
import spring.project.model.QnetDateDTO;

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
	
	//ť�� ���� ������(csv) �߰�
	public void addQnetDate(QnetDateDTO dto);
	
	//����Ȯ�� ������ - ������ �ڰ��� ���� ���
	public List<CertiInfoDTO> getDelList(String[] cnumList);
	//�ڰ��� ���� 
	public int delCerti(String[] cnumList);
	
	
	
	//ȸ�� ���� �޼���
	//ȸ�� ��ü ��� 
	public List<MemberInfoDTO> getMemberList(PagingDTO page, String sort, String order);
	//ȸ�� �� ����
	//�Ű�� ȸ�� ��� 
	
}
