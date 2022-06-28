package spring.project.service;

import spring.project.model.CertiDetailDTO;
import spring.project.model.PassDetailDTO;
import spring.project.model.QnetDateDTO;

public interface DataService {

	//큐넷 일정 데이터(csv) 추가
	public void addQnetDate(QnetDateDTO dto);
	
	//PassDate csv데이터 추가(N=국가기술)
	public void addPassDetailN(PassDetailDTO dto);
	
	//certidetail 테이블 데이터 업데이트
	public int updateCertiDetail(CertiDetailDTO dto, String cname);
}
