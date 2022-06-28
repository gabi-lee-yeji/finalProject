package spring.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import spring.project.model.CertiDetailDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.QnetDateDTO;

public interface AdminMapper {
	
	public int addCerti(CertiInfoDTO dto);
	public int addCertiDetail(CertiDetailDTO dto);
	
	public int findNextseq(String sequence);
	public int findCurrseq(String sequence);
	
	public int modCertInfo(CertiInfoDTO dto, @Param("cnum")String cnum);
	public int modCertDetail(CertiInfoDTO dto, @Param("cnum")String cnum);
	
	public int delCerti(String cnum);
	
	public List<CertiInfoDTO> getCertList();
	
	public CertiInfoDTO getCertiInfo(String cnum);
	public CertiDetailDTO getCertiDetail(String cnum);
	
	public QnetDateDTO getQnetdate(CertiInfoDTO dto);
}
