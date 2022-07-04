package spring.project.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import spring.project.model.CertiDateDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.CertiScheduleDTO;
import spring.project.model.PassDetailDTO;
import spring.project.service.AdminService;
import spring.project.service.DataService;

@Controller
@RequestMapping("/data/*")
public class DataController {

	@Autowired
	private AdminService as;
	@Autowired
	private DataService ds;
	
	//현재 들어가있는 공인민간 데이터에 만료일자 컬럼 업데이트
	@RequestMapping("updateCertiDetail")
	public void updateCertiDetail() throws IOException{
		FileInputStream fis = new FileInputStream(new File("f:/data/062703.csv"));
		BufferedReader br = new BufferedReader(new InputStreamReader(fis,"CP949"));

		String strLine;
		while((strLine = br.readLine()) != null) {
			//System.out.println(strLine);
			
			String [] datas = strLine.split(",");
			CertiInfoDTO dto = new CertiInfoDTO();
			
			dto.setExpiry(datas[1].replaceAll("-", ""));
			
			//System.out.println(dto.getCnum() + dto.getExpiry());
			if(ds.updateCertiDetail(dto, datas[0]) ==0 ) {
				System.out.println(datas[0]);
			}
		}
	}
	
	//공인민간자격증 certiinfo에 데이터 추가
	@RequestMapping("addCertiDetailP1")
	public void addCertiDetailP1() throws IOException{
		FileInputStream fis = new FileInputStream(new File("f:/data/062702.csv"));
		BufferedReader br = new BufferedReader(new InputStreamReader(fis,"CP949"));
		
		String strLine;
		while((strLine = br.readLine()) != null) {
			//System.out.println(strLine);
			
			String [] datas = strLine.split(",");
			CertiInfoDTO info = new CertiInfoDTO();
			
			
			info.setCname(datas[0]);
			info.setCategory("공인민간");
			info.setStatus("Y");
			
			info.setCompany(datas[2]);
			//info.setExpiry(datas[3].replaceAll("-", ""));
			if(datas.length <= 4) {
				info.setWebsite("");
			}else {
				info.setWebsite(datas[4]);
			}
			
			if(!datas[2].equals("")) {
				String [] levels = datas[1].split("@");
				int len = levels.length;
				
				for(int i=0; i<len ; i++) {
					//System.out.println("levels==="+levels[i].trim()); 
					info.setClevel(levels[i].trim());
					//System.out.println("********************\n"+info+"\n"+ detail+"********************\n");
					as.addCertiInfo(info, null,new CertiDateDTO(),null);
				}
			}
			
			
		}
	}
	
	//국가기술자격 연령별/성별 합격자수 통계데이터 추가
	@RequestMapping("addPassDetailN")
	public void addPassDetailN() throws IOException{
		FileInputStream fis = new FileInputStream(new File("d:/proj2/062701.csv"));
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		
		String strLine;
		while((strLine = br.readLine()) != null) {
			//System.out.println(strLine);
			String [] datas = strLine.split(",");
			
			PassDetailDTO dto = new PassDetailDTO();
			
			dto.setCname(datas[0]);
			dto.setM10(datas[1].equals("")?0:Integer.parseInt(datas[1]));
			dto.setM20(datas[2].equals("")?0:Integer.parseInt(datas[2]));
			dto.setM30(datas[3].equals("")?0:Integer.parseInt(datas[3]));
			dto.setM40(datas[4].equals("")?0:Integer.parseInt(datas[4]));
			dto.setM50(datas[5].equals("")?0:Integer.parseInt(datas[5]));
			dto.setM60((datas[6].equals("")?0:Integer.parseInt(datas[6]))
					+(datas[7].equals("")?0:Integer.parseInt(datas[7]))
					+(datas[8].equals("")?0:Integer.parseInt(datas[8])));
			dto.setF10(datas[9].equals("")?0:Integer.parseInt(datas[9]));
			dto.setF20(datas[10].equals("")?0:Integer.parseInt(datas[10]));
			dto.setF30(datas[11].equals("")?0:Integer.parseInt(datas[11]));
			dto.setF40(datas[12].equals("")?0:Integer.parseInt(datas[12]));
			dto.setF50(datas[13].equals("")?0:Integer.parseInt(datas[13]));
			dto.setF60((datas[14].equals("")?0:Integer.parseInt(datas[14]))
					+(datas[15].equals("")?0:Integer.parseInt(datas[15]))
					+(datas[16].equals("")?0:Integer.parseInt(datas[16])));
			dto.setTotal(Integer.parseInt(datas[17]));
			
			//System.out.println(dto);
			
			ds.addPassDetailN(dto);
			
		}
	}
	
