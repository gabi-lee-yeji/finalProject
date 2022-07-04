package spring.project.mapper;

import org.apache.ibatis.annotations.Param;

import spring.project.model.CertiDateDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.CertiScheduleDTO;
import spring.project.model.PassDetailDTO;

public interface DataMapper {

	public void addQnetDate(CertiDateDTO dto);
	
	public void addPassDetailN(PassDetailDTO dto);
	
	public int updateCertiDetail(@Param("dto") CertiInfoDTO dto, @Param("cname") String cname);
	
	public String findCnum(String cname);
	
	public void addNatCertiInfo(CertiInfoDTO info);
	public void addNatCertiSchedule(CertiScheduleDTO sch);
}
