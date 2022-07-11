package spring.project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spring.project.model.CertiAccessible;
import spring.project.model.CertiDateDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.CertiRequirementDTO;
import spring.project.service.CertiService;

@Controller
@RequestMapping("/certificate/*")
public class CertiController {
	
	@Autowired
	public CertiService service;
	
	// 전체 자격증 목록
	@RequestMapping("certiMain")
	public String getCertiList(String cnum, String pageNum, String category, Model model){
		
		if(pageNum == null) pageNum = "1";
	      int pageSize = 15;
	      int currentPage = Integer.parseInt(pageNum);
	      int startRow = (currentPage - 1) * pageSize + 1;
	      int endRow = currentPage * pageSize;
	      int count = 0;
	      int number = 0;
		
		count = service.getCertCnt();
		List<CertiInfoDTO> clist = null;
		
		if(count > 0) {
			clist = service.getCertiList(cnum,startRow, endRow, category);
		}
		
		List<CertiDateDTO> dateList = null;
		System.out.println(cnum);
	/*	if(cnum.substring(0, 1).equals("P")) {
			dateList = service.searchNatPeriod(cnum);
		}else {
			dateList = service.searchPeriod(cnum);
		}*/
		
		number = count - (currentPage - 1) * pageSize;
	
		model.addAttribute("count", count);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startRow", startRow);
		model.addAttribute("endRow", endRow);
		model.addAttribute("number", number);
		model.addAttribute("clist", clist);
		model.addAttribute("category", category);
	/*	model.addAttribute("dateList", dateList);
		model.addAttribute("info",service.getCertiInfo(cnum).get("info")); */
		
		System.out.println(startRow);
		System.out.println(endRow);
		return "/certificate/certiMain";
	}
	
	// 자격증 상세정보
	@RequestMapping("certiContent")
		public String certiContent(String cnum, Model model) {
		
			Map<String, CertiAccessible> map = service.getCertiInfo(cnum);
			System.out.println(cnum);
			
			List<CertiDateDTO> dateList = null;
			System.out.println(cnum);
			if(cnum.substring(0,1).equals("N")) {
				dateList = service.searchNatPeriod(cnum);
			}else {
				dateList = service.searchPeriod(cnum);
			}
			
			model.addAttribute("dateList", dateList);
			model.addAttribute("info",service.getCertiInfo(cnum).get("info"));
			model.addAttribute("cnum", cnum);
			model.addAttribute("info", map.get("info"));
			model.addAttribute("req", map.get("req")); 
			System.out.println(cnum);
			return "/certificate/certiContent";
	}

	
	@RequestMapping("certiLang")
	public String getCertiLangList(Model model) {
		List<CertiInfoDTO> llist = service.getCertiLangList();
		int count = llist.size();
		model.addAttribute("llist", llist);
		model.addAttribute("count", count);
		return "/certificate/certiLang";
	}
	
	// 필터
/*	@RequestMapping("filterForm")
	public String filterForm(@RequestParam("clevel") String clevel, @RequestParam("req_age") String req_age,@RequestParam("req_degree") String req_degree,
			@RequestParam("req_exp") String req_exp, CertiDateDTO ddto, CertiInfoDTO idto,Model model) {
		if(clevel != null || req_age != null || req_degree != null || req_exp != null);
		List<CertiRequirementDTO> rlist = service.getCertiFilter();
		
		if(req_age != null) {
		List<CertiRequirementDTO> alist = service.getCertiFilter(req_age);
		model.addAttribute("req_age", req_age);
		}
		if(req_degree != null) {
		List<CertiRequirementDTO> dlist = service.getCertiFilter();
		model.addAttribute("req_degree", req_degree);
		}
		if(req_exp != null) {
		List<CertiRequirementDTO> elist = service.getCertiFilter();
		model.addAttribute("req_exp", req_exp);
		}
		
		return "/certificate/filterForm";
	} */
}
