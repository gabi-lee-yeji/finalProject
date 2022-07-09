package spring.project.service;

import java.util.List;
import java.util.Map;

import spring.project.model.CertiAccessible;
import spring.project.model.CertiDateDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.CertiRequirementDTO;
import spring.project.model.CertiScheduleDTO;
import spring.project.model.MemberFilterDTO;
import spring.project.model.MemberInfoDTO;
import spring.project.pagination.PagingDTO;

public interface AdminService {
	
	//ï¿½Ú°ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½Þ¼ï¿½ï¿½ï¿½ 
	//ï¿½Ú°ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿?
	public int addCertiInfo(CertiInfoDTO info, CertiScheduleDTO schedule, 
							CertiDateDTO date, CertiRequirementDTO requirement);
	
	//ï¿½ï¿½Ïµï¿? ï¿½Ú°ï¿½ï¿½ï¿½ ï¿½ï¿½Ã¼ ï¿½ï¿½ï¿?
	public List<CertiInfoDTO> getCertList(PagingDTO page, String sort, String order, String category);
	//ï¿½ï¿½Ïµï¿? ï¿½Ú°ï¿½ï¿½ï¿½ ï¿½ï¿½Ã¼ ï¿½ï¿½ï¿½ï¿½
	public int getCertCnt();
	
	//ÀÚ°ÝÁõ °Ë»ö 
	public List<CertiInfoDTO> getSearchList(PagingDTO page, String search, String keyword);
	//ï¿½Ë»ï¿½ ï¿½ï¿½ï¿? ï¿½ï¿½Ã¼ ï¿½ï¿½ï¿½ï¿½
	public int getSearchCnt(String search, String keyword);
		
	
	//µî·ÏµÈ ÀÚ°ÝÁõ Á¤º¸ - »ó¼¼Á¤º¸
	public Map<String, CertiAccessible> getCertiInfo(String cnum);
	
	//ÀÚ°ÝÁõº° ÀÏÁ¤Á¤º¸ ¸ñ·Ï Á¶È¸ ¹× ÀÏÁ¤ °Ë»ö
	public List<CertiDateDTO> searchPeriod(String cnum);
	public List<CertiDateDTO> searchNatPeriod(String cnum);
	
	//ÀÚ°ÝÁõ ÀÏÁ¤ Ãß°¡
	public int addCertiDate(CertiDateDTO dto);
	//ÀÚ°ÝÁõ ÀÏÁ¤ »èÁ¦
	public int deleteCertiDate(String[] dateList);
	//ÀÚ°ÝÁõ ÀÏÁ¤ ¼öÁ¤ - Á¤º¸ºÒ·¯¿À±â
	public CertiDateDTO getCertiDate(int datePK);
	//ÀÚ°ÝÁõ ÀÏÁ¤ ¼öÁ¤ 
	public int modCertiDate(CertiDateDTO dto);
	
	
	//ÀÚ°ÝÁõ Á¤º¸ »èÁ¦ 
	public int delCerti(String cnum, String name);
	//ÀÚ°ÝÁõ Á¤º¸ ¼öÁ¤
	public int modCerti(CertiInfoDTO info, CertiRequirementDTO req);
	
	
	
	//È¸ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½Þ¼ï¿½ï¿½ï¿½
	//È¸ï¿½ï¿½ ï¿½ï¿½Ã¼ ï¿½ï¿½ï¿? 
	public List<MemberInfoDTO> getMemberList(PagingDTO page, String sort, String order);
	//È¸ï¿½ï¿½ ï¿½ï¿½Ã¼ ï¿½ï¿½ ï¿½ï¿½È¸
	public int getMemberCnt();
	
	//È¸ï¿½ï¿½ ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
	public MemberInfoDTO getMemberInfo(String memid);
	
	//ï¿½Å°ï¿½ï¿? È¸ï¿½ï¿½ ï¿½ï¿½ï¿?
	public List<MemberInfoDTO> getMemberReport(String status);
	//ï¿½Å°ï¿½ï¿? È¸ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ - ï¿½Å°ï¿½ï¿½ï¿½ï¿? ï¿½ï¿½/ï¿½ï¿½ï¿? ï¿½ï¿½ï¿?, ï¿½Å°ï¿½ï¿½ï¿½ È¸ï¿½ï¿½
	public List<Map<String,Object>> getreportMemInfo(String memid);
	//ï¿½Å°ï¿½ï¿? È¸ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½
	public int updateRepMemStatus(String memid, String status);
	
	//È¸¿ø¸ñ·Ï - ÇÊÅÍ¸µ / °Ë»ö
	public List<MemberInfoDTO> getMemberFilter(MemberFilterDTO filter, PagingDTO page);
	
	
	//1:1¹®ÀÇ ´äº¯ ¾È´Þ¸° ±Û ¸ð¾Æº¸±â
}
