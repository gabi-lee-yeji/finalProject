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
	

}
