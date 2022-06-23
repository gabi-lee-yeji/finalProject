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
import spring.project.model.QnetDateDTO;
import spring.project.service.AdminService;

@Controller
@RequestMapping("/admin/*")
public class AdminController {
	
	@Autowired
	private AdminService service;
	
	@RequestMapping("addCerti")
	public String addCerti(CertiInfoDTO dto) {
		return "admin/addCerti";
	}
	@RequestMapping("addCertiPro")
	public String addCertiPro(CertiInfoDTO info, CertiDetailDTO detail, Model model) {
		int result = service.addCerti(info, detail);
		model.addAttribute("result", result);
		return "admin/addCertiPro";
	}
	
	@RequestMapping("modCerti")
	public String modCerti(String cnum, Model model) {
		List<Object> list = service.getCertiInfo(cnum);
		model.addAttribute("info", list.get(0));
		model.addAttribute("detail", list.get(1));
		if(list.size()>2) {
			model.addAttribute("qnet", list.get(2));
		}
		return "admin/modCerti";
	}
	@RequestMapping("modCertiPro")
	public String modCertiPro(CertiInfoDTO info, CertiDetailDTO detail, Model model) {
		model.addAttribute("result",service.modCerti(info, detail));
		return "admin/modCertiPro";
	}
	
	
	@RequestMapping("certiList")
	public String getCertiList(Model model) {
		List<CertiInfoDTO> list = service.getCertList();
		int count = list.size();
		model.addAttribute("list", list);
		model.addAttribute("count",count);
		return "admin/certiList";
	}
	
	@RequestMapping("addQnetAll")
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
		
		return "admin/addQnetAll";
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
		
		return "admin/addQnetDate";
	}
	
	private static String trimQuote(String str) {
		str = str.replaceAll("\"=\"\"", "");
		str = str.replaceAll("\"\"\"", "");
		return str;
	}
}
