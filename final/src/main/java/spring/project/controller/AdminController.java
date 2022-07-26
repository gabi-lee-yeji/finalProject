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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
import spring.project.model.EmpBoardDTO;
import spring.project.model.EmpInfoDTO;
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
	
	//?κ²©μ¦ ?±λ‘? ??΄μ§? 
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
	
	//?κ²©μ¦ λͺ©λ‘ ??΄μ§?
	@RequestMapping("certiList")
	public String getCertiList(String pageNum, String sort, String order, String category, Model model) {
		//? ??΄μ§?? λ³΄μ¬μ£Όκ³  ?Ά?? κ²μκΈ?? λ§€κ°λ³??λ‘? ? ?¬
		int pageSize = 30;
		PagingDTO page = pageService.getPaging(pageSize, pageNum);
		model.addAttribute("list", service.getCertList(page, sort, order, category));
		model.addAttribute("count", service.getCertCnt(category));
		
		model.addAttribute("page",page);
		model.addAttribute("sort", sort);
		model.addAttribute("order", order);
		model.addAttribute("category", category);
		return "admin/certi/certiList";
	}
	
	//?κ²©μ¦ κ²??κΈ°λ₯ (κ²°κ³Ό??΄μ§?)
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
	
	//?κ²©μ¦ ??  - ??Έ? λ³? ??Έκ°??₯ 
	@RequestMapping("certiInfo")
	public String getCertiInfo(String cnum, Model model) {
		Map<String, CertiAccessible> map = service.getCertiInfo(cnum);
		model.addAttribute("cnum", cnum);
		model.addAttribute("info", map.get("info"));
		model.addAttribute("req", map.get("requirement"));
		return "admin/certi/certiInfo";
	}
	
	//?κ²©μ¦λ³? ??Έ?Ό?  λͺ©λ‘
	@RequestMapping("certiDate")
	public String getcertiDateInfo(String cnum, Model model) {
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
	//?κ²©μ¦ ?Ό?  μΆκ?
	@RequestMapping("certi/addDate")
	public String addDate(String cnum, Model model) {
		Map<String, CertiAccessible> map = service.getCertiInfo(cnum);
		model.addAttribute("info", map.get("info"));
		return "admin/certi/addDate";
	}
	@RequestMapping("certi/addDatePro")
	public String addDate(HttpServletRequest request, String cnum, String cname, String clevel, Integer count, Model model) {
		int result = 0;
		
		for(int i=1;i<=count;i++) {
			int cyear = Integer.parseInt(request.getParameter("cyear"+i));
			int cround = Integer.parseInt(request.getParameter("cround"+i));
			CertiDateDTO dto = new CertiDateDTO(
						cnum,
						cyear,
						cround,
						clevel,
						request.getParameter("docRegStart1"+i),
						request.getParameter("docRegEnd1"+i),
						request.getParameter("docRegStart2"+i),
						request.getParameter("docRegEnd2"+i),
						request.getParameter("docTestStart"+i),
						request.getParameter("docTestEnd"+i),
						request.getParameter("docResultStart"+i),
						request.getParameter("docResultEnd"+i),
						request.getParameter("docSubmitStart"+i),
						request.getParameter("docSubmitEnd"+i),
						request.getParameter("pracRegStart1"+i),
						request.getParameter("pracRegEnd1"+i),
						request.getParameter("pracRegStart2"+i),
						request.getParameter("pracRegEnd2"+i),
						request.getParameter("pracTestStart"+i),
						request.getParameter("pracTestEnd"+i),
						request.getParameter("pracResStart"+i),
						request.getParameter("pracResEnd"+i)
					);
			System.out.println(dto);
			result += service.addCertiDate(dto);
		}
		
		model.addAttribute("result", result);
		model.addAttribute("cnum", cnum);
		model.addAttribute("cname", cname);
		return "admin/certi/addDatePro";
	}
	
	//?κ²©μ¦ ?Ό?  μΆκ? ??΄λΈ?
	@RequestMapping("certi/addDateTbl")
	public String ajaxDateTbl(String count, Model model) {
		model.addAttribute("count", count);
		return "admin/ajax/addDateTbl";
	}
	
	//?κ²©μ¦ ?Ό?  ?­?   
	@RequestMapping("certi/deleteDate")
	public String deleteDate(String cnum, String[] dateList, Model model){
		//κ΅?κ°??κ²©μ¦?Έ κ²½μ° ?Ό?  ?­?  ?  μ»¨ν ??΄μ§?λ‘? ?΄? 
		if(cnum.startsWith("N")) {
			//CertiSchedule?? ?΄?Ή ?κ²©μ¦ ?°?΄?° ?­?  
			model.addAttribute("result", service.deleteCertiNatDate(dateList, cnum));
		}else {
			//CertiDate?? ?Ό?  ? λ³? ?­?  (datePK ?¬?©) 
			model.addAttribute("result",service.deleteCertiDate(dateList));
		}
		
		model.addAttribute("cnum", cnum);
		
		return "admin/certi/deleteDate";
	}
	
	
	@RequestMapping("certi/modDate")
	public String modifyDate(Integer datepk, String cnum, Model model) {
		if(datepk ==null) return "admin/certi/certiDate?cnum="+cnum;
		
		if(cnum.startsWith("N")) model.addAttribute("count", service.getNatSameCnt(datepk));
		model.addAttribute("dto", service.getCertiDate(datepk));
		model.addAttribute("cnum", cnum);
		return "admin/certi/modDate";
	}
	@RequestMapping("certi/sameSchedule")
	public String getSameSchedule(Integer datepk, String pageNum, Model model) {
		PagingDTO page = pageService.getPaging(20, pageNum);
		model.addAttribute("page", page);
		model.addAttribute("natList", service.getNatSameScheduleList(datepk,page));
		model.addAttribute("count", service.getNatSameCnt(datepk));
		model.addAttribute("datepk", datepk);
		return "admin/certi/sameSchedule";
	}
	
	@RequestMapping("certi/modDatePro")
	public String modifyDatePro(CertiDateDTO dto, Model model) {
		model.addAttribute("result", service.modCertiDate(dto));
		model.addAttribute("cnum", dto.getCnum());
		return "admin/certi/modDatePro";
	}
	
	//?κ²©μ¦ ? λ³? ?­?  (update status)
	@RequestMapping("certi/deleteForm")
	public String deleteForm(String cnum, MemberInfoDTO dto, Model model) {
		//?­? ?κΈ? ?  ? ?? ?κ²©μ¦ λ°? κΆν ??Έ
		model.addAttribute("dto", service.getCertiInfo(cnum).get("info"));
		return "admin/certi/deleteForm";
	}
	@RequestMapping("deletePro")
	public String deletePro(String cnum, String name, MemberInfoDTO dto, Model model) {
		//ID || ps λ―Έμ? ₯? ? ?¨?± κ²??¬ (2μ°?) -> view??? λΉμΉΈ μ²΄ν¬?κΈ?! 
		if(dto.getMemid()==null || dto.getPasswd()==null) return "member/loginForm";
		
		//?? ₯? IDκ°? κ΄?λ¦¬μ ID?Έμ§? 
		if(dto.getMemid().contains("admin")) {
			//id, pw μ²΄ν¬
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
		model.addAttribute("result", service.modCerti(info, req));
		return "/admin/certi/modCertiPro";
	}
	
	@RequestMapping("member/list")
	public String getMemberList(String pageNum, Integer status, Model model) {
		PagingDTO page = pageService.getPaging(30, pageNum);
		model.addAttribute("page",page);
		model.addAttribute("count",service.getMemberCnt(status));
		model.addAttribute("list", service.getMemberList(page, status));
		model.addAttribute("status", status);
		
		if(status!=null)
			model.addAttribute("status_name", service.getMemStatusName(status));
		
		return "/admin/member/list";
	}
	
	@RequestMapping("member/info")
	public String getMemberInfo(String memid, Model model) {
		model.addAttribute("dto", service.getMemberInfo(memid));
		model.addAttribute("age", service.getMemberAge(memid));
		model.addAttribute("obtained", service.getMemberCertList(memid));
		model.addAttribute("liked", service.getMemberLikeList(memid));
		return "/admin/member/memberInfo";
	}
	/*
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
	*/
	@RequestMapping("member/search")
	public String getMemberSearchList(String search, String keyword, String pageNum, Model model) {
		PagingDTO page = pageService.getPaging(20, pageNum);
		model.addAttribute("page", page);
		model.addAttribute("list", service.getMemberSearchList(search, keyword, page));
		model.addAttribute("count", service.getMemberSearchCnt(search, keyword));
		model.addAttribute("search", search);
		model.addAttribute("keyword", keyword);
		return "/admin/member/searchList";
	}
	@RequestMapping("member/reportList")
	public String getMemberReport(Integer status, Model model) {
		model.addAttribute("status", status);
		if(status!=null)
			model.addAttribute("status_name", service.getMemStatusName(status));
		
		model.addAttribute("list", service.getReportMemList(status));
		model.addAttribute("count", service.getReportMemCnt(status));
		return "/admin/member/reportList";
	}
	
	@RequestMapping("member/reportMemInfo")
	public String getReportMemInfo(String memid, String reportCnt, Model model) {
		model.addAttribute("memid", memid);
		model.addAttribute("reportCnt", reportCnt);
		
		//? κ³ λΉ? ?? ? λ³?
		model.addAttribute("dto", service.getMemberInfo(memid));
		//? κ³ λΉ? ??? ? κ³ λ κΈ? λͺ©λ‘ μ‘°ν
		model.addAttribute("postList", service.getReportMemPosting(memid));
		model.addAttribute("postingCnt", service.getReportMemPostingCnt(memid));
		//? κ³ λΉ? ??? ? κ³ λ ?κΈ? λͺ©λ‘ μ‘°ν
		model.addAttribute("commList", service.getReportMemComment(memid));
		model.addAttribute("commCnt", service.getReportMemCommCnt(memid));
		return "/admin/member/reportMemInfo";
	}
	
	@RequestMapping("member/delComment")
	public String delReportComment(Integer[] comm_num, String reportCnt, String memid, Model model) {
		model.addAttribute("result", service.delReportComment(comm_num));
		model.addAttribute("reportCnt", reportCnt);
		model.addAttribute("memid", memid);
		return "/admin/member/delCommentPro";
	}
	
	@RequestMapping("member/commReport")
	public String getCommReportDetails(String memid, Integer pnum, Model model) {
		model.addAttribute("commNum", pnum);
		model.addAttribute("list", service.getCommReportDetails(pnum));
		return "/admin/member/commRepDetails";
	}
	
	@RequestMapping("member/memReportPro")
	public String modReportMember(String memid, String status, Model model) {
		model.addAttribute("result", service.updateRepMemStatus(memid, status));
		return "/admin/member/memReportPro";
	}
	
	@RequestMapping("board/reportDetails")
	public String getReportReason(int pnum, Model model) {
		model.addAttribute("list", service.getReportReasonList(pnum));
		return "/admin/board/reportReason";
	}
	
	@RequestMapping("board/list")
	public String getBoardList(String pageNum, Integer status, 
							Integer board_type, Model model) {
		PagingDTO page = pageService.getPaging(20, pageNum);
		model.addAttribute("list", service.getBoardList(page, status, board_type));
		model.addAttribute("count", service.getBoardCnt(status, board_type));
		model.addAttribute("page", page);
		
		model.addAttribute("status", status);
		model.addAttribute("board_type", board_type);
		return "/admin/board/list";
	}
	@RequestMapping("board/search")
	public String getBoardSearchList(String pageNum, Integer board_type, String search, String keyword, Model model) {
		PagingDTO page = pageService.getPaging(20, pageNum);
		
		model.addAttribute("board_type", board_type);
		model.addAttribute("search", search);
		model.addAttribute("keyword", keyword);
		
		model.addAttribute("list", service.getBoardSearchList(page, board_type, search, keyword));
		model.addAttribute("count",service.getBoardSearchCnt(board_type, search, keyword));
		model.addAttribute("page", page);
		return "/admin/board/search";
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
		model.addAttribute("memReportList", service.getReportMemList(0));
		model.addAttribute("empNotice", service.getEmpNoticeList(page));
		
		//???±κΈ? ??μ‘°μ  
		service.updateMemberStatus();
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
		//κ΅¬κ? ?΅κ³μ? λ°©λ¬Έ?? μ‘°ν?΄? viewλ‘? λ³΄λ΄κΈ?
		return "/admin/ajax/visitor";
	}
	
	//ga tester
	@RequestMapping("test")
	public String googleTest(Model model) throws Exception {
		String start = "7daysAgo";
		String today = "today";
		//κ΅¬κ? ?΅κ³μ? λ°©λ¬Έ?? μ‘°ν?΄? viewλ‘? λ³΄λ΄κΈ?
		model.addAttribute("usersToday", gaService.getUsersStats(today, today));
		model.addAttribute("users7Days", gaService.getUsersStats(start, today));
		return "/admin/stats/test";
	}
	
	
	//κ΄?λ¦¬μ - μ§μκ²μ? - μ§μκ³΅μ?
	@RequestMapping("emp/noticeList")
	public String getEmpNoticeList(String pageNum, Model model) {
		PagingDTO page = pageService.getPaging(10, pageNum);
		model.addAttribute("page", page);
		model.addAttribute("list", service.getEmpNoticeList(page));
		model.addAttribute("count", service.getEmpNoticeCnt());
		return "/admin/emp/board/noticeList";
	}
	@RequestMapping("emp/addNotice")
	public String addEmpNotice(HttpSession session, Model model){
		//model.addAttribute("id", session.getAttribute("memid"));
		model.addAttribute("id", "test");
		return "/admin/emp/board/noticeForm";
	}
	@RequestMapping("emp/addNoticePro")
	public String addEmpNoticePro(EmpBoardDTO dto, Model model){
		model.addAttribute("result", service.addEmpNotice(dto));
		return "/admin/emp/board/noticePro";
	}
	@RequestMapping("emp/notice")
	public String getEmpNotice(int ebnum, HttpSession session, Model model) {
		//model.addAttribute("id", session.getAttribute("memid"));
		model.addAttribute("id", "test");
		service.updateReadCnt(ebnum);  //μ‘°ν?+1
		model.addAttribute("dto",service.getEmpNotice(ebnum));
		return "/admin/emp/board/notice";
	}
	@RequestMapping("emp/modNotice")
	public String modEmpNotice(int ebnum, Model model) {
		model.addAttribute("dto",service.getEmpNotice(ebnum));
		return "/admin/emp/board/noticeForm";
	}
	@RequestMapping("emp/modNoticePro")
	public String modEmpNoticePro(EmpBoardDTO dto, Model model) {
		model.addAttribute("result", service.modEmpNotice(dto));
		return "/admin/emp/board/noticePro";
	}
	@RequestMapping("emp/delNotice")
	public String delEmpNoticePro(int ebnum, Model model) {
		model.addAttribute("result", service.delEmpNotice(ebnum));
		return "/admin/emp/board/delNotice";
	}
	
	//κ΄?λ¦¬μ - ?¬?κ²μ? 
	//?¬?λͺ©λ‘
	@RequestMapping("emp/empList")
	public String getEmpList(String pageNum, String sort, String order, String empjob, String status, 
							HttpSession session, Model model) {
		
		//sessionIdκ°? λ§€λ???΄??Έμ§? μ²΄ν¬
		String empid = (String)session.getAttribute("sid");
		//String empid = "manager";
		int checkIfMgr = service.checkifMgr(empid);
		model.addAttribute("checkIfMgr", checkIfMgr);
		
		//?΄?¬? ? λ³΄λ λ§€λ??κΈ? ?΄?λ§? μ‘°νκ°??₯
		if(status != null && status.equals("Επ»η")){
			if(checkIfMgr != 1) {
				return "/admin/warning";
			}
		} 
		
		model.addAttribute("empjob", empjob);
		model.addAttribute("status", status);
		model.addAttribute("sort", sort);
		model.addAttribute("order",order);
		
		PagingDTO page = pageService.getPaging(10, pageNum);
		model.addAttribute("page",page);
		model.addAttribute("list", service.getEmpList(page, empjob, status, sort, order));
		model.addAttribute("totalCnt", service.getEmpCnt(empjob, status));
		model.addAttribute("quitCnt", service.getQuitCnt(empjob));
		
		//jobList - DB?±λ‘λ μ§μλ¦¬μ€?Έ λ³΄λ΄κΈ?
		model.addAttribute("jobList", service.getEmpjobList());
		//statusList - DB?±λ‘λ μ§κΈλ¦¬μ€?Έ λ³΄λ΄κΈ? 
		model.addAttribute("statusList", service.getEmpStatusList());
		
		return "/admin/emp/info/empList";
	}
	//?¬?λͺ©λ‘ - κ²??κ²°κ³Ό
	@RequestMapping("emp/searchList")
	public String getEmpSearchList(String pageNum, String search, String keyword, HttpSession session, Model model) {
		model.addAttribute("keyword", keyword);
		
		PagingDTO page = pageService.getPaging(10, pageNum);
		model.addAttribute("page", page);
		
		model.addAttribute("list", service.getEmpSearchList(page, search, keyword));
		model.addAttribute("totalCnt", service.getEmpSearchCnt(search, keyword));
		model.addAttribute("quitCnt", service.getQuitCnt_search(search, keyword));
		
		String empid = (String) session.getAttribute("sid");
		model.addAttribute("checkIfMgr", service.checkifMgr(empid));
		return "/admin/emp/info/searchEmpList";
	}
	//?¬?? λ³?
	@RequestMapping("emp/empInfo")
	public String getEmpInfo(String empid, HttpSession session, Model model) {
		String sessionId = (String)session.getAttribute("sid");
		model.addAttribute("sessionId", sessionId);
		//model.addAttribute("sessionId","admin_mgr");
		model.addAttribute("dto",service.getEmpInfo(empid));
		model.addAttribute("age", service.getMemberAge(empid));
		model.addAttribute("checkIfMgr", service.checkifMgr(sessionId));
		return "/admin/emp/info/empInfo";
	}
	//?¬? ?±λ‘?
	@RequestMapping("emp/addEmp")
	public String addEmpForm(String memid, Model model) {
		//?΄λ―? ??κ°?? ??΄?? ?¬?λ§? κ°??₯
		//μ²μ κ°?? ??? ?¬? -> ??κ°??λΆ??° 
		model.addAttribute("memid", memid);
		
		if(memid!=null)
			model.addAttribute("dto", service.getMemberInfo(memid));
		
		model.addAttribute("currentDate", service.getCurrentDate());
		return "/admin/emp/info/addEmp";
	}
	@RequestMapping("emp/addEmpPro")
	public String addEmpPro(EmpInfoDTO dto, Model model) {
		model.addAttribute("result", service.addEmpInfo(dto));
		return "/admin/emp/info/addEmpPro";
	}
	//?¬?? λ³? ?? 
	@RequestMapping("emp/modEmp")
	public String modEmpInfo(String empid, HttpSession session, Model model) {
		//model.addAttribute("sessionId", session.getAttribute("empid"));
		model.addAttribute("dto", service.getEmpInfo(empid));
		return "/admin/emp/info/modEmpForm";
	}
	@RequestMapping("emp/modEmpPro")
	public String modEmpPro(EmpInfoDTO dto, Model model) {
		model.addAttribute("result", service.modEmpInfo(dto));
		model.addAttribute("empid", dto.getEmpid());
		return "/admin/emp/info/modEmpPro";
	}
	//?¬?? λ³? ?­? 
	@RequestMapping("emp/delEmp")
	public String delEmpInfo(String empid, Model model) {
		model.addAttribute("dto", service.getEmpInfo(empid));
		return "/admin/emp/info/delEmpForm";
	}
	@RequestMapping("emp/delEmpPro")
	public String delEmpPro(String empid, String reason, String reason2, Model model) {
		String leavingReason = reason+"-"+reason2;
		model.addAttribute("result", service.delEmpInfo(empid, leavingReason));
		return "/admin/emp/info/delEmpPro";
	}
	
	@RequestMapping("emp/myPage")
	public String empMypage(Model model) {
		//?¬? id μ²΄ν¬ 
		return "/admin/emp/info/empInfo";
	}
}
