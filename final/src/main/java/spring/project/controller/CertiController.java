package spring.project.controller;

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
import spring.project.model.CertiInfoDTO;
import spring.project.model.CertiRequirementDTO;
import spring.project.model.LikeDTO;
import spring.project.pagination.PagingDTO;
import spring.project.pagination.PagingService;
import spring.project.service.AdminService;
import spring.project.service.CertiService;
import spring.project.service.LikeService;

@Controller
@RequestMapping("/certificate/*")
public class CertiController {
	
	@Autowired
	public CertiService service;
	
	@Autowired
	private LikeService likeservice;
	
	@Autowired
	private AdminService adminservice;
	
	@Autowired
	private PagingService pageService;
	
	// 자격증 메인 페이지
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
		public String certiContent(String cnum, String ncs_cat, HttpSession session, Model model) {
		//	Map<String, CertiAccessible> map = service.getCertiInfo(cnum);
			List<CertiRequirementDTO> reqList = adminservice.getCertiReqList(cnum);
			
			List<CertiDateDTO> dateList = null;
			if(cnum.substring(0,1).equals("N")) {
				dateList = service.searchNatPeriod(cnum);
			}else {
				dateList = service.searchPeriod(cnum);
			}
			
			String id = (String)session.getAttribute("sid");
			if(id != null) {
				int cnt = service.count(cnum,id); //관심자격증 등록되어있는지 체크
				model.addAttribute("cnt",cnt);
			}
				
			model.addAttribute("cnum",cnum);
		//	model.addAttribute("cnt",cnt);
			model.addAttribute("dateList", dateList);
			model.addAttribute("info",adminservice.getCertiInfo(cnum));
			model.addAttribute("cnum", cnum);
	//		model.addAttribute("info", map.get("info"));
			model.addAttribute("reqList", reqList);
			model.addAttribute("reqCnt", reqList.size()); 

			return "/certificate/certiContent";
	}
	
	
	// 메인 페이지 필터
	@RequestMapping("mainFilter")
	public String FilterForm(String category, Model model) {
		model.addAttribute("ncsList", service.getNcsCodeList());
		model.addAttribute("category", category);
		return "/certificate/mainFilter";
	}
	
	// 어학 자격증 페이지
	@RequestMapping("certiLang")
	public String getCertiLangList(String pageNum, Model model) {
		PagingDTO page = pageService.getPaging(20, pageNum);
		
		model.addAttribute("page", page);
		model.addAttribute("list", service.getCertiLangList(page));
		model.addAttribute("count", service.getCertiLangCnt());
		return "/certificate/certiLang";
	}
	
	// 자격증 관련 뉴스
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
