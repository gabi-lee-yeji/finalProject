package spring.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.project.mapper.MemberMapper;
import spring.project.mapper.Post_BoardMapper;
import spring.project.model.Comm_BoardDTO;
import spring.project.model.MemberInfoDTO;
import spring.project.model.Post_BoardDTO;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberMapper mapper;
	
	//회원가입 insert
	@Override
	public void insertMember(MemberInfoDTO dto) {
		//mail로 받은 정보를 email로
		dto.setEmail(dto.getMail1()+'@'+dto.getMail2());
		//Pc와 Phone으로 받은 정보를 mobile로
		dto.setMobile(dto.getPC()+' '+dto.getPhone1()+'-'+dto.getPhone2()+'-'+dto.getPhone3());
		//주소들을 전부 더해서 저장
		dto.setAddr_detail(dto.getAddr_detail()+' '+dto.getExtraAddress());
		mapper.insertMember(dto);
	}
	
	//유저 체크 아이디,비밀번호를 조회해서 STATUS로 상태확인
	@Override
	public MemberInfoDTO userCheck(MemberInfoDTO dto) {
		return mapper.userCheck(dto);
	}
	
	// status 변경 3(자진탈퇴)
	@Override
	public void deleteUser(MemberInfoDTO dto) {
		mapper.deleteUser(dto);
	}
	
	//유저 정보 변경시 정보 불러옴
	@Override
	public MemberInfoDTO findUser(MemberInfoDTO dto) {
		return mapper.findUser(dto); 
	}
	
	//유저 정보 update
	@Override
	public void modifyList(MemberInfoDTO dto) {
		dto.setMobile(dto.getPC()+' '+dto.getPhone1()+"-"+dto.getPhone2()+"-"+dto.getPhone3());
		dto.setEmail(dto.getMail1()+'@'+dto.getMail2());
		mapper.modifyList(dto);
	}
	
	//아이디 중복확인
	@Override
	public int idDuplicate(String memid) {
		return mapper.idDuplicate(memid);
	}

	//아이디찾기
	@Override
	public MemberInfoDTO idFind(MemberInfoDTO dto) {
		dto.setMobile(dto.getPC()+" "+dto.getPhone1()+"-"+dto.getPhone2()+"-"+dto.getPhone3());
		return mapper.idFind(dto);
	}
	
	//아이디와 이메일 조건에 따른 회원의 존재여부
	@Override
	public int pwCheck(MemberInfoDTO dto) {
		return mapper.pwCheck(dto);
	}

	//내 게시글 목록
	@Override
	public ArrayList<Post_BoardDTO> myList(String writer,int board_type,int startRow,int endRow) {
		return mapper.myList(writer,board_type,startRow,endRow);
	}

	//내 게시글 총 카운트
	@Override
	public int post_BoardCount(int board_type,String writer) {
		return mapper.post_BoardCount(board_type,writer);
	}

	//내 댓글 목록
	@Override
	public List<Comm_BoardDTO> myComments(String writer,int startRow,int endRow) {
		return mapper.myComments(writer, startRow, endRow);
	}

	//댓글 카운트
	@Override
	public int commentsCount(String writer) {
		return mapper.commentsCount(writer);
	}
	
	//로그인시 로그인 한 시간으로 컬럼값을 변경(휴면 계정 가려내기 위함)
	@Override
	public void updateTime(String memid) {
		mapper.updateTime(memid);
	}
	
	//글,댓글 작성시 포인트 적립
	@Override
	public int addMemberPoint(String memid, int pnum, int comm_num) {
		return mapper.addMemberPoint(memid, pnum, comm_num);
	}
	
	//로그인 한 아이디의 회원상태 확인
	@Override
	public int memberStatusCheck(String memid) {
		return mapper.memberStatusCheck(memid);
	}
	
	//아이디와 이메일 조건에 따른 회원의 모든 정보
	@Override
	public MemberInfoDTO pwFind(MemberInfoDTO dto) {
		return mapper.pwFind(dto);
	}

	//휴면 계정에서 일반 계정으로 update//휴면 계정에서 일반 계정으로 update
	@Override
	public void domancyUpdate(MemberInfoDTO dto) {
		mapper.domancyUpdate(dto);
	}

}
