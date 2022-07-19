package spring.project.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.project.mapper.MemberMapper;
import spring.project.model.MemberInfoDTO;
import spring.project.model.Post_BoardDTO;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberMapper mapper;
	
	@Override
	public void insertMember(MemberInfoDTO dto) {
		dto.setEmail(dto.getMail1()+'@'+dto.getMail2());
		dto.setMobile(dto.getPC()+' '+dto.getPhone1()+'-'+dto.getPhone2()+'-'+dto.getPhone3());
		dto.setAddr_detail(dto.getAddr_detail()+' '+dto.getExtraAddress());
		dto.setPasswdQ1(dto.getQuiz1()+" "+dto.getFindPw1());
		dto.setPasswdQ2(dto.getQuiz2()+" "+dto.getFindPw2());
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

	@Override
	public int idDuplicate(String memid) {
		return mapper.idDuplicate(memid);
	}

	@Override
	public MemberInfoDTO idFind(MemberInfoDTO dto) {
		dto.setMobile(dto.getPC()+" "+dto.getPhone1()+"-"+dto.getPhone2()+"-"+dto.getPhone3());
		return mapper.idFind(dto);
	}

	@Override
	public MemberInfoDTO pwFind(MemberInfoDTO dto) {
		dto.setPasswdQ1(dto.getQuiz1()+" "+dto.getFindPw1());
		dto.setPasswdQ2(dto.getQuiz2()+" "+dto.getFindPw2());
		return mapper.pwFind(dto);
	}

	@Override
	public ArrayList<Post_BoardDTO> myList(String writer,int board_type,int startRow,int endRow) {
		System.out.println(mapper.myList(writer,board_type,startRow,endRow));
		return mapper.myList(writer,board_type,startRow,endRow);
	}

	@Override
	public int post_BoardCount(int board_type,String writer) {
		return mapper.post_BoardCount(board_type,writer);
	}

	@Override
	public int addMemberPoint(String memid, int pnum, int comm_num) {
		return mapper.addMemberPoint(memid, pnum, comm_num);
	}

	@Override
	public int memberStatusCheck(String memid) {
		return mapper.memberStatusCheck(memid);
	}
}
