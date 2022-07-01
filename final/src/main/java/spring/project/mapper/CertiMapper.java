package spring.project.mapper;

import java.util.List;

import spring.project.model.*;

public interface CertiMapper {
	
	public List<CertiInfoDTO> getCertiNatList();
	public List<CertiDetailDTO> getCertiDetailNatList();
	public List<CertiInfoDTO> getCertiPrvList();
	public List<CertiInfoDTO> getCertiLangList();
}
