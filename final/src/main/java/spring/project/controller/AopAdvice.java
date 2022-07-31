package spring.project.controller;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import spring.project.service.AdminService;

@Aspect
@Component
public class AopAdvice {
	
	@Autowired
	private AdminService adminService;
	
	private String getMemid() {
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = sra.getRequest().getSession();
		String memid = (String) session.getAttribute("sid");
		return memid;
	}
	
	//모든 admin 페이지는 사원이상만 접근가능
	@Around("execution(* spring.project.controller.AdminController.*(..))")
	public String adminAround(ProceedingJoinPoint jp) throws Throwable{
		//접근하려던 view 페이지 정보 
		String view = (String) jp.proceed();
		
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = sra.getRequest().getSession();
		String memid = (String) session.getAttribute("sid");
		
		System.out.println("====AOP:AdminPage===="+memid);
		
		//로그인했는지 체크
		if(memid==null) {
			System.out.println("====AOP:AdminPage====로그인필요");
			return "member/loginForm";
		}
		//로그인한 ID가 사원이상인지 체크
		if(adminService.checkIfEmp(memid)==0) {
			System.out.println("====AOP:AdminPage====권한없음:403error");
			return "error/403";
		}
		return view;
	}
	
	//게시판의 글쓰기, 수정, 삭제 메서드는 로그인한 사용자만 이용가능
	@Around("execution(* spring.project.controller.Community*.add*(..))")
	public String communityAdd(ProceedingJoinPoint jp) throws Throwable{
		String view = (String) jp.proceed();
		
		String memid = getMemid();
		
		System.out.println("====AOP:Community-Add===="+memid);
		
		//로그인했는지 체크
		if(memid==null) {
			System.out.println("====AOP:Community-Add====로그인필요");
			return "member/loginForm";
		}
		return view;
	}
	@Around("execution(* spring.project.controller.Community*.mod*(..))")
	public String communityMod(ProceedingJoinPoint jp) throws Throwable{
		String view = (String) jp.proceed();
		
		String memid = getMemid();
		
		System.out.println("====AOP:Community-Mod===="+memid);
		
		//로그인했는지 체크
		if(memid==null) {
			System.out.println("====AOP:Community-Mod====로그인필요");
			return "member/loginForm";
		}
		return view;
	}
	@Around("execution(* spring.project.controller.Community*.del*(..))")
	public String communityDel(ProceedingJoinPoint jp) throws Throwable{
		String view = (String) jp.proceed();
		
		String memid = getMemid();
		
		System.out.println("====AOP:Community-Del===="+memid);
		
		//로그인했는지 체크
		if(memid==null) {
			System.out.println("====AOP:Community-Del====로그인필요");
			return "member/loginForm";
		}
		return view;
	}
	
	//Help의 공지사항, FAQ의 등록, 수정, 삭제는 관리자(사원)만 가능
	@Around("execution(* spring.project.controller.HelpController.*Admin(..))")
	public String helpAdmin(ProceedingJoinPoint jp) throws Throwable{
		String view = (String) jp.proceed();
		
		String memid = getMemid();
		
		System.out.println("====AOP:Community-Add===="+memid);
		
		//로그인했는지 체크
		if(memid==null) {
			System.out.println("====AOP:Community-Add====로그인필요");
			return "member/loginForm";
		}
		//로그인한 ID가 사원이상인지 체크
		if(adminService.checkIfEmp(memid)==0) {
			System.out.println("====AOP:AdminPage====권한없음:403error");
			return "error/403";
		}
		return view;
	}
	
	//Help의 모든 등록,수정,삭제는 로그인한 사용자 이상만 가능
	@Around("execution(* spring.project.controller.Help*.add*(..))")
	public String helpAdd(ProceedingJoinPoint jp) throws Throwable{
		String view = (String) jp.proceed();
		
		String memid = getMemid();
		
		System.out.println("====AOP:Help-Add===="+memid);
		
		//로그인했는지 체크
		if(memid==null) {
			System.out.println("====AOP:Help-Add====로그인필요");
			return "member/loginForm";
		}
		return view;
	}
	@Around("execution(* spring.project.controller.Help*.mod*(..))")
	public String helpMod(ProceedingJoinPoint jp) throws Throwable{
		String view = (String) jp.proceed();
		
		String memid = getMemid();
		
		System.out.println("====AOP:Help-Mod===="+memid);
		
		//로그인했는지 체크
		if(memid==null) {
			System.out.println("====AOP:Help-Mod====로그인필요");
			return "member/loginForm";
		}
		return view;
	}
	@Around("execution(* spring.project.controller.Help*.del*(..))")
	public String helpDel(ProceedingJoinPoint jp) throws Throwable{
		String view = (String) jp.proceed();
		
		String memid = getMemid();
		
		System.out.println("====AOP:Help-del===="+memid);
		
		//로그인했는지 체크
		if(memid==null) {
			System.out.println("====AOP:Help-del====로그인필요");
			return "member/loginForm";
		}
		return view;
	}
	
	//MyPage는 로그인한 사용자만 접근가능
	@Around("execution(* spring.project.controller.MyPage*.*(..))")
	public String myPage(ProceedingJoinPoint jp) throws Throwable {
		String view = (String) jp.proceed();
		
		String memid = getMemid();
		
		System.out.println("====AOP:myPage===="+memid);
		
		//로그인했는지 체크
		if(memid==null) {
			System.out.println("====AOP:myPage====로그인필요");
			return "member/loginForm";
		}
		return view;
	}
}
