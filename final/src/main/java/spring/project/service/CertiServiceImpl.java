package spring.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.project.mapper.CertiMapper;
import spring.project.model.CertiDetailDTO;
import spring.project.model.CertiInfoDTO;

@Service
public class CertiServiceImpl implements CertiService {

	@Autowired
	CertiMapper mapper;
	
	@Override
	public List<CertiInfoDTO> getCertiNatList() {
		
		return mapper.getCertiNatList();
	}
	
	public List<CertiDetailDTO> getCertiDetailNatList() {
		
		return mapper.getCertiDetailNatList();
	}

	@Override
	public List<CertiInfoDTO> getCertiPrvList() {
		
		return mapper.getCertiPrvList();
	}

	@Override
	public List<CertiInfoDTO> getCertiLangList() {
		
		return mapper.getCertiLangList();
	}

}
