package spring.project.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
import spring.project.model.CertiMatchDTO;
import spring.project.model.CertiRequirementDTO;
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
	
	//국가기술자격 연령별/성별 합격자수 통계데이터 추가(pass_detail)
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
	
	//국가기술자격 일정정보 추가(certidate)
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
		String url = "http://openapi.q-net.or.kr/api/service/rest/InquiryAttenQualSVC/getList?ServiceKey=yapz%2B1EpEAK%2BuivcMayhbsLMyJrcxm7Bm3vYUA%2BAgsvrEyFKrVQllmU4ERm8b1jBS4ULE0ZOMIFEBvjfDbEZBQ%3D%3D&numOfRows=10&pageNo=";
		int i=1;

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		
		//ArrayList<CertiMatchDTO> list = new ArrayList<CertiMatchDTO>();
		while(true) {
			Document doc = db.parse(url+i);
			i++;
			doc.getDocumentElement().normalize();
			
			NodeList nodes = doc.getDocumentElement().getElementsByTagName("item");
			if(nodes.getLength() == 0) break;
			for(int j=0; j<nodes.getLength(); j++) {
				Node node = nodes.item(j);
				if(node.getNodeType() == Node.ELEMENT_NODE) {
					Element e = (Element)node;
					CertiMatchDTO dto = new CertiMatchDTO();
					dto.setCfrom(getTagValue("jmNm",e));
					dto.setCto(getTagValue("recomJmNm1",e));
					//list.add(dto);
					System.out.println(dto);
					ds.addCertiRelated(dto);
					dto = new CertiMatchDTO();
					dto.setCfrom(getTagValue("jmNm",e));
					dto.setCto(getTagValue("recomJmNm2",e));
					//list.add(dto);
					System.out.println(dto);
					ds.addCertiRelated(dto);
				}

			}
		
		}
		
		//for(CertiMatchDTO dto : list) System.out.println(dto);
	}
	
	// xml node 이름으로 값 불러오기
	private static String getTagValue(String tag, Element eElement) {
	    NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
	    Node nValue = (Node) nlList.item(0);
	    if(nValue == null) 
	        return null;
	    return nValue.getNodeValue();
	}
	
	//합격률 정보 저장(pass_rate)
	@RequestMapping("addPassRate")
	public void addPassRate() throws Exception {
		
		FileInputStream fis = new FileInputStream(new File("f:/data/minganstat2.csv"));
		BufferedReader br = new BufferedReader(new InputStreamReader(fis, "CP949"));
		
		String strLine;
		ArrayList<String> strList = new ArrayList<String>();
		
		while((strLine=br.readLine()) != null) {
			strList.add(strLine);
		}
		ds.addPassRate(strList);
	}
	
	//민간자격증 데이터 추가 (certiinfo)
	@RequestMapping("addPrvInfo")
	public void addPrvInfo() throws Exception{
		ds.addPrvInfo();
	}
	
}
