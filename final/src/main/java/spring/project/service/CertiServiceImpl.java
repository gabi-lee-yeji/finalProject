package spring.project.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.project.mapper.CertiMapper;
import spring.project.model.CertiAccessible;
import spring.project.model.CertiDateDTO;
import spring.project.model.CertiFilterDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.CertiRequirementDTO;
import spring.project.model.CertiScheduleDTO;
import spring.project.model.MypageNewsDTO;
import spring.project.model.PassDetailDTO;
import spring.project.model.PassRateAccessible;
import spring.project.pagination.PagingDTO;
import spring.project.model.LikeDTO;

@Service
public class CertiServiceImpl implements CertiService {

	@Autowired
	private CertiMapper mapper;
	static Map<String, CertiAccessible> certiMap = new HashMap<String, CertiAccessible>();

	@Override
	public List<CertiInfoDTO> getCertiList(int startRow, int endRow, String category) {
		return mapper.getCertiList(startRow, endRow, category);
	}
	
	
	@Override
	public Map<String, CertiAccessible> getCertiInfo(String cnum) {
		CertiInfoDTO info = mapper.getCertiInfo(cnum);
		CertiRequirementDTO requirement = mapper.getCertiReqInfo(cnum);
		certiMap.put("info", info);
		certiMap.put("req", requirement);
		return certiMap;
	}
	
	@Override
	public List<CertiDateDTO> searchPeriod(String cnum){
		return mapper.searchPeriod(cnum);
	}
	
	@Override
	public List<CertiDateDTO> searchNatPeriod(String cnum){
		List<Integer> cyear_list = new ArrayList<Integer>();
		List<Integer> cround_list = new ArrayList<Integer>();
		
		List<CertiScheduleDTO> schedule = mapper.getQnetDateInfo(cnum);
		for(CertiScheduleDTO dto : schedule ) {
			cyear_list.add(dto.getCyear());
			cround_list.add(dto.getCround());
		}
		String clevel = schedule.get(0).getClevel();
	
		return mapper.searchNatPeriod(clevel, cyear_list, cround_list);
	}
	
	@Override
	public int getCertCnt() {
		return mapper.getCertCnt();
	}

	@Override
	public List<CertiInfoDTO> getCertiLangList(PagingDTO page) {
		return mapper.getCertiLangList(page);
	}
	@Override
	public int getCertiLangCnt() {
		return mapper.getCertiLangCnt();
	}

	@Override
	public ArrayList<MypageNewsDTO> getNews(String cnum) throws Exception{

		RConnection rc = new RConnection();
		
		rc.eval("library(rvest)");
		rc.eval("presss <- character()");
		rc.eval("infos <- character()");
		rc.eval("links <- character()");
		rc.eval("titles <- character()");
		rc.eval("contents <- character()");
		
		String cname = mapper.getCertiInfo(cnum).getCname();
		cname = "\"" + cname + "\"";
		
		rc.eval("url <- \"https://search.naver.com/search.naver?where=news&query=\"");
		rc.eval("text <- read_html( paste0(url,"+ "'\"'," + cname+ ",'\"'" + ")) ");
		
		rc.eval("for (i in 1:20){"
				+ "  nodes <- html_nodes(text, paste0(\"#sp_nws\",i,\" > div > div > div.news_info > div.info_group > a\"));"
				+ "  press <- html_text(nodes) ;"
				+ "  presss <- c(presss, press);"
				
				+ "  nodes <- html_nodes(text, paste0(\"#sp_nws\",i,\" > div > div > div.news_info > div.info_group > span\"));"
				+ "  info <- html_text(nodes);"
				+ "  infos <- c(infos,info);"
				
				+ "  nodes <- html_nodes(text, paste0(\"#sp_nws\",i,\" > div > div > a\"));"
				+ "  link <- html_attr(nodes, \"href\");"
				+ "  links <- c(links,link);"
				+ "  title <- html_text(nodes);"
				+ "  titles <- c(titles, title);"
				
				+ "  nodes <- html_nodes(text, paste0(\"#sp_nws\",i,\" > div > div > div.news_dsc > div > a\"));"
				+ "  content <- html_text(nodes);"
				+ "  if(length(content)!=0) content <- paste0(substring(content,1,100), \"...\");"
				+ "  contents <- c(contents, content);"
				+ "}");
		
		String [] press = rc.eval("presss").asStrings();
		String [] info = rc.eval("infos").asStrings();
		String [] link = rc.eval("links").asStrings();
		String [] title = rc.eval("titles").asStrings();
		String [] content = rc.eval("contents").asStrings();
		
		rc.close();
		
		ArrayList<MypageNewsDTO> list = new ArrayList<MypageNewsDTO>();
		for(int i=0; i< Math.min(press.length,5) ; i++) {
			if(content[i].length() >50) {
				content[i] = content[i].substring(0,50) + "<br/>" + content[i].substring(50);
			}
			list.add(new MypageNewsDTO(press[i], info[i], link[i], title[i], content[i]));
		}
		
		return list;
	}
	
	@Override
	public ArrayList<PassDetailDTO> pyramidGraph(String cnum) {
		return mapper.pyramidGraph(cnum);
	}
	
	@Override
	public ArrayList<? extends PassRateAccessible> lineGraph(CertiInfoDTO dto) {
		if(dto.getCnum().charAt(0) == 'N')
			return mapper.lineGraphNat(dto);
		else
			return mapper.lineGraphPrv(dto);
	}
	

	@Override
	public List<CertiInfoDTO> getFilteredList(CertiFilterDTO dto) {	
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("category", dto.getCategory());
		parameterMap.put("ncs_cat", dto.getNcs_cat());
		parameterMap.put("company", dto.getCompany());
		return mapper.getCertiFilteredList(parameterMap);
	}
	
	@Override
	public List<CertiInfoDTO> getreqList(String req_age,String req_degree,String req_exp){
		return mapper.getreqList(req_age, req_degree, req_exp);
	}
	@Override
	public int count(String cnum, String memid) {
		return mapper.likeCheck(cnum,memid);
	}
	
	@Override
	public List<String> getLikeList(String memid){
		return mapper.getLikeList(memid);
	}
	
	@Override
	public List<Map<String, Object>> getNcsCodeList() {
		return mapper.getNcsCodeList();
	}
	
	@Override
	public int getCertiFilteredCnt(CertiFilterDTO dto) {
		return mapper.getCertiFilteredCnt(dto);
	}
	
	@Override
	public List<String> getNcsName(CertiFilterDTO dto) {
		return mapper.getNcsName(dto);
	}


}




