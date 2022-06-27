package spring.project.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import spring.project.model.CertiDetailDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.PagingDTO;
import spring.project.model.QnetDateDTO;

public interface AdminService {

	//�ڰ��� ���
	public int addCerti(CertiInfoDTO info, CertiDetailDTO detail);
	
	//�ڰ��� ����
	public int modCerti(CertiInfoDTO dto, CertiDetailDTO detail);
	
	//�ڰ��� ���� 
	public int delCerti(String cnum);
	
	//��ϵ� �ڰ��� ���� 
	public List<Object> getCertiInfo(String cnum);
	
	//��ϵ� �ڰ��� ��ü ���
	public List<CertiInfoDTO> getCertList(PagingDTO page);
	//��ϵ� �ڰ��� ��ü ����
	public int getCertCnt();
	
	//�ڰ��� �˻� 
	public List<CertiInfoDTO> getSearchList(PagingDTO page, String search, String keyword);
	//�˻� ��� ��ü ����
	public int getSearchCnt(String search, String keyword);
	
	
}
