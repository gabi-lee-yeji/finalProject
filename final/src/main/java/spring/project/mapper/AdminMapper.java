package spring.project.mapper;

import java.util.List;

import spring.project.model.CertiDetailDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.QnetDateDTO;

public interface AdminMapper {
	
	public int addCerti(CertiInfoDTO dto);
	public int addCertiDetail(CertiDetailDTO dto);
	
	public int findNextseq(String sequence);
	public int findCurrseq(String sequence);
	
	public int modCerti(CertiInfoDTO dto);
	public int delCerti(String cnum);
	public List<CertiInfoDTO> getCertList();
	
	public void addQnetDate(QnetDateDTO dto);
	
}
