package spring.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.project.mapper.DataMapper;
import spring.project.model.CertiDateDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.PassDetailDTO;

@Service
public class DataServiceImpl implements DataService {
	
	@Autowired
	private DataMapper mapper;
	
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
/*
	@Override
	public String findCnum(String cname) {
		return mapper.findCnum(cname);
	}*/
}
