package spring.project.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import google.analytics.reportingAPI.AnalyticsService;
import spring.project.model.CertiAccessible;
import spring.project.model.CertiDateDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.CertiRequirementDTO;
import spring.project.model.CertiScheduleDTO;
import spring.project.model.MemberFilterDTO;
import spring.project.model.MemberInfoDTO;
import spring.project.pagination.PagingDTO;
import spring.project.pagination.PagingService;
import spring.project.service.AdminService;
import spring.project.service.MemberService;

@Controller
@RequestMapping("/admin/*")
public class AdminController {
	
	@Autowired
	private AdminService service;
	
	@Autowired
	private MemberService memService;
	
	@Autowired
	private PagingService pageService;
	
	@Autowired
	private AnalyticsService gaService;
	
	static Map<String, Object> paramMap = new HashMap<String, Object>();
	
	//자격증 등록 페이지 
	@RequestMapping("addCerti")
	public String addCerti() {
		return "admin/certi/addCerti";
	}
	@RequestMapping("addCertiPro")
	public String addCertiPro(CertiInfoDTO info, CertiScheduleDTO schedule, 
								CertiDateDTO certiDate, CertiRequirementDTO requirement,
								Model model) {
		int result = service.addCertiInfo(info, schedule, certiDate, requirement);
		model.addAttribute("category", info.getCategory());
		model.addAttribute("result", result);
		return "admin/certi/addCertiPro";
	}
	
	//자격증 목록 페이지 
	@RequestMapping("certiList")
	public String getCertiList(String pageNum, String sort, String order, String category, Model model) {
		//한 페이지에 보여주고 싶은 게시글수 매개변수로 전달
		int pageSize = 30;
		PagingDTO page = pageService.getPaging(pageSize, pageNum);
		
		model.addAttribute("list", service.getCertList(page, sort, order, category));
		model.addAttribute("count", service.getCertCnt());
		
		model.addAttribute("page",page);
		model.addAttribute("sort", sort);
		model.addAttribute("order", order);
		model.addAttribute("category", category);
		return "admin/certi/certiList";
	}
	
	//자격증 검색기능 (결과페이지)
	@RequestMapping("search")
	public String searchList(String pageNum, String search, String keyword, Model model) {
		PagingDTO page = pageService.getPaging(30, pageNum);
		model.addAttribute("page", page);
		
		model.addAttribute("search", search);
		model.addAttribute("keyword", keyword);
		model.addAttribute("count", service.getSearchCnt(search, keyword));
		model.addAttribute("list", service.getSearchList(page, search, keyword));
	
		return "/admin/certi/searchList";
	}
	
	//자격증 수정 - 상세정보 확인가능 
	@RequestMapping("certiInfo")
	public String certiInfo(String cnum, Model model) {
		Map<String, CertiAccessible> map = service.getCertiInfo(cnum);
		model.addAttribute("cnum", cnum);
		model.addAttribute("info", map.get("info"));
		model.addAttribute("req", map.get("requirement"));
		return "admin/certi/certiInfo";
	}
	
	//자격증별 상세일정 목록
	@RequestMapping("certiDate")
	public String certiDateInfo(String cnum, Model model) {
		List<CertiDateDTO> dateList = null;
		if(cnum.substring(0, 1).equals("N")) {
			dateList = service.searchNatPeriod(cnum);
		}else {
			dateList = service.searchPeriod(cnum);
		}
	
		model.addAttribute("dateList", dateList);
		model.addAttribute("info",service.getCertiInfo(cnum).get("info"));
		
		return "admin/certi/certiDate";
	}
	//자격증 일정 추가
	@RequestMapping("certi/addDate")
	public String addDate(String cnum, Model model) {
		Map<String, CertiAccessible> map = service.getCertiInfo(cnum);
		model.addAttribute("info", map.get("info"));
		return "admin/certi/addDate";
	}
	@RequestMapping("certi/addDatePro")
	public String addDate(CertiDateDTO dto, Model model) {
		model.addAttribute("result", service.addCertiDate(dto));
		return "admin/certi/addDatePro";
	}
	
	//자격증 일정 삭제 
	@RequestMapping("certi/deleteDate")
	public String deleteDate(String cnum, String[] dateList, Model model){
		model.addAttribute("cnum", cnum);
		model.addAttribute("result",service.deleteCertiDate(dateList));
		return "admin/certi/deleteDate";
	}
	
	@RequestMapping("certi/modDate")
	public String modifyDate(String datepk, String cnum, Model model) {
		if(datepk ==null) return "admin/certi/certiDate?cnum="+cnum;
		
		if(cnum.startsWith("N")) return "국가기술 일정 테이블로 보내기?";
		
		int datePK = Integer.parseInt(datepk);
		model.addAttribute("dto", service.getCertiDate(datePK));
		return "admin/certi/modDate";
	}
	@RequestMapping("certi/modDatePro")
	public String modifyDatePro(CertiDateDTO dto, Model model) {
		System.out.println("DATEPK========"+dto.getDatePK());
		System.out.println("CNUM========"+dto.getCnum());
		model.addAttribute("result", service.modCertiDate(dto));
		model.addAttribute("cnum", dto.getCnum());
		return "admin/certi/modDatePro";
	}
	
