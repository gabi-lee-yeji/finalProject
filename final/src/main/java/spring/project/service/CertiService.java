package spring.project.service;

import java.util.List;

import spring.project.model.CertiInfoDTO;

public interface CertiService {
	
	// ���� �ڰ��� ���
	public List<CertiInfoDTO> getCertiNatList();
	
	// �ΰ� �ڰ��� ���
	public List<CertiInfoDTO> getCertiPrvList();
	
	// ���� �ڰ��� ���
	public List<CertiInfoDTO> getCertiLangList();

}
