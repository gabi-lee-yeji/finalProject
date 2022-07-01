package spring.project.service;

import java.lang.reflect.Field;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.project.mapper.AdminMapper;
import spring.project.model.CertiAccessible;
import spring.project.model.CertiDateDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.CertiScheduleDTO;
import spring.project.model.CertiRequirementDTO;
import spring.project.model.MemberFilterDTO;
import spring.project.model.MemberInfoDTO;
import spring.project.pagination.PagingDTO;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	AdminMapper mapper;
	static Map<String, Object> map = new HashMap<String,Object>();
	static Map<String, CertiAccessible> certiMap = new HashMap<String, CertiAccessible>();
	static Map<String, Object> memberMap = new HashMap<String,Object>();
	static List<Object> list = new ArrayList<Object>();
	
	@Transactional
	@Override
	public int addCertiInfo(CertiInfoDTO info, CertiScheduleDTO schedule, 
							CertiDateDTO certiDate, CertiRequirementDTO requirement) {
		String cnum = "";
		String sequence = "";
		
		if(info.getCategory().equals("국가기술")) {
			cnum = "N";
			sequence = "NAT_SEQ";
		}else if(info.getCategory().equals("공인민간")) {
			cnum = "P";
			sequence = "PRV_SEQ";
		}else if(info.getCategory().equals("어학")) {
			cnum = "L";
			sequence = "LANG_SEQ";
		}
		
		//시퀀스값이 0인 경우 초기값 설정 (+1)
		if(mapper.findCurrseq(sequence)==0) {
			mapper.findNextseq(sequence);
		}
		
		cnum += String.format("%05d", mapper.findCurrseq(sequence));
		
		info.setCnum(cnum); 
		schedule.setCnum(cnum);
		requirement.setCnum(cnum);
		//CSV에서 직접 데이터 넣을 경우 대비
		if(certiDate != null) {
			certiDate.setCnum(cnum);
			
			String cyearStr = certiDate.getDocTestStart().split("-")[0];
			//view에서 국가기술을 등록할 경우 대비 - certiDate가 전부 null
			if(cyearStr!=null && cyearStr != "") {
				int cyear = Integer.parseInt(cyearStr);
				certiDate.setCyear(cyear);
			}
		}
		
		int result = 0;
		if(info.getCategory().equals("국가기술")) {
			result += mapper.addCertiInfo(info);
			result += mapper.addCertiSchedule(schedule);
			if(result==2) mapper.findNextseq(sequence);
		}else {
			result += mapper.addCertiInfo(info);
			result += mapper.addCertiDate(certiDate);
			result += mapper.addCertiReq(requirement);
			if(result==3) mapper.findNextseq(sequence);
		}
		return result;
	}

//	@Override
//	public int modCerti(String cnum, CertiInfoDTO info, CertiDetailDTO detail) {
//		info.setCnum(cnum); detail.setCnum(cnum);
//		int result = mapper.modCertInfo(info);
//		System.out.println("===info==="+result);
//		//result += mapper.modCertDetail(info);
//		System.out.println("===detail==="+result);
//		return result;
//	}

	@Override
	public List<CertiInfoDTO> getCertList(PagingDTO page, String sort, String order, String category) {
		map.put("startRow", page.getStartRow());
		map.put("endRow", page.getEndRow());
		map.put("sort", sort);
		map.put("order", order);
		map.put("category", category);
		System.out.println(map);
		return mapper.getCertList(map);
	}
	@Override
	public int getCertCnt() {
		return mapper.getCertCnt();
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
	public List<CertiDateDTO> searchPeriod(Map<String, String> map){
		//국가기술자격인 경우 CertiSchedule정보를 넘겨서 일정정보 먼저 가져오기
		String cnum = map.get("cnum");
		if(cnum.substring(0,1).equals("N")) {
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
		
		return mapper.searchPeriod(map);
	}
	@Override
	public List<CertiDateDTO> searchNatPeriod(String cnum){
		//국가기술자격인 경우 CertiSchedule정보를 넘겨서 일정정보 먼저 가져오기
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
	public List<CertiInfoDTO> getSearchList(PagingDTO page, String search, String keyword) {
		int startRow = page.getStartRow();
		int endRow = page.getEndRow();
		return mapper.getSearchList(startRow, endRow, search, keyword);
	}

	@Override
	public int getSearchCnt(String search, String keyword) {
		return mapper.getSearchCnt(search, keyword);
	}

	@Override
	public List<CertiInfoDTO> getDelList(String[] cnumList) {
		return mapper.getDelList(cnumList);
	}
	
	@Transactional
	@Override
	public int delCerti(String[] cnumList) {
		int result = mapper.delCertiInfo(cnumList);
		System.out.println("==info=="+result);
		result += mapper.delCertiDetail(cnumList);
		System.out.println("==detail=="+result);
		return result;
	}

	@Override
	public List<MemberInfoDTO> getMemberList(PagingDTO page, String sort, String order) {
		map.put("startRow", page.getStartRow());
		map.put("endRow", page.getEndRow());
		map.put("sort", sort);
		map.put("order", order);
		
		return mapper.getMemberList(map);
	}

	@Override
	public int getMemberCnt() {
		return mapper.getMemberCnt();
	}

	@Override
	public List<MemberInfoDTO> getMemberFilter(MemberFilterDTO filter, PagingDTO page) {
		map.put("startRow", page.getStartRow());
		map.put("endRow", page.getEndRow());
		
		map.put("search", filter.getSearch());
		map.put("keyword", filter.getKeyword());
		map.put("status", filter.getStatus());
		map.put("mem_level", filter.getMem_level());
		map.put("mem_point1", filter.getMem_point1());
		map.put("mem_point2", filter.getMem_point2());
		map.put("regDate1", filter.getRegDate1());
		map.put("regDate2", filter.getRegDate2());
		
		
		
		return mapper.getMemberFilter(map);
	}

	@Override
	public List<MemberInfoDTO> getMemberReport(String status) {
		return mapper.getReportMemList(status);
	}

	@Override
	public List<Map<String, Object>> getreportMemInfo(String memid) {
		return mapper.getreportMemInfo(memid);
	}

	@Override
	public MemberInfoDTO getMemberInfo(String memid) {
		return mapper.getMemberInfo(memid);
	}

	@Override
	public int updateRepMemStatus(String memid, String status) {
		return mapper.updateRepMemStatus(memid, status);
	}

	
}
