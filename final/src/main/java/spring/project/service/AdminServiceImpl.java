package spring.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.project.mapper.AdminMapper;
import spring.project.model.CertiDetailDTO;
import spring.project.model.CertiInfoDTO;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	AdminMapper mapper;
	
	@Transactional
	@Override
	public int addCerti(CertiInfoDTO info, CertiDetailDTO detail) {
		int result = mapper.addCerti(info);
		
		int cnum = mapper.findCnum();
		detail.setCnum(cnum);
		
		System.out.println("cnum :"+cnum);
		System.out.println("result1=="+result);
		
		result += mapper.addCertiDetail(detail);
		System.out.println("result2=="+result);
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
}
