package spring.project.service;

public interface DataService {

	//csv파일에서 qnet일정 읽어서 insert (certischedule)
	public void addQnetDate() throws Exception;
	
	//연령별/성별 합격자 정보(N=국가기술) (pass_detail)
	public void addPassDetailN() throws Exception;
	
	//minganstatN.csv 파일 - 민간자격증 급수별,연도별 접수/응시/합격자수
	public void addPassRate() throws Exception;
	
	//연관자격증 DB에 추가 (certi_related)
	public void addCertiRelated() throws Exception;
	
	//국가자격기술 데이터 추가(natfinal.csv -> certiinfo)
	public void addNatInfo() throws Exception;
	
	//국가기술자격 시험회차정보 입력(kki.csv -> certischedule)
	public void addNatSchedule() throws Exception;
	
	//NCS코드별 코드이름 입력(ncs.csv -> ncs_code)
	public void addNcsCode() throws Exception;
	
	//민간공인자격 데이터 추가(mingan4.csv, 062702.csv -> certiinfo)
	public void addPrvInfo() throws Exception;
	
	//자격증 응시조건 데이터 추가(certi_requirement)
	public void addCertiReq() throws Exception;
}


