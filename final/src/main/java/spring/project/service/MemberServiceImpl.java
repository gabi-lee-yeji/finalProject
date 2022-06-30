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

		mapper.insertMember(dto);
	}

	@Override
	public int userCheck(MemberInfoDTO dto) {
			
		return mapper.userCheck(dto);
	}
	
	
}
