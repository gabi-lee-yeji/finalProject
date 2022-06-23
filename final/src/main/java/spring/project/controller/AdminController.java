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
		
		int i=0;
		String strLine;
		String msg="";
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
}
