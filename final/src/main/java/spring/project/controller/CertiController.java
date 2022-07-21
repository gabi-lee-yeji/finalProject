package spring.project.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import spring.project.model.CertiInfoDTO;
import spring.project.model.CertiRequirementDTO;
import spring.project.service.CertiService;

@Controller
@RequestMapping("/certificate/*")
public class CertiController {
	
	@Autowired
	public CertiService service;
	
	// ��ü �ڰ��� ���
	@RequestMapping("certiMain")
	public String getCertiList(HttpServletRequest request, String cnum, String pageNum, String category, Model model,String clevel,String req_degree, String req_age,String req_exp){
		
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
			clist = service.getCertiList(cnum,startRow, endRow, category,req_degree,req_age,
					req_exp,clevel);
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
	
	// �ڰ��� ������
	@RequestMapping("certiContent")
		public String certiContent(String cnum, Model model) {
		
			Map<String, CertiAccessible> map = service.getCertiInfo(cnum);
			System.out.println(cnum);
			
			List<CertiDateDTO> dateList = null;
			
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
	
	@RequestMapping("news")
	public String getNews(String cnum, Model model) throws Exception{
		model.addAttribute("list", service.getNews(cnum));
		return "certificate/news";
	}
	
}