	//국가기술자격 데이터 추가 certiinfo
	@RequestMapping("addQnetAll")
	public void addQnetAll() throws IOException {
		System.out.println("aa");
		FileInputStream fis = new FileInputStream(new File("F:/data/kki.csv"));
		BufferedReader br = new BufferedReader(new InputStreamReader(fis,"CP949"));
		
		String strLine;
		while((strLine = br.readLine()) != null) {
			System.out.println(strLine);
			String [] datas = strLine.split(",");
			
			String bef = "";
			String cnum = "";
			CertiInfoDTO info = null;
			CertiScheduleDTO sch = new CertiScheduleDTO();

			if(!datas[3].equals(bef)) {
				//중복이 아니면 certiinfo / certischedule 모두 입력
				bef = datas[3];
				
				info = new CertiInfoDTO();
				info.setCategory("국가기술");
				info.setCname(datas[3]);
				info.setClevel(datas[2]);
				info.setCompany("한국산업인력공단");
				info.setStatus("Y");
				cnum = ds.findCnum(datas[3]);
			}

			//중복데이터면 certischedule에만 들어간다
			sch.setCnum(cnum);
			if(!datas[1].equals(""))
				sch.setCround(Integer.parseInt(datas[1]));
			if(!datas[0].equals(""))
				sch.setCyear(Integer.parseInt(datas[0]));
			sch.setClevel(datas[2]);
			
			
			ds.addNatCerti(info, sch);
			System.out.println("********************************\n" + info + "\n" + sch);
		}
		
	}
	
	//certidate 테이블에 국가기술자격 데이터 추가
	@RequestMapping("addQnetDate")
	public void addQnetDate() throws IOException {
		
		FileInputStream fis = new FileInputStream(new File("F:/data/qnetdate.csv"));
		BufferedReader br = new BufferedReader(new InputStreamReader(fis, "CP949"));
		
		String strLine;
		while((strLine = br.readLine()) != null) {
			//System.out.println(strLine);
			CertiDateDTO qdto = new CertiDateDTO();
			String [] datas = strLine.split(",");
			for(int i=0; i<datas.length; i++) datas[i] = trimQuote(datas[i]); 
			
			qdto.setCyear(Integer.parseInt(datas[0]));
			qdto.setCround(Integer.parseInt(datas[1]));
			qdto.setClevel(datas[2]);
			qdto.setDocRegStart1(datas[3].substring(0, 8));
			qdto.setDocRegEnd1(datas[3].substring(8, 16));
			if(datas[3].length() > 16) {
				qdto.setDocRegStart2(datas[3].substring(16, 24));
				qdto.setDocRegEnd2(datas[3].substring(24, 32));
			}
			qdto.setDocTestStart(datas[4].substring(0,8));
			if(datas[4].length() > 8) {
				qdto.setDocTestEnd(datas[4].substring(8,16));
			}
			qdto.setDocResultStart(datas[5]);
			if(datas[6].length() > 0) {
				qdto.setDocSubmitStart(datas[6].substring(0, 8));
				qdto.setDocSubmitEnd(datas[6].substring(8, 16));
			}
			qdto.setPracRegStart1(datas[7].substring(0, 8));
			qdto.setPracRegEnd1(datas[7].substring(8, 16));
			if(datas[7].length() > 16) {
				qdto.setPracRegStart2(datas[7].substring(16, 24));
				qdto.setPracRegEnd2(datas[7].substring(24,32));
			}
			qdto.setPracTestStart(datas[8].substring(0,8));
			qdto.setPracTestEnd(datas[8].substring(8,16));
			qdto.setPracResStart(datas[9].substring(0, 8));
			if(datas[9].length() > 8) {
				qdto.setPracResEnd(datas[9].substring(8, 16));
			}
			
			//System.out.println(qdto);
			ds.addQnetDate(qdto);
			
		}
		
	}
	
	//csv파일 "=" """ 제거
	private static String trimQuote(String str) {
		str = str.replaceAll("\"=\"\"", "");
		str = str.replaceAll("\"\"\"", "");
		return str;
	}
	
