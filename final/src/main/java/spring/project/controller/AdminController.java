package spring.project.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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

import spring.project.model.CertiDetailDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.MemberFilterDTO;
import spring.project.model.MemberInfoDTO;
import spring.project.model.QnetDateDTO;
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
	
	
	@RequestMapping("addCerti")
	public String addCerti(CertiInfoDTO dto) {
		return "admin/certi/addCerti";
	}
	@RequestMapping("addCertiPro")
	public String addCertiPro(CertiInfoDTO info, CertiDetailDTO detail, Model model) {
		int result = service.addCerti(info, detail);
		model.addAttribute("result", result);
		return "admin/certi/addCertiPro";
	}
	
	@RequestMapping("modCerti")
	public String modCerti(String cnum, Model model) {
		List<Object> list = service.getCertiInfo(cnum);
		model.addAttribute("cnum", cnum);
		model.addAttribute("info", list.get(0));
		model.addAttribute("detail", list.get(1));
		if(list.size()>2) {
			model.addAttribute("qnet", list.get(2));
		}
		return "admin/certi/modCerti";
	}
	@RequestMapping("modCertiPro")
	public String modCertiPro(String cnum, CertiInfoDTO info, CertiDetailDTO detail, Model model) {
		model.addAttribute("cnum", cnum);
		model.addAttribute("result",service.modCerti(cnum, info, detail));
		return "admin/certi/modCertiPro";
	}
	
	@RequestMapping("deleteForm")
	public String deleteForm(String[] cnumList, Model model) {
		for(String cnum : cnumList)
			System.out.print(cnum);
		//�����ϱ��� ������ �ڰ��� �����ϴ� ������ 
		model.addAttribute("list", service.getDelList(cnumList));
		return "admin/certi/deleteForm";
	}
	@RequestMapping("deletePro")
	public String deletePro(String[] cnumList, Model model) {
		model.addAttribute("result", service.delCerti(cnumList));
		return "admin/certi/deletePro";
	}
	
	@RequestMapping("certiList")
	public String getCertiList(String pageNum, String sort, String order, Model model) {
		//�� �������� �����ְ� ���� �Խñۼ� �Ű������� ����
		int pageSize = 30;
		PagingDTO page = pageService.getPaging(pageSize, pageNum);
		
		
		List<CertiInfoDTO> list = service.getCertList(page, sort, order);
		int count = service.getCertCnt();
		model.addAttribute("list", list);
		model.addAttribute("count",count);
		model.addAttribute("page",page);
		model.addAttribute("sort", sort);
		model.addAttribute("order", order);
		return "admin/certi/certiList";
	}
	
	private static String trimQuote(String str) {
		str = str.replaceAll("\"=\"\"", "");
		str = str.replaceAll("\"\"\"", "");
		return str;
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
	
	@RequestMapping("/member/searchList")
	public String getSearchList(MemberFilterDTO dto, String pageNum, Model model) {
		PagingDTO page = pageService.getPaging(10, pageNum);
		model.addAttribute("list", service.getSearchList(dto, page));
		return "/admin/member/searchList";
	}
}
