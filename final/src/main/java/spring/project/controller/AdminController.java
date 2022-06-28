package spring.project.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
		//삭제하기전 선택한 자격증 컨펌하는 페이지 
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
		//한 페이지에 보여주고 싶은 게시글수 매개변수로 전달
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
	
	@RequestMapping("cert/addQnetAll")
	public String addQnetAll() throws IOException {
		
		FileInputStream fis = new FileInputStream(new File("F:/R/kki.csv"));
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		
		String strLine;
		while((strLine = br.readLine()) != null) {
			//System.out.println(strLine);
			String [] datas = strLine.split(",");
			
			CertiInfoDTO info = new CertiInfoDTO();
			CertiDetailDTO detail = new CertiDetailDTO();
			
			info.setCategory("국가기술");
			info.setCname(datas[3]);
			info.setCtype(datas[2]);
			info.setCround(Integer.parseInt(datas[1]));
			info.setCyear(Integer.parseInt(datas[0]));
			
			detail.setCompany("한국산업인력공단");
			
			service.addCerti(info,detail);
			//System.out.println(info);
			//System.out.println(detail);
		}
		return "admin/certi/addQnetAll";
	}
	

	@RequestMapping("addQnetDate")
	public String addQnetDate() throws IOException {
		
		FileInputStream fis = new FileInputStream(new File("F:/R/qnetdate.csv"));
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		
		String strLine;
		while((strLine = br.readLine()) != null) {
			//System.out.println(strLine);
			QnetDateDTO qdto = new QnetDateDTO();
			String [] datas = strLine.split(",");
			for(String s : datas) s = trimQuote(s); 
			
			qdto.setCyear(Integer.parseInt(datas[0]));
			qdto.setCround(Integer.parseInt(datas[1]));
			qdto.setCtype(datas[2]);
			qdto.setDocRegStart1(datas[3].substring(0, 8));
			qdto.setDocRegEnd1(datas[3].substring(8, 16));
			if(datas[3].length() > 16) {
				qdto.setDocRegStart2(datas[3].substring(16, 24));
				qdto.setDocRegEnd2(datas[3].substring(24, 32));
			}
			qdto.setDocTestStart(datas[4].substring(0,8));
			if(datas[4].length() > 8) {
				qdto.setDocTestEnd(datas[4].substring(8,16));
			}
			qdto.setDocResult(datas[5]);
			if(datas[6].length() > 0) {
				qdto.setDocSubmitStart(datas[6].substring(0, 8));
				qdto.setDocSubmitEnd(datas[6].substring(8, 16));
			}
			qdto.setPracRegStart1(datas[7].substring(0, 8));
			qdto.setPracRegEnd1(datas[7].substring(8, 16));
			if(datas[7].length() > 16) {
				qdto.setPracRegStart2(datas[7].substring(16, 24));
				qdto.setPracRegEnd2(datas[7].substring(24,32));
			}
			qdto.setPracTestStart(datas[8].substring(0,8));
			qdto.setPracTestEnd(datas[8].substring(8,16));
			qdto.setPracResStart(datas[9].substring(0, 8));
			if(datas[9].length() > 8) {
				qdto.setPracResEnd(datas[9].substring(8, 16));
			}
			
			//System.out.println(qdto);
			service.addQnetDate(qdto);
			
		}
		
		return "admin/certi/addQnetDate";
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
