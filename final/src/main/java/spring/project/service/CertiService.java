package spring.project.service;

import java.util.List;

import spring.project.model.CertiDetailDTO;
import spring.project.model.CertiInfoDTO;

public interface CertiService {
	
	// 국가 자격증 목록
	public List<CertiInfoDTO> getCertiNatList();
	
	// 국가 자격증 세부
	public List<CertiDetailDTO> getCertiDetailNatList();
	
	// 민간 자격증 목록
	public List<CertiInfoDTO> getCertiPrvList();
	
	// 어학 자격증 목록
	public List<CertiInfoDTO> getCertiLangList();

}
