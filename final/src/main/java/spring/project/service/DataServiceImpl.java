package spring.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.project.mapper.AdminMapper;
import spring.project.mapper.DataMapper;
import spring.project.model.CertiDateDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.CertiScheduleDTO;
import spring.project.model.PassDetailDTO;

@Service
public class DataServiceImpl implements DataService {
	
	@Autowired
	private DataMapper mapper;
	@Autowired
	private AdminMapper am;
	
	@Override
	public void addQnetDate(CertiDateDTO dto) {
		mapper.addQnetDate(dto);
		
	}

	@Override
	public void addPassDetailN(PassDetailDTO dto) {
		mapper.addPassDetailN(dto);
	}
	
	@Override
	public int updateCertiDetail(CertiInfoDTO dto, String cname) {
		return mapper.updateCertiDetail(dto, cname);
	}

	@Override
	public String findCnum(String cname) {
		return mapper.findCnum(cname);
	}
	
	@Transactional
	@Override
	public void addNatCerti(CertiInfoDTO info, CertiScheduleDTO sch) {

		String cnum = mapper.findCnum(info.getCname());
		if(cnum == null) {

			//cnum 조합하기
			if(am.findCurrseq("NAT_SEQ") == 0) {
				am.findNextseq("NAT_SEQ");
			}
			info.setCnum("N"+String.format("%05d", am.findCurrseq("NAT_SEQ")));
			
			mapper.addNatCertiInfo(info);
			am.findNextseq("NAT_SEQ");
			sch.setCnum(info.getCnum());
		}else {
			sch.setCnum(cnum);
		}
		

		mapper.addNatCertiSchedule(sch);
	}
	
	@Transactional
	@Override
	public void splitCmethod() {
		List<CertiInfoDTO> list = mapper.getCmethods();
		
		for(CertiInfoDTO strLine : list) {
			if(strLine.getCmethod() == null) continue;
			
			String data = "";
			if(strLine.getCmethod().contains("필기")) {
				data += strLine.getCmethod().split("필기")[1].split("-")[0].replaceAll(":", "").trim();
			}
			if(strLine.getCmethod().contains("실기")) {
				data += "@"+strLine.getCmethod().split("실기")[1].split("※")[0].replaceAll(":", "").trim();
			}else if (strLine.getCmethod().contains("면접")) {
				data += "@"+strLine.getCmethod().split("면접",2)[1].replaceAll(":", "").trim();
			}
			if(strLine.getCmethod().contains("※")) {
				data += "^" + strLine.getCmethod().split("※")[1].trim();
			}
			strLine.setCmethod(data);
			mapper.updateCmethods(strLine);
		}
	}
	
	@Override
	@Transactional
	public void splitSubject() {
		List<CertiInfoDTO> list = mapper.getSubjects();
		
		for(CertiInfoDTO info : list) {
			//System.out.println(info.getSubject());
			if(info.getSubject() == null) continue;
			String pilgi = "";
			String silgi = "";
			String hap = "";
			String text = info.getSubject().replaceAll("(?<=\\()(.*?)(?=점\\))","");
			text = text.replaceAll("\\(점\\)", "");
			
			if(text.contains("필기")) {
				//필기시험 있는경우
				pilgi = text.split("필기", 2)[1].trim().split("실기")[0].replaceAll("[\\-:]", "").trim();
			}
			if(text.contains("실기")) {
				//실기시험 있는경우 @...
				silgi = text.split("실기",2)[1].trim().replaceAll("[\\-:]", "").trim();
			}
			boolean linetxt = false;
			if(!text.contains("필기") && !text.contains("실기")){
				//필기/실기 구분없이 과목명만 있는경우 = 필기로 간주
				pilgi = text.replaceAll("[\\-:]", "").trim();
				linetxt = true;
			}
			
			//System.out.println(info.getCnum() + "\t" + pilgi + "@" + silgi);

			//필기 과목 분리하기
			//System.out.println("\n"+info.getCnum());
			if(pilgi.contains("1.")) {
				//1. 2. 3. 으로 나눠진 경우
				for(String p : pilgi.split("(\\d+\\.)")) {
					if(p.equals("")) continue;
					if(p.trim().endsWith(",")) {
						p=p.trim().substring(0,p.trim().length()-1);
					}
					hap += p.trim()+"@";
				}
			}else {
				//, 으로 과목이 나눠진 경우
				
				//필기 / 실기로 구분된게 아닌 줄글인 경우(기술사 등)
				if(linetxt) {
					hap += pilgi;
				}else {
					
					pilgi = pilgi.replaceAll("에 관한 사항", "");
					ArrayList chl = new ArrayList();
					boolean br = false;
					for(int i=0; i<pilgi.toCharArray().length ; i++) {
						char ch = pilgi.toCharArray()[i];
						
						if(br) {
							if(ch==')') {
								br=false;
							}else {
								continue;
							}
						}
						
						if(ch=='(') {
							br=true;
						}
						if(ch==',') {
							chl.add(i);
						}
						
					}
					chl.add(0,0);
					chl.add(pilgi.length());
					for(int i=0; i<chl.size()-1; i++) {
						String sj = pilgi.substring((int) chl.get(i), (int)chl.get(i+1));
						if(sj.startsWith(",")) {
							sj = sj.substring(1);
						}
						if(sj.trim().endsWith(",")) {
							sj = sj.trim().substring(0, sj.length()-1);
						}
						hap += sj.trim()+"@";
					}
				}
			}
			hap += "^"+silgi;
			
			//System.out.println(hap);
			info.setSubject(hap);
			mapper.updateSubject(info);
		}
	}
	
	public void updateMingan(ArrayList<String> strList) {
		List<CertiInfoDTO> list = mapper.getMingan();
		ArrayList<String> cname = new ArrayList<String>();
		
		for(int i=0; i<list.size(); i++) {
			cname.add(list.get(i).getCname().replaceAll(" ", ""));
		}
		for(int i=0; i<strList.size(); i++) {
			if(cname.contains(strList.get(i).split(",",2)[0]) ) {
				
			}else {
				System.out.println(strList.get(i).split(",",2)[0]);
			}
		}
	}
}







