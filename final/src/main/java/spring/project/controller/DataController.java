package spring.project.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

import spring.project.model.CertiDetailDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.PassDetailDTO;
import spring.project.model.QnetDateDTO;
import spring.project.service.AdminService;
import spring.project.service.DataService;

@Controller
@RequestMapping("/data/*")
public class DataController {

	@Autowired
	private AdminService as;
	@Autowired
	private DataService ds;
	
	
	@RequestMapping("updateCertiDetail")
	public void updateCertiDetail() throws IOException{
		FileInputStream fis = new FileInputStream(new File("d:/proj2/062703.csv"));
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));

		String strLine;
		while((strLine = br.readLine()) != null) {
			//System.out.println(strLine);
			
			String [] datas = strLine.split(",");
			CertiDetailDTO dto = new CertiDetailDTO();
			
			dto.setExpiry(datas[1].replaceAll("-", ""));
			
			//System.out.println(dto.getCnum() + dto.getExpiry());
			if(ds.updateCertiDetail(dto, datas[0]) ==0 ) {
				System.out.println(datas[0]);
			}
		}
	}
	
	@RequestMapping("addcertiDetailP1")
	public void addCertiDetailP1() throws IOException{
		FileInputStream fis = new FileInputStream(new File("d:/proj2/062702.csv"));
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		
		String strLine;
		while((strLine = br.readLine()) != null) {
			//System.out.println(strLine);
			
			String [] datas = strLine.split(",");
			CertiInfoDTO info = new CertiInfoDTO();
			CertiDetailDTO detail = new CertiDetailDTO();
			
			info.setCname(datas[0]);
			info.setCategory("공인민간");
			
			detail.setCompany(datas[2]);
			if(datas.length <= 4) {
				detail.setWebsite("");
			}else {
				detail.setWebsite(datas[4]);
			}
			if(!datas[2].equals("")) {
				String [] levels = datas[1].split("@");
				int len = levels.length;
				
				for(int i=0; i<len ; i++) {
					//System.out.println("levels==="+levels[i].trim()); 
					detail.setClevel(levels[i].trim());
					//System.out.println("********************\n"+info+"\n"+ detail+"********************\n");
					as.addCerti(info, detail);
				}
			}
			
			
		}
	}
	
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
	
	@RequestMapping("addQnetAll")
	public String addQnetAll() throws IOException {
		
		FileInputStream fis = new FileInputStream(new File("F:/R/kki.csv"));
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		
		String strLine;
		while((strLine = br.readLine()) != null) {
			//System.out.println(strLine);
			String [] datas = strLine.split(",");
			
			CertiInfoDTO info = new CertiInfoDTO();
			CertiDetailDTO detail = new CertiDetailDTO();
			
			info.setCategory("국가기술");
			info.setCname(datas[3]);
			info.setCtype(datas[2]);
			info.setCround(Integer.parseInt(datas[1]));
			info.setCyear(Integer.parseInt(datas[0]));
			
			detail.setCompany("한국산업인력공단");
			
			as.addCerti(info,detail);
			//System.out.println(info);
			//System.out.println(detail);
		}
		
		return "admin/addQnetAll";
	}
	

	@RequestMapping("addQnetDate")
	public String addQnetDate() throws IOException {
		
		FileInputStream fis = new FileInputStream(new File("F:/R/qnetdate.csv"));
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		
		String strLine;
		while((strLine = br.readLine()) != null) {
			//System.out.println(strLine);
			QnetDateDTO qdto = new QnetDateDTO();
			String [] datas = strLine.split(",");
			for(String s : datas) s = trimQuote(s); 
			
			qdto.setCyear(Integer.parseInt(datas[0]));
			qdto.setCround(Integer.parseInt(datas[1]));
			qdto.setCtype(datas[2]);
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
			qdto.setDocResult(datas[5]);
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
		
		return "admin/addQnetDate";
	}
	
	//csv파일 불러올때 일부 숫자에 달린 =" " 제거
	private static String trimQuote(String str) {
		str = str.replaceAll("\"=\"\"", "");
		str = str.replaceAll("\"\"\"", "");
		return str;
	}
	
	//추천자격증 기능 관련 메서드(오류)
	@RequestMapping("certiMatchTest")
	public String certiMatchTest() throws Exception {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		Document document = builder.parse(new File("F:/data/jobCode.xml"));
		
		NodeList nodes = document.getElementsByTagName("content");
		System.out.println("리스트 수: "+ nodes.getLength());
		
		ArrayList<String> list = new ArrayList();
		
		for(int i=0; i<nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) node;
				//System.out.println(getTagValue("jobdicSeq",e));
				//list.add(getTagValue("jobdicSeq",e));
				DocumentBuilderFactory factory2 = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder2 = factory2.newDocumentBuilder();
				Document document2 = builder2.parse("http://www.career.go.kr/cnet/openapi/getOpenApi.xml?apiKey=34caf563dfd1941d93bc320d28211597&svcType=api&svcCode=JOB_VIEW&jobdicSeq="
						+ getTagValue("jobdicSeq",e));
				NodeList nodes2 = document2.getElementsByTagName("content");
				Node node2 = nodes2.item(0);
				Element f = (Element)node2;
				if(getTagValue("capacity",f) != null) {
					//System.out.println(getTagValue("job",f)+"("+ getTagValue("jobdicSeq",e) + ") : " + getTagValue("capacity",f));
					String str = getTagValue("capacity",f).replaceAll("[(.)]", "");
					String [] strs = str.split(",");
					for(String s : strs) {
						s=s.trim();
						s=s.replaceAll(" ", "");
						String data = "\"" + getTagValue("job",f)+ "\"," +s+"\n";
						System.out.print(data);
						
						FileOutputStream fstream = new FileOutputStream(new File("f:/data/ttt.csv"),true);
						fstream.write(data.getBytes());
						fstream.close();
					}
					
				}
			}
		}
		return "admin/test";
	}
	// xml 파일 tag값의 정보를 가져오는 메소드
	private static String getTagValue(String tag, Element eElement) {
	    NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
	    Node nValue = (Node) nlList.item(0);
	    if(nValue == null) 
	        return null;
	    return nValue.getNodeValue();
	}
}