	//자격증 정보 삭제 (update status)
	@RequestMapping("certi/deleteForm")
	public String deleteForm(String cnum, MemberInfoDTO dto, Model model) {
		//삭제하기 전 선택한 자격증 및 권한 확인
		model.addAttribute("dto", service.getCertiInfo(cnum).get("info"));
		return "admin/certi/deleteForm";
	}
	@RequestMapping("deletePro")
	public String deletePro(String cnum, String name, MemberInfoDTO dto, Model model) {
		//ID || ps 미입력시 유효성 검사 (2차) -> view에서도 빈칸 체크하기! 
		if(dto.getMemid()==null || dto.getPasswd()==null) return "member/loginForm";
		
		//입력한 ID가 관리자 ID인지 
		if(dto.getMemid().contains("admin")) {
			//id, pw 체크
			if(memService.userCheck(dto)==1) {
				model.addAttribute("result",service.delCerti(cnum, name));
			}
		}else {
			return "member/loginForm";
		}
		return "admin/certi/deletePro";
	}
	
	@RequestMapping("modCertiPro")
	public String modCerti(CertiInfoDTO info, CertiRequirementDTO req, Model model) {
		
		return "/admin/certi/modCertiPro";
	}
	
	@RequestMapping("member/list")
	public String getMemberList(String pageNum, String sort, String order, Model model) {
		PagingDTO page = pageService.getPaging(30, pageNum);
		model.addAttribute("page",page);
		model.addAttribute("count",service.getMemberCnt());
		model.addAttribute("list", service.getMemberList(page, sort, order));
		return "/admin/member/list";
	}
	
	
	@RequestMapping("member/filter")
	public String memberFilter() {
		return "/admin/member/memberFilter";
	}
	
	@RequestMapping("member/filterPro")
	public String getSearchList(MemberFilterDTO dto, String pageNum, Model model) {
		PagingDTO page = pageService.getPaging(10, pageNum);
		List<MemberInfoDTO> list = service.getMemberFilter(dto, page);
		model.addAttribute("list", list);
		return "/admin/member/searchList";
	}
	
	@RequestMapping("member/reportList")
	public String getMemberReport(String status, Model model) {
		model.addAttribute("status", status);
		model.addAttribute("list", service.getReportMemList(status));
		return "/admin/member/reportList";
	}
	
	@RequestMapping("member/reportMemInfo")
	public String getReportMemInfo(String memid, String reportCnt, Model model) {
		model.addAttribute("memid", memid);
		model.addAttribute("reportCnt", reportCnt);
		model.addAttribute("dto", service.getMemberInfo(memid));
		model.addAttribute("list", service.getreportMemInfo(memid));
		return "/admin/member/reportMemInfo";
	}
	
	@RequestMapping("member/memReportPro")
	public String modReportMember(String memid, String status, Model model) {
		model.addAttribute("result", service.updateRepMemStatus(memid, status));
		return "/admin/member/memReportPro";
	}
	
	@RequestMapping("board/request")
	public String getUserRequestList(String PageNum, Model model) {
		PagingDTO page = pageService.getPaging(20, PageNum);
		model.addAttribute("page",page);
		model.addAttribute("count",service.getNewRequestCnt());
		model.addAttribute("list", service.getNewRequestList(page));
		return "/admin/board/request";
	}
	
	
	@RequestMapping("main")
	public String adminMain(Model model) {
		model.addAttribute("newCertCnt", service.getNewCertiCnt());
		model.addAttribute("newReportCnt",service.getNewReportCnt());
		
		PagingDTO page = pageService.getPaging(5, null);
		model.addAttribute("certList",service.getCertList(page, "registDate", "desc", null));
		model.addAttribute("memReportList", service.getReportMemList("일반"));
		
		return "/admin/main";
	}
	@RequestMapping("ajax/newMember")
	public String adminMainMember(Model model) {
		model.addAttribute("map", service.getNewMemberData());
		
		return "/admin/ajax/newMember";
	}
	@RequestMapping("ajax/newRequest")
	public String adminMainRequest(Model model) {
		PagingDTO page = pageService.getPaging(5, null);
		model.addAttribute("reqList",service.getNewRequestList(page));
		model.addAttribute("count", service.getNewRequestCnt());
		return "/admin/ajax/newRequest";
	}
	@RequestMapping("ajax/visitor")
	public String adminMainVisitor() {
		//구글 통계에서 방문자수 조회해서 view로 보내기
		return "/admin/ajax/visitor";
	}
	
	//ga tester
	@RequestMapping("test")
	public String googleTest(Model model) throws Exception {
		String start = "7daysAgo";
		String today = "today";
		//구글 통계에서 방문자수 조회해서 view로 보내기
		model.addAttribute("usersToday", gaService.getUsersStats(start, today));
		model.addAttribute("users7Days", gaService.getUsersStats(today, today));
		return "/admin/stats/test";
	}
}
