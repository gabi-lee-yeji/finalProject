package spring.project.service;

import java.util.ArrayList;

import spring.project.model.CertiDateDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.CertiMatchDTO;
import spring.project.model.CertiScheduleDTO;
import spring.project.model.PassDetailDTO;

public interface DataService {

	//csv파일에서 qnet일정 읽어서 insert (certischedule)
	public void addQnetDate(CertiDateDTO dto);
	
	//연령별/성별 합격자 정보(N=국가기술) (pass_detail)
	public void addPassDetailN(PassDetailDTO dto);
	
	//certiinfo 테이블에 자격증 상세정보 업데이트 ********
	public int updateCertiDetail(CertiInfoDTO dto, String cname);
	
	//이름으로 cnum 검색해서 리턴한다
	public String findCnum(String cname);
	
	//certiinfo의 cmethod문자열을 필기/실기/면접 항목으로 분리한다
	public String splitCmethod(String cmethod);
	
	//certiinfo의 subject문자열을 필기/실기/면접 항목으로 분리
	public String splitSubject(String subject);
	
	//minganN.csv 파일-민간자격증 cname, company, cjob, clevel, cinfo 정보
	public void updateMingan(ArrayList<String> strList);
	
	//minganstatN.csv 파일 - 민간자격증 급수별,연도별 접수/응시/합격자수
	public void addPassRate(ArrayList<String> strList) ;
	
	//연관자격증 DB에 추가 (certi_related)
	public void addCertiRelated(CertiMatchDTO dto);
	
	//국가자격기술 데이터 추가(natfinal.csv -> certiinfo)
	public void addNatInfo() throws Exception;
	
	//국가기술자격 시험회차정보 입력(kki.csv -> certischedule)
	public void addNatSchedule() throws Exception;
	
	//NCS코드별 코드이름 입력(ncs.csv -> ncs_code)
	public void addNcsCode() throws Exception;
	
	//민간공인자격 데이터 추가(mingan4.csv, 062702.csv -> certiinfo)
	public void addPrvInfo() throws Exception;
}


