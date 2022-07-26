package spring.project.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.project.mapper.CertiMapper;
import spring.project.model.CertiAccessible;
import spring.project.model.CertiDateDTO;
import spring.project.model.CertiFilterDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.CertiRequirementDTO;
import spring.project.model.CertiScheduleDTO;

@Service
public class CertiServiceImpl implements CertiService {

	@Autowired
	private CertiMapper mapper;
	static Map<String, CertiAccessible> certiMap = new HashMap<String, CertiAccessible>();

	@Override
	public List<CertiInfoDTO> getCertiList(int startRow, int endRow,String category) {
		return mapper.getCertiList(startRow, endRow, category);
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
	public List<CertiInfoDTO> getFilteredList(CertiFilterDTO dto) {	
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("category", dto.getCategory());
		parameterMap.put("ncs_cat", dto.getNcs_cat());
		parameterMap.put("company", dto.getCompany());
		return mapper.getCertiFilteredList(parameterMap);
	}
	
	@Override
	public List<CertiInfoDTO> getreqList(String req_age,String req_degree,String req_exp){
		return mapper.getreqList(req_age, req_degree, req_exp);
	}
	@Override
	public int count(String cnum, String memid) {
		return mapper.likeCheck(cnum,memid);
	}
	
	@Override
	public List<String> getLikeList(String memid){
		return mapper.getLikeList(memid);
	}
	
	@Override
	public List<Map<String, Object>> getNcsCodeList() {
		return mapper.getNcsCodeList();
	}
	
	@Override
	public int getCertiFilteredCnt(CertiFilterDTO dto) {
		return mapper.getCertiFilteredCnt(dto);
	}
	
	@Override
	public List<String> getNcsName(CertiFilterDTO dto) {
		return mapper.getNcsName(dto);
	}

}
