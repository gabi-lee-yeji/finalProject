package spring.project.mapper;

import java.util.List;

import spring.project.model.CertiInfoDTO;

public interface AdminMapper {
	public int addCerti(CertiInfoDTO dto);
	public int modCerti(CertiInfoDTO dto);
	public int delCerti(String cnum);
	public List<CertiInfoDTO> getCertList();
}
