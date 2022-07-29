package spring.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import spring.project.model.CertiDateDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.CertiMatchDTO;
import spring.project.model.CertiRequirementDTO;
import spring.project.model.CertiScheduleDTO;
import spring.project.model.NcsDTO;
import spring.project.model.PassDetailDTO;
import spring.project.model.PassRateNatDTO;
import spring.project.model.PassRatePrvDTO;

public interface DataMapper {

	public void addQnetDate(CertiDateDTO dto);
	
	public void addPassDetailN(PassDetailDTO dto);
	
	public String findCnum(String cname);
	public String findPrvCnum(@Param("cname") String cname, @Param("clevel") String clevel);
	public int findCnumCount(String cname);
	
	public int addPassRate(PassRatePrvDTO dto);
	public void addCertiRelated(CertiMatchDTO dto);
	public void addNatData(CertiInfoDTO dto);
	public void updateNatClevel(CertiScheduleDTO dto);
	public void addCertiSchedule(CertiScheduleDTO dto);
	public void addNcsCode(NcsDTO dto);
	public void addPrvInfo(CertiInfoDTO dto);
	public void updatePrvInfo1(CertiInfoDTO dto);
	public void updatePrvInfo2(CertiInfoDTO dto);
	public void addCertiReq(CertiRequirementDTO dto);
	public void addLangInfo(CertiInfoDTO dto);
	public void addPassRateNat(PassRateNatDTO dto);
	
	public void temp1(CertiDateDTO dto);
	public void temp2(CertiDateDTO dto);
}
