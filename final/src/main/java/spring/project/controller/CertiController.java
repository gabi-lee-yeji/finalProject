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
	private CertiService service;
	
	@RequestMapping("certiList")
	public String getCertiNatList(String category, Model model){
		List<CertiInfoDTO> nlist = service.getCertiNatList();
		int count = nlist.size();
		model.addAttribute("nlist", nlist);
		model.addAttribute("count", count);
		return "/certificate/certiNat";
	}
	
	@RequestMapping("certiPrv")
	public String getCertiPriList(Model model) {
		List<CertiInfoDTO> plist = service.getCertiPrvList();
		int count = plist.size();
		model.addAttribute("plist", plist);
		model.addAttribute("count", count);
		return "/certificate/certiPrv";
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
