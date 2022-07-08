package spring.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;

import spring.project.model.CertiInfoDTO;

import spring.project.service.CertiService;

@Controller
@RequestMapping("/certificate/*")
public class CertiController {
	
	@Autowired
	public CertiService service;
	
	// 전체 자격증 목록
	@RequestMapping("certiMain")
	public String getCertiList(String pageNum, String category, Model model){
		
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
			clist = service.getCertiList(startRow, endRow, category);
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
		
		System.out.println(startRow);
		System.out.println(endRow);
		return "/certificate/certiMain";
	}
	
	
	@RequestMapping("certiLang")
	public String getCertiLangList(Model model) {
		List<CertiInfoDTO> llist = service.getCertiLangList();
		int count = llist.size();
		model.addAttribute("llist", llist);
		model.addAttribute("count", count);
		return "/certificate/certiLang";
	}
	@RequestMapping("filterForm")
	public String filterForm() {
		return "/certificate/filterForm";
	}
}
