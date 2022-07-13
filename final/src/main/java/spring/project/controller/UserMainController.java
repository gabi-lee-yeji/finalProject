package spring.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.project.service.UserMainService;

@Controller
@RequestMapping("/main")
public class UserMainController {
	
	@Autowired 
	private UserMainService service;
	
	@RequestMapping("")
	public String userMain(Model model) {
		return "/main";
	}
	
	@RequestMapping("/calendar")
	public String calendarTest(Model model) {
		model.addAttribute("natList", service.getNatSchedules());
		return "/main/calendarTest";
	}
}
