package spring.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.project.mapper.MemberMapper;
import spring.project.model.MemberInfoDTO;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberMapper mapper;
	
	@Override
	public void insertMember(MemberInfoDTO dto) {
		dto.setEmail(dto.getMail1()+'@'+dto.getMail2());
		dto.setMobile(dto.getPC()+' '+dto.getPhone1()+'-'+dto.getPhone2()+'-'+dto.getPhone3());
		mapper.insertMember(dto);
	}

	@Override
	public int userCheck(MemberInfoDTO dto) {
		return mapper.userCheck(dto);
	}

	@Override
	public MemberInfoDTO AccountInfo(String memid) {
		return mapper.AccountInfo(memid);
	}
	@Override
	public void deleteUser(MemberInfoDTO dto) {
		mapper.deleteUser(dto);
	}
	@Override
	public MemberInfoDTO findUser(MemberInfoDTO dto) {
		return mapper.findUser(dto); 
	}
	
	@Override
	public void modifyList(MemberInfoDTO dto) {
		dto.setMobile(dto.getPC()+' '+dto.getPhone1()+"-"+dto.getPhone2()+"-"+dto.getPhone3());
		dto.setEmail(dto.getMail1()+'@'+dto.getMail2());
		mapper.modifyList(dto);
	}
	
}
