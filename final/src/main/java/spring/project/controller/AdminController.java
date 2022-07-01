package spring.project.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

@Controller
@RequestMapping("/admin/*")
public class AdminController {
	
	@Autowired
	private AdminService service;
	
	@Autowired
	private PagingService pageService;
	
	static Map<String,String> paramMap = new HashMap<String,String>();
	
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
		return "admin/certi/infoList";
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
	public String certiDateInfo(String cnum, String cname, Model model) {
		List<CertiDateDTO> dateList = null;
		if(cnum.substring(0, 1).equals("N")) {
			dateList = service.searchNatPeriod(cnum);
		}else {
			paramMap.put("cnum", cnum);
			dateList = service.searchPeriod(paramMap);
		}
	
		model.addAttribute("dateList", dateList);
		model.addAttribute("cnum",cnum);
		model.addAttribute("cname",cname);
		
		return "admin/certi/certiDate";
	}
	//자격증별 상세일정 - 기간 검색
	@RequestMapping("certi/searchPeriod")
	public String searchPeriod(String cname, String cnum, String startDay, String endDay, String search, Model model) {
		paramMap.put("cnum", cnum); paramMap.put("search", search); 
		paramMap.put("startDay", startDay); paramMap.put("endDay", endDay); 
		
		model.addAttribute("dateList", service.searchPeriod(paramMap));
		return "admin/certi/searchPeriod";
	}
	@RequestMapping("certi/addDate")
	public String addDate(String cnum, String cname, Model model) {
		model.addAttribute("cnum", cnum);
		model.addAttribute("cname", cname);
		return "admin/certi/addDate";
	}
	@RequestMapping("certi/addDatePro")
	public String addDate(CertiDateDTO date, Model model) {
		return "admin/certi/addDatePro";
	}
//	@RequestMapping("modCerti")
//	public String modCertiPro(String cnum, CertiInfoDTO info, CertiDetailDTO detail, Model model) {
//		model.addAttribute("cnum", cnum);
//		model.addAttribute("result",service.modCerti(cnum, info, detail));
//		return "admin/certi/modCertiPro";
//	}
	
	@RequestMapping("deleteForm")
	public String deleteForm(String[] cnumList, Model model) {
		for(String cnum : cnumList)
			System.out.print(cnum);
		//삭제하기전 선택한 자격증 컨펌하는 페이지 
		model.addAttribute("list", service.getDelList(cnumList));
		return "admin/certi/deleteForm";
	}
	@RequestMapping("deletePro")
	public String deletePro(String[] cnumList, Model model) {
		model.addAttribute("result", service.delCerti(cnumList));
		return "admin/certi/deletePro";
	}
	
	
	
	@RequestMapping("search")
	public String searchList(String pageNum, String search, String keyword, String category, Model model) {
		PagingDTO page = pageService.getPaging(30, pageNum);
		model.addAttribute("search", search);
		model.addAttribute("page", page);
		if(search.equals("category")) {
			if(category == null) category = keyword;
			model.addAttribute("keyword", category);
			model.addAttribute("count", service.getSearchCnt(search, category));
			model.addAttribute("list", service.getSearchList(page, search, category));
		}else {
			model.addAttribute("keyword", keyword);
			model.addAttribute("count", service.getSearchCnt(search, keyword));
			model.addAttribute("list", service.getSearchList(page, search, keyword));
		}
		System.out.println("search : "+search);
		System.out.println("keyword : "+keyword);
		System.out.println("category : "+category);
		return "/admin/certi/searchList";
	}
	
	@RequestMapping("/member/list")
	public String getMemberList(String pageNum, String sort, String order, Model model) {
		PagingDTO page = pageService.getPaging(30, pageNum);
		model.addAttribute("page",page);
		model.addAttribute("count",service.getMemberCnt());
		model.addAttribute("list", service.getMemberList(page, sort, order));
		return "/admin/member/list";
	}
	
	
	@RequestMapping("/member/filter")
	public String memberFilter() {
		return "/admin/member/memberFilter";
	}
	
	@RequestMapping("/member/filterPro")
	public String getSearchList(MemberFilterDTO dto, String pageNum, Model model) {
		PagingDTO page = pageService.getPaging(10, pageNum);
		List<MemberInfoDTO> list = service.getMemberFilter(dto, page);
		model.addAttribute("list", list);
		return "/admin/member/searchList";
	}
	
	@RequestMapping("/member/reportList")
	public String getMemberReport(String status, Model model) {
		model.addAttribute("status", status);
		model.addAttribute("list", service.getMemberReport(status));
		return "/admin/member/reportList";
	}
	
	@RequestMapping("/member/reportMemInfo")
	public String getReportMemInfo(String memid, String reportCnt, Model model) {
		model.addAttribute("memid", memid);
		model.addAttribute("reportCnt", reportCnt);
		model.addAttribute("dto", service.getMemberInfo(memid));
		model.addAttribute("list", service.getreportMemInfo(memid));
		return "/admin/member/reportMemInfo";
	}
	
	@RequestMapping("/member/memReportPro")
	public String modReportMember(String memid, String status, Model model) {
		model.addAttribute("result", service.updateRepMemStatus(memid, status));
		return "/admin/member/memReportPro";
	}
}
