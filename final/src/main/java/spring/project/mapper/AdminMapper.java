package spring.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import spring.project.model.CertiDetailDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.PagingDTO;
import spring.project.model.QnetDateDTO;

public interface AdminMapper {
	
	public int addCerti(CertiInfoDTO dto);
	public int addCertiDetail(CertiDetailDTO dto);
	
	public int findNextseq(String sequence);
	public int findCurrseq(String sequence);
	
	public int modCertInfo(CertiInfoDTO dto);
	public int modCertDetail(CertiInfoDTO dto);
	
	public List<CertiInfoDTO> getCertList(@Param("startRow")int startRow,
										@Param("endRow")int endRow,
										@Param("sort")String sort, 
										@Param("order")String order);
	public int getCertCnt();
	
	
	public CertiInfoDTO getCertiInfo(String cnum);
	public CertiDetailDTO getCertiDetail(String cnum);
	public QnetDateDTO getQnetdate(CertiInfoDTO dto);

	
	public List<CertiInfoDTO> getSearchList(@Param("startRow")int startRow,
											@Param("endRow")int endRow,
											@Param("search")String search, 
											@Param("keyword")String keyword);
	public int getSearchCnt(@Param("search")String search, 
							@Param("keyword")String keyword);
	
	public List<CertiInfoDTO> getDelList(String[] cnumList);
	public int delCertiInfo(String[] cnumList);
	public int delCertiDetail(String[] cnumList);
}
