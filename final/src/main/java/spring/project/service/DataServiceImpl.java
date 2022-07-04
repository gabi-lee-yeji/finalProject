package spring.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.project.mapper.AdminMapper;
import spring.project.mapper.DataMapper;
import spring.project.model.CertiDateDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.CertiScheduleDTO;
import spring.project.model.PassDetailDTO;

@Service
public class DataServiceImpl implements DataService {
	
	@Autowired
	private DataMapper mapper;
	@Autowired
	private AdminMapper am;
	
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

	@Override
	public String findCnum(String cname) {
		return mapper.findCnum(cname);
	}
	
	@Transactional
	@Override
	public void addNatCerti(CertiInfoDTO info, CertiScheduleDTO sch) {
		if(info!=null) {
			//cnum 조합하기
			if(am.findCurrseq("NAT_SEQ") == 0) {
				am.findNextseq("NAT_SEQ");
			}
			info.setCnum("N"+String.format("%05d", am.findCurrseq("NAT_SEQ")));
			
			mapper.addNatCertiInfo(info);
			am.findNextseq("NAT_SEQ");
		}
		mapper.addNatCertiSchedule(sch);
	}
}







