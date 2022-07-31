package spring.project.service;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
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
import spring.project.model.EmpInfoDTO;
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
		
		//자격증 종류에 따라서 자격증 일련번호 앞자리 알파벳 설정
		if(info.getCategory().equals("national")) {
			cnum = "N";
			sequence = "NAT_SEQ";
		}else if(info.getCategory().equals("private")) {
			cnum = "P";
			sequence = "PRV_SEQ";
		}else if(info.getCategory().equals("language")) {
			cnum = "L";
			sequence = "LANG_SEQ";
		}
		
		//currval이 0인 경우 시퀀스 +1 
		if(mapper.findCurrseq(sequence)==0) {
			mapper.findNextseq(sequence);
		}
		
		//자격증 번호 생성 (알파벳+숫자5자리)
		cnum += String.format("%05d", mapper.findCurrseq(sequence));
		
		info.setCnum(cnum); 
		schedule.setCnum(cnum);
		requirement.setCnum(cnum);
		
		//자격증일정이 입력됐는지 체크후 certiDate에도 cnum 설정
		if(certiDate != null) {
			certiDate.setCnum(cnum);
		}
		
		int result = 0;
		
		//국가자격증 - certiinfo / certischedule(큐넷일정) 에만 insert
		//민간,어학 - certiinfo / certidate(상세일정) 에 insert
		if(info.getCategory().equals("national")) {
			result += mapper.addCertiInfo(info);
			result += mapper.addCertiSchedule(schedule);
			if(result==2) mapper.findNextseq(sequence);
		}else {
			result += mapper.addCertiInfo(info);
			result += mapper.addCertiDate(certiDate);
			
			//입력된 certirequirement(응시자격)이 있을 경우 insert
			if(requirement != null) {
				result += mapper.addCertiReq(requirement);
			}
			if(result>=2) mapper.findNextseq(sequence);
		}
		System.out.println("===자격증등록==="+result);
		return result;
	}

	@Override
	public List<CertiInfoDTO> getCertList(PagingDTO page, String sort, String order, String category) {
		//자격증 목록 조회 전 유효날짜에 따라 시행현황 업데이트
		mapper.updateCertiStatus();
		
		map.put("startRow", page.getStartRow());
		map.put("endRow", page.getEndRow());
		map.put("sort", sort);
		map.put("order", order);
		map.put("category", category);
		return mapper.getCertList(map);
	}
	@Override
	public int getCertCnt(String category) {
		return mapper.getCertCnt(category);
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
	
	
	//자격증 상세정보 조회
	@Override
	public CertiInfoDTO getCertiInfo(String cnum) {
		return mapper.getCertiInfo(cnum);
	}
		
	//자격증 응시자격 조회 - 응시자격은 여러개일 수 있어서 분리함
	@Override 
	public List<CertiRequirementDTO> getCertiReqList(String cnum) {
		return mapper.getCertiReqList(cnum);
	}
	
	//공인민간, 어학 자격증 상세일정 조회
	@Override
	public List<CertiDateDTO> searchPeriod(String cnum){
		return mapper.searchPeriod(cnum);
	}
	@Override
	public List<CertiDateDTO> searchNatPeriod(String cnum){
		List<CertiDateDTO> list = null;
		
		List<Integer> cyear_list = new ArrayList<Integer>();
		List<Integer> cround_list = new ArrayList<Integer>();
		//CertiSchedule에 등록된 국가자격증 회차 정보 조회
		List<CertiScheduleDTO> schedule = mapper.getQnetDateInfo(cnum);
		
		//조회된 회차 정보가 있을 경우만 상세일정 조회
		if(schedule.size() > 0) {
			for(CertiScheduleDTO dto : schedule ) {
				cyear_list.add(dto.getCyear());
				cround_list.add(dto.getCround());
			}
			String clevel = schedule.get(0).getClevel();
			list = mapper.searchNatPeriod(clevel, cyear_list, cround_list);
		}
	
		return list;
	}
	
	//자격증 상세일정 추가
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
	@Override
	public int deleteCertiNatDate(String[] dateList, String cnum) {
		int result = 0;
		for(String s : dateList) {
			String[] cons = s.split("@");
			int cyear = Integer.parseInt(cons[0]);
			int cround = Integer.parseInt(cons[1]);
			CertiScheduleDTO dto = new CertiScheduleDTO(cnum, cyear, cround, cons[2]);
			
			result += mapper.deleteCertiNatDate(dto);
		}
		return result;
	}
	
	//�ڰ��� ���� ����
	@Override
	public CertiDateDTO getCertiDate(int datePK) {
		return mapper.getCertiDate(datePK);
	}
	@Override
	public List<CertiInfoDTO> getNatSameScheduleList(int datepk, PagingDTO page){
		CertiDateDTO date = mapper.getCertiDate(datepk);
		map.put("startRow", page.getStartRow());
		map.put("endRow", page.getEndRow());
		map.put("cyear", date.getCyear());
		map.put("cround", date.getCround());
		map.put("clevel", date.getClevel());
		return mapper.getNatSameScheduleList(map);
	}
	@Override
	public int getNatSameCnt(int datepk) {
		return mapper.getNatSameCnt(datepk);
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
	public int modCerti(CertiInfoDTO info) {
		return mapper.modCertiInfo(info);
	}
	
	@Override
	public void updateMemberStatus() {
		//휴면회원 전환 (1년 이상 미접속 회원)
		mapper.updateToSleep();
		//활동중지 해제 (활동중지된 후 1주일 지난 회원)
		mapper.updateFromBlock();
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
	public List<MemberInfoDTO> getMemberSearchList(String search, String keyword, PagingDTO page) {
		map.put("startRow", page.getStartRow());
		map.put("endRow", page.getEndRow());
		map.put("search", search);
		map.put("keyword", keyword);
		
		return mapper.getMemberSearchList(map);
	}
	@Override
	public int getMemberSearchCnt(String search, String keyword) {
		return mapper.getMemberSearchCnt(search, keyword);
	}
	/*
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
	*/
	@Override
	public List<MemberInfoDTO> getReportMemList(Integer status, PagingDTO page) {
		map.put("status", status);
		map.put("startRow", page.getStartRow());
		map.put("endRow", page.getEndRow());
		return mapper.getReportMemList(map);
	}
	@Override
	public int getReportMemCnt(Integer status) {
		return mapper.getReportMemCnt(status);
	}
	
	
	@Override
	public List<Map<String, Object>> getReportMemPosting(String memid) {
		return mapper.getReportMemPosting(memid);
	}
	@Override
	public int getReportMemPostingCnt(String memid) {
		return mapper.getReportMemPostingCnt(memid);
	}
	@Override
	public List<MemberReportDTO> getReportReasonList(int pnum){
		return mapper.getReportReasonList(pnum);
	}
	
	@Override
	public int getReportMemCommCnt(String memid) {
		return mapper.getReportMemCommCnt(memid);
	}
	@Override
	public List<Map<String, Object>> getReportMemComment(String memid) {
		return mapper.getReportMemComment(memid);
	}
	@Override
	public List<MemberReportDTO> getCommReportDetails(int pnum){
		return mapper.getCommReportDetails(pnum);
	}

	

	@Override
	public MemberInfoDTO getMemberInfo(String memid) {
		MemberInfoDTO dto = mapper.getMemberInfo(memid);
		
		if(dto!=null) {
			if(dto.getBirthday()!=null) {
				dto.setBirthday(dto.getBirthday().split(" ")[0]);
			}
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
	
	@Override
	public String getCurrentDate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate = sdf.format(date);
		return currentDate;
	}
	
	@Transactional
	@Override
	public int addEmpInfo(EmpInfoDTO dto) {
		int result = 0;
		//memberinfo의 status 관리자로 변경
		result += mapper.updateToAdmin(dto.getEmpid());
		//emp_info 에 insert
		result += mapper.addEmpInfo(dto);
		return result;
	}

	@Override
	public List<EmpInfoDTO> getEmpList(PagingDTO page, String empjob, String status, String sort, String order) {
		map.put("startRow", page.getStartRow());
		map.put("endRow", page.getEndRow());
		
		map.put("empjob", empjob);
		map.put("status", status);
		map.put("sort", sort);
		map.put("order", order);
		return mapper.getEmpList(map);
	}
	@Override
	public int getEmpCnt(String empjob, String status) {
		map.put("empjob", empjob);
		map.put("status", status);
		
		return mapper.getEmpCnt(map);
	}
	@Override
	public EmpInfoDTO getEmpInfo(String empid){
		return mapper.getEmpInfo(empid);
	}

	@Override
	public int modEmpInfo(EmpInfoDTO dto) {
		return mapper.modEmpInfo(dto);
	}
	
	@Transactional
	@Override
	public int delEmpInfo(String empid, String leavingReason) {
		int result = 0;
		result += mapper.delEmpInfo(empid, leavingReason);
		result += mapper.updateToMember(empid);
		return result;
	}

	@Override
	public int checkifMgr(String empid) {
		return mapper.checkifMgr(empid);
	}

	@Override
	public List<String> getEmpjobList() {
		return mapper.getEmpjobList();
	}

	@Override
	public List<String> getEmpStatusList() {
		return mapper.getEmpStatusList();
	}

	@Override
	public int getQuitCnt(String empjob) {
		return mapper.getQuitCnt(empjob);
	}
	@Override
	public int getQuitCnt_search(String search, String keyword) {
		map.put("search", search);
		map.put("keyword", keyword);
		return mapper.getQuitCnt_search(map);
	}

	@Override
	public List<EmpInfoDTO> getEmpSearchList(PagingDTO page, String search, String keyword) {
		map.put("startRow", page.getStartRow());
		map.put("endRow", page.getEndRow());
		map.put("search", search);
		map.put("keyword", keyword);
		return mapper.getEmpSearchList(map);
	}

	@Override
	public int getEmpSearchCnt(String search, String keyword) {
		map.put("search", search);
		map.put("keyword", keyword);
		return mapper.getEmpSearchCnt(map);
	}

	@Override
	public int delReportComment(Integer[] comm_num) {
		int result = 0;
		for(int c : comm_num) {
			result += mapper.delReportComment(c);
		}
		return result;
	}

	@Override
	public int checkIfEmp(String memid) {
		return mapper.checkIfEmp(memid);
	}

}
