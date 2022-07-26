package spring.project.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spring.project.model.CertiAccessible;
import spring.project.model.CertiDateDTO;
import spring.project.model.CertiFilterDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.CertiRequirementDTO;
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
	
	// 占쏙옙체 占쌘곤옙占쏙옙 占쏙옙占�
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
	
	// 자격증 상세정보
	@RequestMapping("certiContent")
		public String certiContent(String cnum, Model model,String ncs_cat,HttpSession session,HttpServletRequest request) {
		
			Map<String, CertiAccessible> map = service.getCertiInfo(cnum);
			
			List<CertiDateDTO> dateList = null;
			if(cnum.substring(0,1).equals("N")) {
				dateList = service.searchNatPeriod(cnum);
			}else {
				dateList = service.searchPeriod(cnum);
			}
			
			String id = (String)session.getAttribute("sid");
			int cnt = service.count(cnum,id);
			System.out.println("cnt:"+cnt+"sessionID=="+id+"cnum:"+cnum);
				
			model.addAttribute("cnum",cnum);
			model.addAttribute("cnt",cnt);
			model.addAttribute("dateList", dateList);
			model.addAttribute("info",service.getCertiInfo(cnum).get("info"));
			model.addAttribute("cnum", cnum);
			model.addAttribute("info", map.get("info"));
			model.addAttribute("req", map.get("req")); 

			return "/certificate/certiContent";
	}
	
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
	
	@RequestMapping("certiLang")
	public String getCertiLangList(Model model) {
		List<CertiInfoDTO> list = service.getCertiLangList();
		int count = list.size();
		model.addAttribute("list", list);
		model.addAttribute("count", count);
		return "/certificate/certiLang";
	}
	
	@RequestMapping("news")
	public String getNews(String cnum, Model model) throws Exception{
		model.addAttribute("list", service.getNews(cnum));
		return "certificate/news";
	}
	
	@RequestMapping("pyramidGraph")
	public String pyramidGraph(Model model, String cnum) {
		model.addAttribute("data", service.pyramidGraph(cnum));
		return "certificate/pyramidGraph";
	}
	
	@RequestMapping("lineGraph")
	public String lineGraph(Model model, CertiInfoDTO dto) {
		model.addAttribute("cnum", dto.getCnum());
		model.addAttribute("data", service.lineGraph(dto));
		return "certificate/lineGraph";
	}
	
	
	
}
