package spring.project.service;

import spring.project.model.CertiDateDTO;
import spring.project.model.CertiInfoDTO;
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
	
}
