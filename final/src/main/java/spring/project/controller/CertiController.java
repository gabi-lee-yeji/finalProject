package spring.project.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.project.model.CertiAccessible;
import spring.project.model.CertiDateDTO;
import spring.project.model.CertiFilterDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.LikeDTO;
import spring.project.service.CertiService;
import spring.project.service.LikeService;

@Controller
@RequestMapping("/certificate/*")
public class CertiController {
	
	@Autowired
	public CertiService service;
	
	@Autowired
	private LikeService likeservice;
	
	// 전체 자격증 목록 페이지
	@RequestMapping("certiMain")
	public String getCertiList(LikeDTO like,HttpSession session,HttpServletRequest request, String pageNum, Model model,String clevel,String category){
		
		if(pageNum == null) pageNum = "1";
	      int pageSize = 15;
	      int currentPage = Integer.parseInt(pageNum);
	      int startRow = (currentPage - 1) * pageSize + 1;
	      int endRow = currentPage * pageSize;
	      int count = 0;
	      int number = 0;
	      
		count = service.getCertCnt();
		List<CertiInfoDTO> clist = service.getCertiList(startRow, endRow,category);
		
		String memid = (String)session.getAttribute("sid");
		if(memid != null) {
			List<String> mlist = service.getLikeList(memid);
			model.addAttribute("check", mlist.size());
			model.addAttribute("mlist", mlist);
		}
		
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
		
		return "/certificate/certiMain";
	}
	
	// 자격증 상세정보 페이지
	@RequestMapping("certiContent")
	public String certiContent(String cnum, Model model,String ncs_cat,HttpSession session,HttpServletRequest request) {
			// 현재 날짜 구하기	
		LocalDate now = LocalDate.now();	        

		Map<String, CertiAccessible> map = service.getCertiInfo(cnum);
			
		List<CertiDateDTO> dateList = null;
		if(cnum.substring(0,1).equals("N")) {
			dateList = service.searchNatPeriod(cnum);
		}else {
			dateList = service.searchPeriod(cnum);
		}
			
		String id = (String)session.getAttribute("sid");
		int cnt = service.count(cnum,id);
				
		model.addAttribute("cnum",cnum);
		model.addAttribute("cnt",cnt);
		model.addAttribute("dateList", dateList);
		model.addAttribute("info",service.getCertiInfo(cnum).get("info"));
		model.addAttribute("cnum", cnum);
		model.addAttribute("info", map.get("info"));
		model.addAttribute("req", map.get("req")); 
		model.addAttribute("now",now);

		return "/certificate/certiContent";
	}
	
	// 자격증 필터창
	@RequestMapping("mainFilter")
	public String FilterForm(String category, Model model) {
		model.addAttribute("ncsList", service.getNcsCodeList());
		model.addAttribute("category", category);
		return "/certificate/mainFilter";
	}
	
	@RequestMapping("filterPro")
	public String getFilterResult(CertiFilterDTO dto, Model model) {
		
		model.addAttribute("dto", dto);
		model.addAttribute("list", service.getFilteredList(dto));
		model.addAttribute("count", service.getCertiFilteredCnt(dto));
		
		
		if(dto.getNcs_cat().length>0) {
			model.addAttribute("ncsName", service.getNcsName(dto));
			model.addAttribute("ncs_length", dto.getNcs_cat().length+1);
		}
		
		return "/certificate/certiFilterPro";
	}
	
/*	@RequestMapping("filterPro")
	public String getFilteredList(String pageNum, Model model,String[] clevel,String req_degree, String req_age,String req_exp){
	
		List<CertiInfoDTO> list = null;

		list = service.getFilteredList(clevel);
		
		model.addAttribute("list", list);
		
		return "/certificate/filterList";
	}*/
	
	// 어학 자격증 페이지
	@RequestMapping("certiLang")
	public String getCertiLangList(Model model) {
		List<CertiInfoDTO> list = service.getCertiLangList();
		int count = list.size();
		model.addAttribute("list", list);
		model.addAttribute("count", count);
		return "/certificate/certiLang";
	}
	
	
	
	
}