	//국가기술자격 상세정보 업데이트(api csv)
	@RequestMapping("updateNDetail1")
	public void updateNDetail1() throws Exception {
		

		FileInputStream fis = new FileInputStream(new File("f:/data/ndetail.csv"));
		BufferedReader br = new BufferedReader(new InputStreamReader(fis,"CP949"));

		String strLine;
		
		while((strLine = br.readLine()) != null) {
			//System.out.println(strLine);
			String [] datas = strLine.split(",", 3);
			
			CertiInfoDTO dto = new CertiInfoDTO();
			int res = 0;
			//dto.setCnum( ds.findCnum(datas[0]) );
			
			switch(datas[1]) {
			case "개요":
				dto.setCinfo(datas[2]);
				res=1;
				break;
			case "수행직무":
				dto.setCjob(datas[2]);
				res=1;
				break;
			case "진로 및 전망":
				dto.setCjob(datas[2]);
				res=1;
				break;
			case "변천과정":
			case "실시기관명":
			case "실시기관 홈페이지":
			case "위 자격취득자에 대한 법령상 우대현황":
				break;
			case "출제경향":
				//?
				break;
			case "취득방법":
				//System.out.println(datas[2]);
				if(datas[2].contains("시험과목")) {
					String datass = datas[2].split("시험과목",2)[1].split("[①-⑩]",2)[0].trim().replaceAll("[ ]{2,}", " ");
					//System.out.println(datas[0] + "//" + datass);
					//System.out.println("시험과목");
					dto.setSubject(datass);
				}
				if(datas[2].contains("응시자격")) {
					String datass = datas[2].split("응시자격",2)[1].split("[①-⑩]",2)[0].trim().replaceAll("[ ]{2,}", " ").replaceAll(":", "").trim();
					//System.out.println("응시자격");
					dto.setRequirement(datass);
				}
				if(datas[2].contains("검정기준")) {
					String datass = datas[2].split("검정기준",2)[1].split("[①-⑩]",2)[0].trim().replaceAll("[ ]{2,}", " ");
					//System.out.println("검정기준");
					dto.setCmethod(datass);
				}else if(datas[2].contains("검정방법")) {
					String datass = datas[2].split("검정방법",2)[1].split("[①-⑩]",2)[0].trim().replaceAll("[ ]{2,}", " ");
					//System.out.println("검정방법");
					dto.setCmethod(datass);
				}
				if(datas[2].contains("합격기준")) {
					String datass = datas[2].split("합격기준",2)[1].split("[①-⑩]",2)[0].trim().replaceAll("[ ]{2,}", " ").replaceAll(":", "").trim();
					//System.out.println("합격기준");
					dto.setCutline(datass);
				}
				res=1;
				break;
			default:
				System.out.println(datas[1]);
				break;
			}
			
			if(res==1) {
				//System.out.println("*******************************"+datas[0]+"\n"+dto);
				if ( ds.updateCertiDetail(dto, datas[0]) == 0) {
					System.out.println(datas[0]);
				}
			}
			
			
		}
	}
	
	//NCS분류1 - 자격증 코드 목록따오기
	@RequestMapping("certiGetNCS")
	public void certiGetNCS() throws Exception{
		ArrayList <String> jmCdList = new ArrayList<String>();
		
		String url = "https://c.q-net.or.kr/openapi/cjmncslist.do?serviceKey=";
		url += "yapz%2B1EpEAK%2BuivcMayhbsLMyJrcxm7Bm3vYUA%2BAgsvrEyFKrVQllmU4ERm8b1jBS4ULE0ZOMIFEBvjfDbEZBQ%3D%3D";
		url += "&type=xml&pageNo=1&numOfRows=200";
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(url);
		doc.getDocumentElement().normalize();
		//System.out.println("root element===" + doc.getDocumentElement().getChildNodes());
		
		NodeList nodes = doc.getDocumentElement().getElementsByTagName("jmInfo");//.item(0).getChildNodes();
		//System.out.println(nodes);
		//System.out.println(nodes.getLength());
		for(int i=0; i<nodes.getLength(); i++) {
			Node node = nodes.item(i);
			//System.out.println("current element===="+node.getNodeName());
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) node;
				jmCdList.add(getTagValue("jmCd",e));
				System.out.println(getTagValue("jmCd",e));
			}
		}
		//System.out.println(jmCdList.size());
		
		url = "https://c.q-net.or.kr/openapi/cjmncsinfolist.do?serviceKey=";
		url += "yapz%2B1EpEAK%2BuivcMayhbsLMyJrcxm7Bm3vYUA%2BAgsvrEyFKrVQllmU4ERm8b1jBS4ULE0ZOMIFEBvjfDbEZBQ%3D%3D";
		url += "&type=xml&jmCd=";
		for(String jmCd : jmCdList) {
			
			try {doc = db.parse(url+jmCd);}
			catch (Exception e) {System.out.println("*********************error********************");continue;}
			doc.getDocumentElement().normalize();
			
			
			nodes = doc.getDocumentElement().getElementsByTagName("compeUnitInfo");
			ArrayList <String> temp=new ArrayList<String>();
			for(int i=0; i<nodes.getLength(); i++) {
				Node node = nodes.item(i);
				if(node.getNodeType() == Node.ELEMENT_NODE) {
					Element e = (Element)node;

					String tt = getTagValue("lclasCd", e) +getTagValue("mclasCd", e)+getTagValue("sclasCd", e);
					/*System.out.println(tt);
					if(!temp.contains(tt)) {
						System.out.println(tt);
						temp.add(tt);
					}*/
				}
			}/*
			if(temp.size()>3) {
				System.out.println(getTagValue("jmNm", doc.getDocumentElement()) + " " + jmCd);
				System.out.println(temp.size());
			}*/
		}
	}
	
	/*
	//연관 자격증 찾기
	@RequestMapping("certiMatchTest")
	public void certiMatchTest() throws Exception {
	}
	*/
	
	// xml node 이름으로 값 불러오기
	private static String getTagValue(String tag, Element eElement) {
	    NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
	    Node nValue = (Node) nlList.item(0);
	    if(nValue == null) 
	        return null;
	    return nValue.getNodeValue();
	}
}
