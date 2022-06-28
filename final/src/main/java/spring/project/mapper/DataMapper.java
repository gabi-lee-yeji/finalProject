package spring.project.mapper;

import org.apache.ibatis.annotations.Param;

import spring.project.model.CertiDetailDTO;
import spring.project.model.PassDetailDTO;
import spring.project.model.QnetDateDTO;

public interface DataMapper {

	public void addQnetDate(QnetDateDTO dto);
	
	public void addPassDetailN(PassDetailDTO dto);
	
	public int updateCertiDetail(@Param("dto") CertiDetailDTO dto, @Param("cname") String cname);
	
	//public String findCnum(String cname);
}
