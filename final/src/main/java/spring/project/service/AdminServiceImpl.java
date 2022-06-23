package spring.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.project.mapper.AdminMapper;
import spring.project.model.CertiDetailDTO;
import spring.project.model.CertiInfoDTO;
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
		
		if(mapper.findCurrseq(sequence)==0) {
			mapper.findNextseq(sequence);
		}
		
		cnum += String.format("%05d", mapper.findCurrseq(sequence));
		info.setCnum(cnum); 
		detail.setCnum(cnum);
		
		int result = mapper.addCerti(info);
		result += mapper.addCertiDetail(detail);
		
		if(result == 2) mapper.findNextseq(sequence);
		
		return result;
	}

	@Override
	public int modCerti(CertiInfoDTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delCerti(String cnum) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CertiInfoDTO> getCertList() {
		return mapper.getCertList();
	}
	
	@Override
	public void addQnetDate(QnetDateDTO dto) {
		mapper.addQnetDate(dto);
	}
	
}
