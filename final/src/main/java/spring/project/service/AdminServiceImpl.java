package spring.project.service;

import java.lang.reflect.Field;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.project.mapper.AdminMapper;
import spring.project.model.CertiDateDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.CertiScheduleDTO;
import spring.project.model.MemberFilterDTO;
import spring.project.model.MemberInfoDTO;
import spring.project.pagination.PagingDTO;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	AdminMapper mapper;
	
	static Map<String, Object> map = new HashMap<String,Object>();
	
	@Transactional
	@Override
	public int addCertiInfo(CertiInfoDTO info, CertiScheduleDTO schedule, CertiDateDTO certiDate) {
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
		
		//시퀀스 값 0이면 1부터 시작하게 올림 (+1)
		if(mapper.findCurrseq(sequence)==0) {
			mapper.findNextseq(sequence);
		}
		
		cnum += String.format("%05d", mapper.findCurrseq(sequence));
		
		info.setCnum(cnum); 
		if(schedule != null) {
			schedule.setCnum(cnum);
		}
		//CSV에서 직접 데이터 넣을경우 ??
		if(certiDate != null) {
			certiDate.setCnum(cnum);
			
		}
		
		int result = 0;
		if(info.getCategory().equals("국가기술")) {
			result += mapper.addCertiInfo(info);
			result += mapper.addCertiSchedule(schedule);
		}else {
			result += mapper.addCertiInfo(info);
			result += mapper.addCertiDate(certiDate);
		}
		
		if(result==2) mapper.findNextseq(sequence);
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
	public List<CertiInfoDTO> getCertList(PagingDTO page, String sort, String order) {
		int startRow = page.getStartRow();
		int endRow = page.getEndRow();
		return mapper.getCertList(startRow, endRow, sort, order);
	}
	@Override
	public int getCertCnt() {
		return mapper.getCertCnt();
	}
	

	@Override
	public List<Object> getCertiInfo(String cnum) {
		List<Object> list = new ArrayList<Object>();
		
		CertiInfoDTO info = mapper.getCertiInfo(cnum);
		CertiDateDTO date = null;
		
		//��������ڰ��� ��� CertiSchedule������ �Ѱܼ� �������� ��������
		if(cnum.substring(0,1).equals("N")) {
			CertiScheduleDTO schedule = mapper.getQnetDateInfo(cnum);
			date = mapper.getQnetDate(schedule);
		}else {
			date = mapper.getCertiDate(cnum);
		}
		list.add(info);
		list.add(date);
		
		return list;
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
