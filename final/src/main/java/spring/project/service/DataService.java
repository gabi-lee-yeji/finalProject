package spring.project.service;

import java.util.ArrayList;

import spring.project.model.CertiDateDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.CertiMatchDTO;
import spring.project.model.CertiScheduleDTO;
import spring.project.model.PassDetailDTO;

public interface DataService {

	//csv파일에서 qnet일정 읽어서 insert
	public void addQnetDate(CertiDateDTO dto);
	
	//연령별/성별 합격자 정보(N=국가기술)
	public void addPassDetailN(PassDetailDTO dto);
	
	//certiinfo 테이블에 자격증 상세정보 업데이트
	public int updateCertiDetail(CertiInfoDTO dto, String cname);
	
	//이름으로 cnum 검색해서 리턴한다
	public String findCnum(String cname);
	
	//국가기술자격 추가
	public void addNatCerti(CertiInfoDTO info, CertiScheduleDTO sch);
	
	//certiinfo의 cmethod문자열을 필기/실기/면접 항목으로 분리한다
	public void splitCmethod();
	
	//certiinfo의 subject문자열을 필기/실기/면접 항목으로 분리
	public void splitSubject();
	
	//minganN.csv 파일-민간자격증 cname, company, cjob, clevel, cinfo 정보
	public void updateMingan(ArrayList<String> strList);
	
	//minganstatN.csv 파일 - 민간자격증 급수별,연도별 접수/응시/합격자수
	public void addPassRate(ArrayList<String> strList) ;
	
	//연관자격증 DB에 추가
	public void addCertiRelated(CertiMatchDTO dto);
	
}
