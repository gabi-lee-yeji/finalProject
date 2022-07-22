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
	
	//????
	public MemberInfoDTO AccountInfo(String memid);
	
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
	
	//비밀번호 찾기
	public MemberInfoDTO pwFind(MemberInfoDTO dto);
	
	//내 게시글 목록
	public ArrayList<Post_BoardDTO> myList(String writer, int board_type, int startRow, int endRow);
	
	//내 게시글 총 카운트
	public int post_BoardCount(int board_type,String writer);
	
	public int addMemberPoint(String memid, int comm_num, int pnum);
	public int memberStatusCheck(String memid);
	
	public List<Comm_BoardDTO> myComments(String writer,int startRow,int endRow);
	public int commentsCount(String writer);
	public void updateTime(String memid);
}
 