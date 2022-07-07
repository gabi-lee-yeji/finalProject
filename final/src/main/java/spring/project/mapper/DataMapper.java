package spring.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import spring.project.model.CertiDateDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.CertiMatchDTO;
import spring.project.model.CertiScheduleDTO;
import spring.project.model.PassDetailDTO;
import spring.project.model.PassRateDTO;

public interface DataMapper {

	public void addQnetDate(CertiDateDTO dto);
	
	public void addPassDetailN(PassDetailDTO dto);
	
	public int updateCertiDetail(@Param("dto") CertiInfoDTO dto, @Param("cname") String cname);
	
	public String findCnum(String cname);
	
	public void addNatCertiInfo(CertiInfoDTO info);
	public void addNatCertiSchedule(CertiScheduleDTO sch);
	public List<CertiInfoDTO> getCmethods();
	public void updateCmethods(CertiInfoDTO dto);
	public List<CertiInfoDTO> getSubjects();
	public void updateSubject(CertiInfoDTO dto);
	public List<CertiInfoDTO> getMingan();
	public int updateMingan(CertiInfoDTO dto);
	public int addPassRate(PassRateDTO dto);
	public void addCertiRelated(CertiMatchDTO dto);
}
