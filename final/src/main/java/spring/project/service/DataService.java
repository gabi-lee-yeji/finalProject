package spring.project.service;

import spring.project.model.CertiDateDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.PassDetailDTO;

public interface DataService {

	//ť�� ���� ������(csv) �߰�
	public void addQnetDate(CertiDateDTO dto);
	
	//PassDate csv������ �߰�(N=�������)
	public void addPassDetailN(PassDetailDTO dto);
	
	//certidetail ���̺� ������ ������Ʈ
	public int updateCertiDetail(CertiInfoDTO dto, String cname);
	/*
	//�ڰ��� �̸����� certiinfo ���� cnum ã��
	public String findCnum(String cname);
	*/
}
