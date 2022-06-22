package spring.project.mapper;

import java.util.List;

import spring.project.model.CertiDetailDTO;
import spring.project.model.CertiInfoDTO;

public interface AdminMapper {
	
	public int addCerti(CertiInfoDTO dto);
	public int addCertiDetail(CertiDetailDTO dto);
	
	public int findCnum();
	
	public int modCerti(CertiInfoDTO dto);
	public int delCerti(String cnum);
	public List<CertiInfoDTO> getCertList();
}
