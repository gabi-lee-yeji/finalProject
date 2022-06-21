package spring.project.service;

import java.util.List;

import spring.project.model.CertiInfoDTO;

public interface AdminService {

	//�ڰ��� ���
	public int addCerti(CertiInfoDTO dto);
	//�ڰ��� ����
	public int modCerti(CertiInfoDTO dto);
	//�ڰ��� ���� 
	public int delCerti(String cnum);
	//��ϵ� �ڰ��� ��ü ���
	public List<CertiInfoDTO> getCertList();
}
