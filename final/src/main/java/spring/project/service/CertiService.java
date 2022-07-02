package spring.project.service;

import java.util.List;

import spring.project.model.CertiDetailDTO;
import spring.project.model.CertiInfoDTO;

public interface CertiService {
	
	// ���� �ڰ��� ���
	public List<CertiInfoDTO> getCertiNatList();
	
	// ���� �ڰ��� ����
	public List<CertiDetailDTO> getCertiDetailNatList();
	
	// �ΰ� �ڰ��� ���
	public List<CertiInfoDTO> getCertiPrvList();
	
	// ���� �ڰ��� ���
	public List<CertiInfoDTO> getCertiLangList();

}
