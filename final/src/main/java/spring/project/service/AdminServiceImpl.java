package spring.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.project.mapper.AdminMapper;
import spring.project.model.CertiInfoDTO;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	AdminMapper mapper;

	@Override
	public int addCerti(CertiInfoDTO dto) {
		return mapper.addCerti(dto);
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
