package spring.project.service;

import java.util.List;

import spring.project.model.CertiInfoDTO;

public interface CertiService {
	
	// ï¿½ï¿½ï¿½ï¿½ ï¿½Ú°ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿?
	public List<CertiInfoDTO> getCertiNatList();
	
	// ±¹°¡ ÀÚ°ÝÁõ ¼¼ºÎ
	//public List<CertiDetailDTO> getCertiDetailNatList();
	
	// ¹Î°£ ÀÚ°ÝÁõ ¸ñ·Ï
	public List<CertiInfoDTO> getCertiPrvList();
	
	// ï¿½ï¿½ï¿½ï¿½ ï¿½Ú°ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿?
	public List<CertiInfoDTO> getCertiLangList();

}
