package spring.project.service;

import spring.project.model.CertiDetailDTO;
import spring.project.model.PassDetailDTO;
import spring.project.model.QnetDateDTO;

public interface DataService {

	//ť�� ���� ������(csv) �߰�
	public void addQnetDate(QnetDateDTO dto);
	
	//PassDate csv������ �߰�(N=�������)
	public void addPassDetailN(PassDetailDTO dto);
	
	//certidetail ���̺� ������ ������Ʈ
	public int updateCertiDetail(CertiDetailDTO dto, String cname);
}
