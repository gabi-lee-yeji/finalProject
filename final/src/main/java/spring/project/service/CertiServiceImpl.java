package spring.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.project.mapper.CertiMapper;
import spring.project.model.CertiInfoDTO;
import spring.project.model.CertiRequirementDTO;
import spring.project.pagination.PagingDTO;

@Service
public class CertiServiceImpl implements CertiService {

	@Autowired
	private CertiMapper mapper;

	@Override
	public List<CertiInfoDTO> getCertiList(int startRow, int endRow, String category) {
		return mapper.getCertiList(startRow, endRow, category);
	}
	
	@Override
	public int getCertCnt() {
		return mapper.getCertCnt();
	}

	@Override
	public List<CertiInfoDTO> getCertiLangList() {
		
		return mapper.getCertiLangList();
	}

	@Override
	public List<CertiRequirementDTO> getCertiFilter() {
	      return mapper.getCertiFilter();
	   }


}
