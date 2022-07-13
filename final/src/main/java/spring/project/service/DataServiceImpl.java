package spring.project.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.project.mapper.AdminMapper;
import spring.project.mapper.DataMapper;
import spring.project.model.CertiDateDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.CertiMatchDTO;
import spring.project.model.CertiRequirementDTO;
import spring.project.model.CertiScheduleDTO;
import spring.project.model.NcsDTO;
import spring.project.model.PassDetailDTO;
import spring.project.model.PassRateDTO;

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
	
	@Override
	public String splitCmethod(String cmethod) {
		
		if(cmethod == null) return "";
		
		String data = "";
		if(cmethod.contains("필기")) {
			data += cmethod.split("필기")[1].split("-")[0].replaceAll(":", "").trim();
		}
		if(cmethod.contains("실기")) {
			data += "@"+cmethod.split("실기")[1].split("※")[0].replaceAll(":", "").trim();
		}else if (cmethod.contains("면접")) {
			data += "@"+cmethod.split("면접",2)[1].replaceAll(":", "").trim();
		}
		if(cmethod.contains("※")) {
			data += "^" + cmethod.split("※")[1].trim();
		}
		return data;
	}
	
	@Override
	public String splitSubject(String subject) {
		
		if(subject==null) return "";
		
		String pilgi = "";
		String silgi = "";
		String hap = "";
		String text = subject.replaceAll("(?<=\\()(.*?)(?=점\\))","");
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
		
		
		return hap;
	}
	
	@Override
	public void updateMingan(ArrayList<String> strList) {
		List<CertiInfoDTO> list = mapper.getMingan();
		
		for(int i=0; i<strList.size(); i++) {

			CertiInfoDTO dto = new CertiInfoDTO();
			dto.setCname(strList.get(i).split(";")[0]);
			dto.setClevel(strList.get(i).split(";")[2]);
			if(dto.getClevel().equals("")) dto.setClevel(null);
			dto.setCompany(strList.get(i).split(";")[1]);
			dto.setCinfo(strList.get(i).split(";")[3]);
			dto.setCjob(strList.get(i).split(";")[4]);
			
			if(mapper.updateMingan(dto) != 1) {
				System.out.println(dto);
			}
		}
	}
	
	@Override
	public void addPassRate(ArrayList<String> strList) {
		List<CertiInfoDTO> list = mapper.getMingan();
		
		for(int i=0; i<strList.size(); i++) {
			
			PassRateDTO dto = new PassRateDTO();
			dto.setCname(strList.get(i).split(";")[0]);
			dto.setClevel(strList.get(i).split(";")[1]);
			dto.setCyear(Integer.parseInt(strList.get(i).split(";")[2]));
			dto.setApplied(Integer.parseInt(strList.get(i).split(";")[3]));
			dto.setTested(Integer.parseInt(strList.get(i).split(";")[4]));
			dto.setPassed(Integer.parseInt(strList.get(i).split(";")[5]));
			
			if(mapper.addPassRate(dto) != 1) {
				System.out.println(dto);
			}
			/*
			boolean f = false;
			for(int j =0 ; j<list.size(); j++) {
				
				if(list.get(j).getCname().equals(strList.get(i).split(";")[0]) &&
						strList.get(i).split(";")[1].equals(list.get(j).getClevel()==null?"":list.get(j).getClevel())) {
					f = true;
					break;
				}
				
			}
			if(!f)	System.out.println(strList.get(i).split(";")[0] + " // " + strList.get(i).split(";")[1]);
			*/
			
		}
	}
	
	@Override
	public void addCertiRelated(CertiMatchDTO dto) {
		mapper.addCertiRelated(dto);
	}
	
	
	@Override
	@Transactional
	public void addNatInfo() throws Exception{
		FileInputStream fis = new FileInputStream(new File("f:/data/natfinal.csv"));
		BufferedReader br = new BufferedReader(new InputStreamReader(fis, "CP949"));

		String strLine;
		
		while((strLine=br.readLine()) != null) {
			String [] datas = strLine.split(";");
			
			CertiInfoDTO dto = new CertiInfoDTO();
			
			dto.setCname(datas[0]);
			dto.setCinfo(datas[1]);
			dto.setCjob(datas[2]);
			dto.setWebsite(datas[3]);
			//datas[4]는 처리필요함
			HashMap<String,String> map = splitd4(datas[4]);
			dto.setSubject(splitSubject(map.get("subject")));
			dto.setRequirement(map.get("requirement"));
			dto.setCmethod(splitCmethod(map.get("cmethod")));
			dto.setCutline(map.get("cutline"));
			dto.setPrice(datas[5]);
			dto.setNcs_cat(datas[6]);
			dto.setCompany(datas[7]);
			dto.setClevel(datas[8]);
			
			dto.setCategory("국가기술");
			dto.setStatus("Y");
			
			if(am.findCurrseq("NAT_SEQ")==0) {
				am.findNextseq("NAT_SEQ");
			}
			dto.setCnum("N"+String.format("%05d", am.findNextseq("NAT_SEQ")));
			
			mapper.addNatData(dto);
			CertiRequirementDTO req = new CertiRequirementDTO();
			req.setCnum(dto.getCnum());
			am.addCertiReq(req);
		}
	}
	
	public HashMap<String,String> splitd4(String data) {
		
		int p = data.lastIndexOf("작업형 실기시험 기본정보");
		if(p != -1)
			data = data.substring(0,p);
		
		HashMap<String,String> result = new HashMap<String, String>();
		
		if(data.contains("시험과목")) {
			String datass = data.split("시험과목",2)[1].split("[①-⑩]",2)[0].trim().replaceAll("[ ]{2,}", " ");
			result.put("subject", datass);
		}
		if(data.contains("응시자격")) {
			String datass = data.split("응시자격",2)[1].split("[①-⑩]",2)[0].trim().replaceAll("[ ]{2,}", " ").replaceAll(":", "").trim();
			result.put("requirement", datass);
		}
		if(data.contains("검정기준")) {
			String datass = data.split("검정기준",2)[1].split("[①-⑩]",2)[0].trim().replaceAll("[ ]{2,}", " ");
			result.put("cmethod", datass);
		}else if(data.contains("검정방법")) {
			String datass = data.split("검정방법",2)[1].split("[①-⑩]",2)[0].trim().replaceAll("[ ]{2,}", " ");
			result.put("cmethod", datass);
		}
		if(data.contains("합격기준")) {
			String datass = data.split("합격기준",2)[1].split("[①-⑩]",2)[0].trim().replaceAll("[ ]{2,}", " ").replaceAll(":", "").trim();
			result.put("cutline", datass);
		}
		
		return result;
	}

	@Override
	@Transactional
	public void addNatSchedule() throws Exception{

		FileInputStream fis = new FileInputStream(new File("f:/data/kki.csv"));
		BufferedReader br = new BufferedReader(new InputStreamReader(fis, "CP949"));

		String strLine;
		
		while((strLine=br.readLine()) != null) {
			String [] datas = strLine.split(";");
			
			CertiScheduleDTO dto = new CertiScheduleDTO();
			String cnum = mapper.findCnum(datas[3]);
			
			if(cnum!=null) {
				dto.setCnum(cnum);
				dto.setClevel(datas[2]);
				if(!datas[0].equals(""))
					dto.setCyear(Integer.parseInt(datas[0]));
				if(!datas[1].equals(""))
					dto.setCround(Integer.parseInt(datas[1]));
				
				mapper.updateNatClevel(dto);
				mapper.addCertiSchedule(dto);
				
			}else {
				System.out.println(datas[3]);
			}
		}
	}
	
	@Override
	public void addNcsCode() throws Exception{

		FileInputStream fis = new FileInputStream(new File("f:/data/ncs.csv"));
		BufferedReader br = new BufferedReader(new InputStreamReader(fis, "CP949"));

		String strLine;
		
		while((strLine=br.readLine()) != null) {
			String [] datas = strLine.split(";");
			
			NcsDTO dto = new NcsDTO();
			dto.setCode(Integer.parseInt(datas[0]));
			dto.setLname(datas[1]);
			dto.setMname(datas[2]);
			
			mapper.addNcsCode(dto);
		}
	}
	
	@Override
	@Transactional
	public void addPrvInfo() throws Exception{
		
		//mingan4.csv insert
		FileInputStream fis = new FileInputStream(new File("f:/data/mingan4.csv"));
		BufferedReader br = new BufferedReader(new InputStreamReader(fis, "CP949"));

		String strLine;
		
		while((strLine=br.readLine()) != null) {
			String [] datas = strLine.split(";");
			
			CertiInfoDTO dto = new CertiInfoDTO();
			
			dto.setCname(datas[0]);
			dto.setCompany(datas[1]);
			dto.setClevel(datas[2]);
			dto.setCinfo(datas[3]);
			dto.setCjob(datas[4]);
			
			dto.setCategory("공인민간");
			dto.setStatus("Y");

			if(am.findCurrseq("PRV_SEQ")==0) {
				am.findNextseq("PRV_SEQ");
			}
			dto.setCnum("P"+String.format("%05d", am.findNextseq("PRV_SEQ")));
			
			mapper.addPrvInfo(dto);
		}
		
		//062702.csv update
		fis = new FileInputStream(new File("f:/data/062702.csv"));
		br = new BufferedReader(new InputStreamReader(fis, "CP949"));
		
		while((strLine=br.readLine()) != null) {
			String [] datas = strLine.split(";");
			
			CertiInfoDTO dto = new CertiInfoDTO();
			

			int cnum = mapper.findCnumCount(datas[0]);
			
			if(cnum!=0) {
				dto.setCname(datas[0]);
				dto.setWebsite(datas[4]);
				dto.setExpiry(datas[3]);
				
				mapper.updatePrvInfo1(dto);
			}else {
				System.out.println(datas[0]);
			}
		}
	}
}







