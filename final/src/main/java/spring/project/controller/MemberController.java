package spring.project.controller;

import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.project.model.Comm_BoardDTO;
import spring.project.model.MemberInfoDTO;
import spring.project.model.Post_BoardDTO;
import spring.project.service.MailSendService;
import spring.project.service.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	@Autowired
	private MailSendService mailService;
	
	@Autowired
	private MemberService service;
	
	@RequestMapping("loginForm")
	public String loginForm(HttpSession session,Model model,String memid, HttpServletRequest request) {
		if(memid != null) model.addAttribute("memid",memid);
		return "member/loginForm";
	}
	@RequestMapping("agreeForm")
	public String agree(String memid,Model model) {
		return "member/agreeForm";
	}
	
	@RequestMapping("loginPro")
	public String loginPro(HttpServletRequest request,HttpSession session,HttpServletResponse response,Model model,MemberInfoDTO dto,String auto) {
		
		//HttpServletRequest는 getCookies(쿠키 다가져오기(배열)) 할 때 필요하고,
		//HttpSession는 세션받아올 때 필요하고,
		//HttpServletResponse는 addCookie할 때 필요함
		
		String memid = (String)dto.getMemid();
		String passwd = (String)dto.getPasswd();
		
		Cookie [] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie c : cookies) {
				String cname = c.getName();
				if(cname.equals("cid")) dto.setMemid(c.getValue());
				if(cname.equals("cpw")) dto.setPasswd(c.getValue()); 
				if(cname.equals("cauto")) auto = c.getValue();
			}
		}
		MemberInfoDTO dto2 = service.userCheck(dto);
		int result = 0;
		if(dto2 != null) {
		result = dto2.getCnt();
		}
		if(result==1) {
			if(auto != null) {
				Cookie cid = new Cookie("cid",memid);
				Cookie cpw = new Cookie("cpw",passwd);
				Cookie cauto = new Cookie("cauto",auto);
				
				cid.setMaxAge(60*60*24*3);
				cpw.setMaxAge(60*60*24*3);
				cauto.setMaxAge(60*60*24*3);
				
				cid.setPath("/");
				cpw.setPath("/");
				cauto.setPath("/");
				
				response.addCookie(cid);
				response.addCookie(cpw);
				response.addCookie(cauto);
				
			}
			service.updateTime(memid);
			session.setAttribute("sid", dto.getMemid());
			session.setMaxInactiveInterval(60*60*24);
		}
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		//날짜 더하기
		Calendar cal = Calendar.getInstance();
		Date time = new Date();
		if(dto2 != null) {
		time = dto2.getRef_date();
		}
		cal.setTime(time);
		cal.add(Calendar.DATE,7);
		
		model.addAttribute("time",format1.format(cal.getTime()));
		model.addAttribute("result",result);
		model.addAttribute("dto",dto2);
		
		return "member/loginPro";
	}
	//로그아웃 - 세션,쿠키 삭제
	@RequestMapping("logout")
	public String logout(HttpSession session,HttpServletRequest request,HttpServletResponse response) {
		session.invalidate();
		Cookie [] cookies = request.getCookies();
		for(Cookie c : cookies) {
			String cname = c.getName();
			if(cname.equals("cid")) {
				c.setPath("/");
				c.setMaxAge(0);
				response.addCookie(c);
			}
			if(cname.equals("cpw")) {
			c.setPath("/");
			c.setMaxAge(0);
			response.addCookie(c);
			}
			if(cname.equals("cauto")) {
				c.setPath("cauto");
				c.setMaxAge(0);
				response.addCookie(c);
			}
		}
		
		return "member/logout";
	}
	//메인
	@RequestMapping("main")
	public String main(HttpServletResponse response,HttpSession session,Model model,HttpServletRequest request) {
		
		String sid = (String)session.getAttribute("sid");
		Cookie[] cookies = request.getCookies();
		
		MemberInfoDTO dto = new MemberInfoDTO();
		String cid = null, cpw=null,cauto=null;
		
		if(sid == null) {
			if(cookies != null) {
				for(Cookie c : cookies) {
					String cname = c.getName();
					if(cname.equals("cid")) {
						dto.setMemid(c.getValue());
						cid = c.getValue();
					}
					if(cname.equals("cpw")) { 
						dto.setPasswd(c.getValue());
						cpw = c.getValue();
					}
					if(cname.equals("cauto")) {cauto = c.getValue();
					}
				}
				if(cauto != null && cid != null && cpw != null) {
					loginPro( request, session, response, model, dto, cauto);
				}
			}
		}
		return "member/main";
	}
	//아이디 중복 확인
	@RequestMapping(value = "idDuplicate", method = RequestMethod.GET)
	public @ResponseBody String idDuplicate(String memid,HttpServletRequest request,Model model) {
		
		memid = (String)request.getParameter("memid");
		int result = service.idDuplicate(memid);
		return result+"";
	}
	//아이디,이메일로 검색 후 확인
	@RequestMapping(value = "pwCheck",method=RequestMethod.GET)
	@ResponseBody String pwFind(MemberInfoDTO dto) {
		int result = service.pwCheck(dto);
		return result+"";
	}
	//비밀번호 찾기 폼
	@RequestMapping("pwFindForm")
	public String pwFindForm() {
		return "member/pwFindForm";
	}
	//비밀번호 찾기 프로
	@RequestMapping("pwFindPro")
	public String pwFindPro(MemberInfoDTO dto,Model model) {
		//responsebody로 넘어오는 것에는 dto.email이 있기때문에 service에서 못 넣고 여기서 넣음
		dto.setEmail(dto.getMail1()+'@'+dto.getMail2());
		int result = service.pwCheck(dto);

		MemberInfoDTO dto2 = service.pwFind(dto);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		Calendar cal = Calendar.getInstance();
		Date date = dto2.getRef_date();
		cal.setTime(date);
		cal.add(Calendar.DATE, 7);
		
		model.addAttribute("time",sdf.format(cal.getTime()));
		model.addAttribute("dto",dto2);
		model.addAttribute("result",result);
		return "member/pwFindPro";
	}
	//휴면계정 폼
	@RequestMapping("dormancyForm")
	public String dormancyForm() {
		return "member/dormancyForm";
	}
	//휴면계정 프로
	@RequestMapping("dormancyPro")
	public String domancyPro(MemberInfoDTO dto,Model model) {
		//responsebody로 넘어오는 것에는 dto.email이 있기때문에 service에서 못 넣고 여기서 넣음
		dto.setEmail(dto.getMail1()+'@'+dto.getMail2());
		int result = service.pwCheck(dto);
		service.domancyUpdate(dto);
		MemberInfoDTO dto2 = service.pwFind(dto);
		
		model.addAttribute("dto",dto2);
		model.addAttribute("result",result);
		return "member/dormancyPro";
	}
	//수정 아이디확인 폼
	@RequestMapping("modifyConfirm")
	public String modifyConfirm() {
		return "member/modifyConfirm";
	}
	//수정 폼
	@RequestMapping("modifyForm")
	public String modifyForm(HttpSession session,MemberInfoDTO dto,Model model) {
		
		MemberInfoDTO dto2 = service.userCheck(dto);
			int result = dto2.getCnt();
			if(result == 1) {
				dto = service.findUser(dto);
				dto.setMail1(dto.getEmail().split("@")[0]);
				dto.setMail2(dto.getEmail().split("@")[1]);
				
				/* dto.getMobile().split("-")[0]; */
				String phone = dto.getMobile().split(" ")[1];
				dto.setPhone1(phone.split("-")[0]);
				dto.setPhone2(phone.split("-")[1]);
				dto.setPhone3(phone.split("-")[2]);
				
				dto.setBirthday(dto.getBirthday().split(" ")[0]);
				dto.setExtraAddress(dto.getAddr_detail());
				
				String pC = dto.getMobile().substring(0,3).trim();
				model.addAttribute("dto",dto);
				model.addAttribute("pC",pC);
			}else{
				return "member/loginForm";
			}
		model.addAttribute("result",result);
		return "member/modifyForm";
	}
	//수정 프로
	@RequestMapping("modifyPro")
	public String modifyPro(MemberInfoDTO dto) {
		service.modifyList(dto);
		return "member/modifyPro";
	}
	//가입 폼
	@RequestMapping("signUpForm")
	public String signUpForm() {
		return "member/signUpForm";
	}
	//가입 프로
	@RequestMapping("signUpPro")
	public String signUpPro(MemberInfoDTO dto,Model model) {
		service.insertMember(dto);
		return "member/signUpPro";
	}
	//삭제 폼
	@RequestMapping("deleteForm")
	public String deleteForm() {
		return "member/deleteForm";
	}
	//삭제 프로
	@RequestMapping("deletePro")
	public String deletePro(HttpServletRequest request,HttpServletResponse response,HttpSession session,MemberInfoDTO dto,Model model) {
		MemberInfoDTO dto2 = service.userCheck(dto);
		int result = dto2.getCnt();
			if(result == 1) {
				service.deleteUser(dto);
				Cookie [] cookies = request.getCookies();
				for(Cookie c : cookies) {
					String cname = c.getName();
					if(cname.equals("cid")) {
						c.setPath("/");
						c.setMaxAge(0);
						response.addCookie(c);
					}
					if(cname.equals("cpw")) {
						c.setPath("/");
						c.setMaxAge(0);
						response.addCookie(c);
					}
					if(cname.equals("cauto")) {
						c.setPath("/");
						c.setMaxAge(0);
						response.addCookie(c);
					}
				}
			}
		model.addAttribute("result",result);
	return "member/deletePro";
	}
 	//아이디 찾기 폼
	@RequestMapping("idFindForm")
	public String idFindForm() {
		return "member/idFindForm";
	}
	//아이디 찾기 프로
	@RequestMapping("idFindPro")
	public String idFindPro(MemberInfoDTO dto,Model model) {
		int result = 0;
		MemberInfoDTO dto2 = service.idFind(dto);
		if(dto2 != null) {
			result = 1;
		}
		model.addAttribute("result",result);
		model.addAttribute("dto",dto2);
		return "member/idFindPro";
	}
	//이메일 인증
	@GetMapping("/mailCheck")
	@ResponseBody
	public String mailCheck(String email) {
		System.out.println("이메일 인증 요청이 들어옴!");
		System.out.println("이메일 인증 이메일 : " + email);
		return mailService.joinEmail(email);
	}
	//내가 쓴 글 확인
	@RequestMapping("myList")
	public String Mylist(String pageNum,Model model,Integer board_type,String writer) {
		if(pageNum == null) pageNum = "1";
		int pageSize = 10;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;
		System.out.println(board_type);
		if(board_type == null) board_type = 0;
		count = service.post_BoardCount(board_type,writer);
		ArrayList<Post_BoardDTO> boardList = null;
		if(count > 0) {
		boardList = (ArrayList<Post_BoardDTO>)service.myList(writer,board_type,startRow,endRow);
		}
		number = count - (currentPage - 1) * pageSize;
		
		model.addAttribute("count", count);
		model.addAttribute("board_type",board_type);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startRow", startRow);
		model.addAttribute("endRow", endRow);
		model.addAttribute("number", number);
		model.addAttribute("boardList", boardList);
		   
		return "member/myList";
	}
	//내 댓글 확인
	@RequestMapping("myComments")
	public String myComments(String pageNum,String writer,Model model) {
		if(pageNum == null) pageNum = "1";
		int pageSize = 10;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;
		List<Comm_BoardDTO> commList = null;
		count = service.commentsCount(writer);
		if(count > 0) {
			commList = service.myComments(writer,startRow,endRow);
			}
			number = count - (currentPage - 1) * pageSize;
			model.addAttribute("count", count);
			model.addAttribute("pageNum", pageNum);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("startRow", startRow);
			model.addAttribute("endRow", endRow);
			model.addAttribute("number", number);
			model.addAttribute("commList",commList);
		return "member/myComments";
	}
	
}
