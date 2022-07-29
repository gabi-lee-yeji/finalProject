package spring.project.service;

import java.util.ArrayList;
import java.util.List;

import spring.project.model.Comm_BoardDTO;
import spring.project.model.MemberInfoDTO;
import spring.project.model.Post_BoardDTO;


public interface MemberService {

	//회원가입 insert
	public void insertMember(MemberInfoDTO dto);
	
	//유저 체크 아이디,비밀번호를 조회해서 STATUS로 상태확인  
	public MemberInfoDTO userCheck(MemberInfoDTO dto);
	
	// status 변경 3(자진탈퇴)
	public void deleteUser(MemberInfoDTO dto);
	
	//유저 정보 변경시 정보 불러옴
	public MemberInfoDTO findUser(MemberInfoDTO dto);
	
	//유저 정보 update
	public void modifyList(MemberInfoDTO dto);
	
	//아이디 중복확인
	public int idDuplicate(String memid);
	
	//아이디찾기
	public MemberInfoDTO idFind(MemberInfoDTO dto);
	
	//내 게시글 목록
	public ArrayList<Post_BoardDTO> myList(String writer, int board_type, int startRow, int endRow);
	
	//내 게시글 총 카운트
	public int post_BoardCount(int board_type,String writer);
	
	//글,댓글 작성시 포인트 적립
	public int addMemberPoint(String memid, int comm_num, int pnum);
	
	//로그인 한 아이디의 회원상태 확인 
	public int memberStatusCheck(String memid);
	
	//내 댓글 목록
	public List<Comm_BoardDTO> myComments(String writer,int startRow,int endRow);
	
	//댓글 카운트
	public int commentsCount(String writer);
	
	//로그인시 로그인 한 시간으로 컬럼값을 변경(휴면 계정 가려내기 위함)
	public void updateTime(String memid);
	
	//아이디와 이메일 조건에 따른 회원의 존재여부
	public int pwCheck (MemberInfoDTO dto);
	
	//아이디와 이메일 조건에 따른 회원의 모든 정보
	public MemberInfoDTO pwFind(MemberInfoDTO dto);
	
	//휴면 계정에서 일반 계정으로 update
	public void domancyUpdate(MemberInfoDTO dto);
}
 