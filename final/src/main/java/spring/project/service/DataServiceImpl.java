package spring.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.project.mapper.DataMapper;
import spring.project.model.CertiDetailDTO;
import spring.project.model.PassDetailDTO;
import spring.project.model.QnetDateDTO;

@Service
public class DataServiceImpl implements DataService {
	
	@Autowired
	private DataMapper mapper;
	
	@Override
	public void addQnetDate(QnetDateDTO dto) {
		mapper.addQnetDate(dto);
		
	}

	@Override
	public void addPassDetailN(PassDetailDTO dto) {
		mapper.addPassDetailN(dto);
	}
	
	@Override
	public int updateCertiDetail(CertiDetailDTO dto, String cname) {
		return mapper.updateCertiDetail(dto, cname);
	}
/*
	@Override
	public String findCnum(String cname) {
		return mapper.findCnum(cname);
	}*/
}
