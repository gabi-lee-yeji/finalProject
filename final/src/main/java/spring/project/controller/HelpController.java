package spring.project.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

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
/*	
	@RequestMapping("addNoticePro")
	public String addNoticePro(Post_BoardDTO dto, MultipartFile img) throws Exception {
		String fileName = img.getOriginalFilename(); // 업로드 = 저장되는 이름
		File f = new File("c://spring//save//", fileName); // 파일 저장 경로
		img.transferTo(f);
		
		System.out.println("fileName"+fileName);
		
		service.addNotice(dto);
		return "/help/addNoticePro";
	}
*/
	@RequestMapping("noticeList")
	public String noticeList(Model model, String pageNum) {
		if(pageNum == null) pageNum = "1";
		
		int pageSize = 10;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;
		
		count = service.noticeCount();
		List<Post_BoardDTO> noticeList = null;
		
		if(count > 0) {
			noticeList = service.noticeLists(startRow, endRow);
		}
		number = count - (currentPage - 1) * pageSize;
				
		model.addAttribute("count", count);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startRow", startRow);
		model.addAttribute("endRow", endRow);
		model.addAttribute("number", number);
		model.addAttribute("noticeList", noticeList);
		
		return "help/noticeList";
	}
	
	@RequestMapping("noticeContent")
	public String noticeContent(int pageNum, int pnum, Model model) {
		Post_BoardDTO dto = service.noticeContent(pnum);
		
		service.upReadCnt(dto);
		
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("dto", dto);
		return "help/noticeContent";
	}
	
	@RequestMapping("modNotice")
	public String modNotice(int pageNum, int pnum, Model model) {
		Post_BoardDTO dto = service.noticeContent(pnum);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("dto", dto);
		
		return "help/modNotice";
	}
	
	@RequestMapping("modNoticePro")
	public String modNoticePro(int pageNum, int pnum, Model model, Post_BoardDTO dto) {
		service.modNotice(dto);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("pnum", pnum);
		
		return "help/modNoticePro";
	}
	
	@RequestMapping("delNotice")
	public String delNotice(int pageNum, int pnum, String memid, String passwd, Model model) {
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("pnum", pnum);
		model.addAttribute("memid", memid);
		model.addAttribute("passwd", passwd);
		System.out.println("memid");
		System.out.println(memid);
		
		return "help/delNotice";
	}
		
	@RequestMapping("delNoticePro")
	public String delNoticePro(int pageNum, int pnum, String memid, String passwd, Model model) {
		int result = service.passwdCheck(memid, passwd);
		if(result == 1) {
			service.delNotice(pnum);
		}else {
			model.addAttribute("pnum", pnum);
		}
		
		model.addAttribute("result", result);
		model.addAttribute("pageNum", pageNum);
		
		return "help/delNoticePro";
	}
	
	
	
	
}
