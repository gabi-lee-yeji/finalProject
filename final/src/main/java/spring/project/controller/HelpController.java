package spring.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;
import spring.project.model.Post_BoardDTO;
import spring.project.service.HelpService;

@Controller
@RequestMapping("/help/*")
@Log4j
public class HelpController {
	
	@Autowired
	private HelpService service;
	
	@RequestMapping("addNotice")
	public String addNotice(Post_BoardDTO dto) {
		
		log.info("addNotice 연결 확인");
		
		return "/help/addNotice";
	}
	
	@RequestMapping("addNoticePro")
	public String addNoticePro(Post_BoardDTO dto) {
		service.addNotice(dto);
		return "/help/addNoticePro";
	}

	@RequestMapping("noticeList")
	public String noticeList() {
		return "help/noticeList";
	}
}
