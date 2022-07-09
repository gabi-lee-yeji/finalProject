package spring.project.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.project.mapper.AdminMapper;
import spring.project.model.CertiDetailDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.MemberInfoDTO;
import spring.project.model.PagingDTO;
import spring.project.model.QnetDateDTO;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	AdminMapper mapper;
	
	@Transactional
	@Override
	public int addCerti(CertiInfoDTO info, CertiDetailDTO detail) {
		String cnum = "";
		String sequence = "";
		
		if(info.getCategory().equals("국가기술")) {
			cnum = "N";
			sequence = "NAT_SEQ";
		}else if(info.getCategory().equals("공인민간")) {
			cnum = "P";
			sequence = "PRV_SEQ";
		}else if(info.getCategory().equals("어학")) {
			cnum = "L";
			sequence = "LANG_SEQ";
		}
		
		//시퀀스값이 0인 경우 초기값 설정 (+1)
		if(mapper.findCurrseq(sequence)==0) {
			mapper.findNextseq(sequence);
		}
		
		cnum += String.format("%05d", mapper.findCurrseq(sequence));
		
		info.setCnum(cnum); 
		detail.setCnum(cnum);
		
		int result = mapper.addCerti(info);
		result += mapper.addCertiDetail(detail);
		
		if(result==2) mapper.findNextseq(sequence);
		return result;
	}

	@Override
	public int modCerti(String cnum, CertiInfoDTO info, CertiDetailDTO detail) {
		info.setCnum(cnum); detail.setCnum(cnum);
		int result = mapper.modCertInfo(info);
		System.out.println("===info==="+result);
		//result += mapper.modCertDetail(info);
		System.out.println("===detail==="+result);
		return result;
	}

	@Override
	public List<CertiInfoDTO> getCertList(PagingDTO page, String sort, String order) {
		int startRow = page.getStartRow();
		int endRow = page.getEndRow();
		System.out.println("order by : "+sort+" "+order);
		return mapper.getCertList(startRow, endRow, sort, order);
	}
	@Override
	public int getCertCnt() {
		return mapper.getCertCnt();
	}
	

	@Override
	public List<Object> getCertiInfo(String cnum) {
		List<Object> list = new ArrayList<Object>();
		
		list.add(mapper.getCertiInfo(cnum));
		list.add(mapper.getCertiDetail(cnum));
		
		//국가기술자격인 경우 qnetdate에서 일정정보 가져오기
		if(cnum.substring(0,1).equals("N")) {
			list.add(mapper.getQnetdate(mapper.getCertiInfo(cnum)));
		}
		return list;
	}

	@Override
	public List<CertiInfoDTO> getSearchList(PagingDTO page, String search, String keyword) {
		int startRow = page.getStartRow();
		int endRow = page.getEndRow();
		return mapper.getSearchList(startRow, endRow, search, keyword);
	}

	@Override
	public int getSearchCnt(String search, String keyword) {
		return mapper.getSearchCnt(search, keyword);
	}

	@Override
	public void addQnetDate(QnetDateDTO dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CertiInfoDTO> getDelList(String[] cnumList) {
		return mapper.getDelList(cnumList);
	}
	
	@Transactional
	@Override
	public int delCerti(String[] cnumList) {
		int result = mapper.delCertiInfo(cnumList);
		System.out.println("==info=="+result);
		result += mapper.delCertiDetail(cnumList);
		System.out.println("==detail=="+result);
		return result;
	}

	@Override
	public List<MemberInfoDTO> getMemberList(PagingDTO page, String sort, String order) {
		// TODO Auto-generated method stub
		return null;
	}


	

	
//	@Override
//	public void addQnetDate(QnetDateDTO dto) {
//		mapper.addQnetDate(dto);
//	}
	
}
