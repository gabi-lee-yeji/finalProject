package spring.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.project.service.DataService;

@Controller
@RequestMapping("/data/*")
public class DataController {

	@Autowired
	private DataService ds;
	
	//국가기술자격 연령별/성별 합격자수 통계데이터 추가(pass_detail)
	@RequestMapping("addPassDetailN")
	public void addPassDetailN() throws Exception{
		ds.addPassDetailN();
	}
	
	//국가기술자격 일정정보 추가(certidate)
	@RequestMapping("addQnetDate")
	public void addQnetDate() throws Exception {
		ds.addQnetDate();
	}
	
	//국가기술자격 상세정보 입력(certiinfo)
	@RequestMapping("addNatInfo")
	public void addNatInfo() throws Exception {
		ds.addNatInfo();
	}
	
	//국가기술자격 시험회차정보 입력(certischedule)
	@RequestMapping("addNatSchedule")
	public void addNatSchedule() throws Exception{
		ds.addNatSchedule();
	}
	
	//NCS코드명 입력(ncs_code)
	@RequestMapping("addNcsCode")
	public void addNcsCode() throws Exception{
		ds.addNcsCode();
	}
	
	//연관 자격증 찾기(certi_related)
	@RequestMapping("addCertiRelated")
	public void addCertiRelated() throws Exception {
		ds.addCertiRelated();
	}
	
	//합격률 정보 저장(pass_rate)
	@RequestMapping("addPassRate")
	public void addPassRate() throws Exception {
		
		ds.addPassRate();
	}
	
	//민간자격증 데이터 추가 (certiinfo)
	@RequestMapping("addPrvInfo")
	public void addPrvInfo() throws Exception{
		ds.addPrvInfo();
	}
	
}
