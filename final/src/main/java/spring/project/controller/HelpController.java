package spring.project.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import spring.project.model.Post_BoardDTO;
import spring.project.service.HelpService;

@Controller
@RequestMapping("/help/*")
public class HelpController {
	
	@Autowired
	private HelpService service;
	
	@RequestMapping("notice/addNotice")
	public String addNotice() {
		return "/help/notice/addNotice";
	}
	
//	@RequestMapping("notice/addNoticePro")
//	public String addNoticePro(Post_BoardDTO dto) {
//		service.addNotice(dto);
//		return "/help/notice/addNoticePro";
//	}
	
	@RequestMapping("notice/addNoticePro")
	public String addNoticePro(Post_BoardDTO dto, MultipartFile img) throws Exception {
		String fileName = img.getOriginalFilename();
		File f = new File("c://spring//save//", fileName); // 파일 저장경로
		
		img.transferTo(f);
		
		System.out.println("writer="+dto.getWriter());
		System.out.println("file name="+img.getOriginalFilename());
/*		String uploadFolder = "c://spring//save//";
		
		for(MultipartFile multipartFile : uploadFile) {
			log.info("---------------------");
			log.info("uploaf File Name: "+ multipartFile.getOriginalFilename());
			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
			
		try {
			multipartFile.transferTo(saveFile);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		}
		*/
	//	String fileName = img.getOriginalFilename(); // 업로드 = 저장되는 이름
	//	File f = new File(uploadFolder, img.getOriginalFilename()); // 파일 저장 경로
	//	img.transferTo(f);
	//	
	//	System.out.println("fileName"+img.getOriginalFilename()+fileName);
	//	
		service.addPost_Board(dto);
		return "/help/notice/addNoticePro";
	}

	@RequestMapping("notice/noticeList")
	public String noticeList(Model model, String pageNum, String board_type) {
		if(pageNum == null) pageNum = "1";
		
		int pageSize = 10;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;
		
		count = service.post_BoardCount(board_type);
		List<Post_BoardDTO> noticeList = null;
		
		if(count > 0) {
			noticeList = service.post_BoardLists(startRow, endRow, board_type);
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
		
		return "help/notice/noticeList";
	}
	
	@RequestMapping("notice/noticeContent")
	public String noticeContent(int pageNum, int pnum, Model model) {
		Post_BoardDTO dto = service.post_BoardContent(pnum);
		
		service.upReadCnt(dto);
		
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("dto", dto);
		return "help/notice/noticeContent";
	}
	
	@RequestMapping("notice/modNotice")
	public String modNotice(int pageNum, int pnum, Model model) {
		Post_BoardDTO dto = service.post_BoardContent(pnum);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("dto", dto);
		
		return "help/notice/modNotice";
	}
	
	@RequestMapping("notice/modNoticePro")
	public String modNoticePro(int pageNum, int pnum, Model model, Post_BoardDTO dto) {
		service.modPost_Board(dto);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("pnum", pnum);
		
		return "help/notice/modNoticePro";
	}
	
	@RequestMapping("notice/delNotice")
	public String delNotice(int pageNum, int pnum, String memid, String passwd, Model model) {
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("pnum", pnum);
		model.addAttribute("memid", memid);
		model.addAttribute("passwd", passwd);
		System.out.println("memid");
		System.out.println(memid);
		
		return "help/notice/delNotice";
	}
		
	@RequestMapping("notice/delNoticePro")
	public String delNoticePro(int pageNum, int pnum, String memid, String passwd, Model model) {
		int result = service.passwdCheck(memid, passwd);
		if(result == 1) {
			service.delPost_Board(pnum);
		}else {
			model.addAttribute("pnum", pnum);
		}
		
		model.addAttribute("result", result);
		model.addAttribute("pageNum", pageNum);
		
		return "help/notice/delNoticePro";
	}
	
	@RequestMapping("qna/addQna")
	public String addQna(String pnum, Post_BoardDTO dto, Model model) {
		System.out.println(pnum);
		int number = 0;
		if(pnum!=null)
			number = Integer.parseInt(pnum);
		if(number != 0) {
			dto = service.post_BoardContent(number);
			model.addAttribute("dto", dto);
		}else {
			model.addAttribute("pnum", number);
		}
			return "help/qna/addQna";
		}
		
	
	@RequestMapping("qna/addQnaPro")
	public String addQnaPro(MultipartFile img, Post_BoardDTO dto) throws IOException {
/*		String uploadFolder = "C:\\spring\\src\\main\\webapp\\file";
		
		String fileName = img.getOriginalFilename();
		System.out.println(fileName);
		MultipartFile uploadFile = dto.getUploadFile();
			System.out.println(uploadFile);
		
		if(!uploadFile.isEmpty()) {
			fileName = uploadFile.getOriginalFilename();
			System.out.println("fileName------------"+fileName);
			String originalFileName = uploadFile.getOriginalFilename();
			String ext = FilenameUtils.getExtension(originalFileName); // 확장자
			fileName = "."+ext;
			uploadFile.transferTo(new File("c:/spring/save"+fileName));
		}
		
		System.out.println(fileName);
		
		dto.setImg(fileName);
		
		System.out.println(dto.getImg());
	*/	
		service.addPost_Board(dto);
		return "help/qna/addQnaPro";
	}
	
	@RequestMapping("qna/qnaList")
	public String qnaList(Model model, String pageNum, String board_type) {
		//System.out.println(board_type);
		if(pageNum == null) pageNum = "1";
		
		int pageSize = 10;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;
		
		count = service.post_BoardCount(board_type);
		List<Post_BoardDTO> qnaList = null;
		
		if(count > 0) {
			qnaList = service.post_BoardLists(startRow, endRow, board_type);
		}
		number = count - (currentPage - 1) * pageSize;
				
		model.addAttribute("count", count);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startRow", startRow);
		model.addAttribute("endRow", endRow);
		model.addAttribute("number", number);
		model.addAttribute("qnaList", qnaList);
		model.addAttribute("board_type", board_type);
		
		return "help/qna/qnaList";
	}

	@RequestMapping("qna/qnaContent")
	public String qnaContent(int pnum, String pageNum, Model model) {
		Post_BoardDTO dto = service.post_BoardContent(pnum);
		service.upReadCnt(dto);

		model.addAttribute("pageNum", pageNum);
		model.addAttribute("dto", dto);
		return "help/qna/qnaContent";
	}
	
}















