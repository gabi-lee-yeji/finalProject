package spring.project.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.project.mapper.CertiMapper;
import spring.project.model.CertiAccessible;
import spring.project.model.CertiDateDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.CertiRequirementDTO;
import spring.project.model.CertiScheduleDTO;

@Service
public class CertiServiceImpl implements CertiService {

	@Autowired
	private CertiMapper mapper;
	static Map<String, CertiAccessible> certiMap = new HashMap<String, CertiAccessible>();

	@Override
	public List<CertiInfoDTO> getCertiList(String cnum,int startRow, int endRow, String category) {

		return mapper.getCertiList(cnum,startRow, endRow, category);
	}
	
	
	@Override
	public Map<String, CertiAccessible> getCertiInfo(String cnum) {
		CertiInfoDTO info = mapper.getCertiInfo(cnum);
		CertiRequirementDTO requirement = mapper.getCertiReqInfo(cnum);
		certiMap.put("info", info);
		certiMap.put("req", requirement);
		return certiMap;
	}
	
	@Override
	public List<CertiDateDTO> searchPeriod(String cnum){
		return mapper.searchPeriod(cnum);
	}
	
	@Override
	public List<CertiDateDTO> searchNatPeriod(String cnum){
		List<Integer> cyear_list = new ArrayList<Integer>();
		List<Integer> cround_list = new ArrayList<Integer>();
		
		List<CertiScheduleDTO> schedule = mapper.getQnetDateInfo(cnum);
		for(CertiScheduleDTO dto : schedule ) {
			cyear_list.add(dto.getCyear());
			cround_list.add(dto.getCround());
		}
		String clevel = schedule.get(0).getClevel();
	
		return mapper.searchNatPeriod(clevel, cyear_list, cround_list);
	}
	
	@Override
	public int getCertCnt() {
		return mapper.getCertCnt();
	}

	@Override
	public List<CertiInfoDTO> getCertiLangList() {
		
		return mapper.getCertiLangList();
	}


	@Override
	public List<CertiInfoDTO> getFilteredList(String[] clevel) {	
		return mapper.getFilteredList(clevel);
	}
	
	@Override
	public List<CertiInfoDTO> getreqList(String req_age,String req_degree,String req_exp){
		return mapper.getreqList(req_age, req_degree, req_exp);
	}
	
	

}
