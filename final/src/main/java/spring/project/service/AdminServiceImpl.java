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
import spring.project.model.EmpBoardDTO;
import spring.project.model.CertiRequirementDTO;
import spring.project.model.MemberFilterDTO;
import spring.project.model.MemberInfoDTO;
import spring.project.model.MemberReportDTO;
import spring.project.model.Post_BoardDTO;
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
		
		//?��????�� �? 0?���? 1�??�� ?��?��?���? ?���? (+1)
		if(mapper.findCurrseq(sequence)==0) {
			mapper.findNextseq(sequence);
		}
		
		cnum += String.format("%05d", mapper.findCurrseq(sequence));
		
		info.setCnum(cnum); 
		schedule.setCnum(cnum);
		requirement.setCnum(cnum);
		//CSV���� ���� ������ ���� ��� ���
		if(certiDate != null) {
			certiDate.setCnum(cnum);
			
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
	
	//�ڰ��� �˻� ���
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
	
	
	//�ڰ��� ������
	@Override
	public Map<String, CertiAccessible> getCertiInfo(String cnum) {
		CertiInfoDTO info = mapper.getCertiInfo(cnum);
		CertiRequirementDTO requirement = mapper.getCertiReqInfo(cnum);
		
		certiMap.put("info", info);
		certiMap.put("req", requirement);
		return certiMap;
	}
	
	//�ڰ��� ������
	@Override
	public List<CertiDateDTO> searchPeriod(String cnum){
		return mapper.searchPeriod(cnum);
	}
	@Override
	public List<CertiDateDTO> searchNatPeriod(String cnum){
		//��������ڰ��� ��� CertiSchedule������ �Ѱܼ� �������� ���� ��������
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
	
	//�ڰ��� ���� �߰�
	@Override
	public int addCertiDate(CertiDateDTO dto) {
		return mapper.addCertiDate(dto);
	}
	
	//�ڰ��� ���� ���� 
	@Override
	public int deleteCertiDate(String[] dateList) {
		int[] date = new int[dateList.length];
		for(int i=0;i<dateList.length;i++) {
			int d = Integer.parseInt(dateList[i]);
			date[i] = d;
		}
		return mapper.deleteCertiDate(date);
	}
	
	//�ڰ��� ���� ����
	@Override
	public CertiDateDTO getCertiDate(int datePK) {
		return mapper.getCertiDate(datePK);
	}

	@Override
	public int modCertiDate(CertiDateDTO dto) {
		return mapper.modCertiDate(dto);
	}
	
	@Override
	public int delCerti(String cnum, String name) {
		String status = "D-"+name;
		return mapper.delCerti(status, cnum);
	}
	
	@Transactional
	@Override
	public int modCerti(CertiInfoDTO info, CertiRequirementDTO req) {
		int result = mapper.modCertiInfo(info);
		result += mapper.modCertiReq(req);
		return result;
	}
	
	@Override
	public List<MemberInfoDTO> getMemberList(PagingDTO page, Integer status) {
		map.put("startRow", page.getStartRow());
		map.put("endRow", page.getEndRow());
		map.put("status", status);
		
		return mapper.getMemberList(map);
	}

	@Override
	public int getMemberCnt(Integer status) {
		return mapper.getMemberCnt(status);
	}
	
	@Override
	public String getMemStatusName(Integer status) {
		return mapper.getMemStatusName(status);
	}

	@Override
	public List<CertiInfoDTO> getMemberCertList(String memid){
		return mapper.getMemberCertList(memid);
	}
	@Override
	public List<CertiInfoDTO> getMemberLikeList(String memid){
		return mapper.getMemberLikeList(memid);
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
	public List<MemberInfoDTO> getReportMemList(Integer status) {
		return mapper.getReportMemList(status);
	}
	@Override
	public int getReportMemCnt(Integer status) {
		return mapper.getReportMemCnt(status);
	}
	
	
	@Override
	public List<Map<String, Object>> getreportMemInfo(String memid) {
		return mapper.getreportMemInfo(memid);
	}
	@Override
	public List<MemberReportDTO> getReportReasonList(int pnum){
		return mapper.getReportReasonList(pnum);
	}
	

	@Override
	public MemberInfoDTO getMemberInfo(String memid) {
		MemberInfoDTO dto = mapper.getMemberInfo(memid);
		
		if(dto.getBirthday()!=null) {
			dto.setBirthday(dto.getBirthday().split(" ")[0]);
		}
		return dto;
	}
	
	@Override
	public int getMemberAge(String memid) {
		int age = 0;
		
		String birthY = mapper.getMemberInfo(memid).getBirthday();
		int birthYear = 0;
		if(birthY != null) birthYear = Integer.parseInt(birthY.substring(0, 4));
		
		Calendar cal = Calendar.getInstance();
		int currentYear = cal.get(Calendar.YEAR);
		
		age = currentYear - birthYear;
		return age;
	}
	
	@Override
	public int updateRepMemStatus(String memid, String status) {
		return mapper.updateRepMemStatus(memid, status);
	}

	@Override
	public List<Post_BoardDTO> getNewRequestList(PagingDTO page) {
		return mapper.getNewRequestList(page);
	}

	@Override
	public int getNewRequestCnt() {
		return mapper.getNewRequestCnt();
	}

	@Override
	public Map<String,Integer> getNewMemberData() {
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("memberToday", mapper.getMemberTodayCnt());
		map.put("memberLastWeek", mapper.getMemberLastWeekCnt());
		return map;
	}

	@Override
	public int getNewCertiCnt() {
		return mapper.getNewCertiCnt();
	}

	@Override
	public int getNewReportCnt() {
		return mapper.getNewReportCnt();
	}

	@Override
	public List<Post_BoardDTO> getBoardList(PagingDTO page, Integer status, Integer board_type) {
		map.put("startRow", page.getStartRow());
		map.put("endRow", page.getEndRow());
		
		map.put("status", status);
		map.put("board_type", board_type);
		return mapper.getBoardList(map);
	}
	@Override
	public int getBoardCnt(Integer status, Integer board_type) {
		return mapper.getBoardCnt(status, board_type);
	}

	@Override
	public List<Post_BoardDTO> getBoardSearchList(PagingDTO page, Integer board_type, String search, String keyword) {
		List<Post_BoardDTO> list = null;
		
		map.put("startRow", page.getStartRow());
		map.put("endRow", page.getEndRow());
		map.put("keyword", keyword);
		map.put("board_type", board_type);
		
		if(search.equals("both")) {
			list = mapper.getBoardSearchBoth(map);
		}else if(search.equals("writer")) {
			list = mapper.getBoardSearchWriter(map);
		}
		
		return list;
	}

	@Override
	public int getBoardSearchCnt(Integer board_type, String search, String keyword) {
		int result = 0;
		map.put("keyword", keyword);
		map.put("board_type", board_type);
		
		if(search.equals("both")) {
			result = mapper.getSearchBothCnt(map);
		}else if(search.equals("writer")) {
			result = mapper.getSearchWriterCnt(map);
		}
		return result;
	}

	@Override
	public List<EmpBoardDTO> getEmpNoticeList(PagingDTO page) {
		return mapper.getEmpNoticeList(page);
	}

	@Override
	public int getEmpNoticeCnt() {
		return mapper.getEmpNoticeCnt();
	}

	@Override
	public int addEmpNotice(EmpBoardDTO dto) {
		return mapper.addEmpNotice(dto);
	}

	@Override
	public EmpBoardDTO getEmpNotice(int ebnum) {
		return mapper.getEmpNotice(ebnum);
	}
	@Override
	public void updateReadCnt(int ebnum) {
		mapper.updateReadCnt(ebnum);
	}
	
	@Override
	public int modEmpNotice(EmpBoardDTO dto) {
		return mapper.modEmpNotice(dto);
	}

	@Override
	public int delEmpNotice(int ebnum) {
		return mapper.delEmpNotice(ebnum);
	}

	



}
