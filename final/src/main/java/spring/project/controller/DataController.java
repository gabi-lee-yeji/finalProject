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
	
	//자격증 응시조건 데이터 추가(certi_requirement)
	@RequestMapping("addCertiReq")
	public void addCertiReq() throws Exception {
		ds.addCertiReq();
	}
	
	//외국어 데이터 추가(certiinfo)
	@RequestMapping("addLangInfo")
	public void addLangInfo() throws Exception{
		ds.addLangInfo();
	}
	
	//PRV데이터 수동으로 가져온것들 DB에 업데이트(certiinfo)
	@RequestMapping("updatePrvInfo")
	public void updatePrvInfo() throws Exception{
		ds.updatePrvInfo();
	}
	
	//temp
	@RequestMapping("temp1")
	public void temp1() throws Exception{
		ds.temp1();
	}
	//temp2
	@RequestMapping("temp2")
	public void temp2() throws Exception{
		ds.temp2();
	}
}