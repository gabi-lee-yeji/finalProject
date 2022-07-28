package spring.project.mapper;


import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import spring.project.model.Comm_BoardDTO;
import spring.project.model.MemberInfoDTO;
import spring.project.model.Post_BoardDTO;

public interface MemberMapper {
	
	//회원가입 insert
	public int insertMember(MemberInfoDTO dto);
	
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
	public ArrayList<Post_BoardDTO> myList
	(@Param("writer") String writer , @Param("board_type") int board_type , @Param("startRow") int startRow , @Param("endRow") int endRow);
	
	//내 게시글 총 카운트
	public int post_BoardCount(@Param("board_type")int board_type,@Param("writer")String writer);
	
	//글,댓글 작성시 포인트 적립
	public int addMemberPoint(@Param("memid") String memid,
								@Param("pnum")int pnum,
								@Param("comm_num") int comm_num);
	
	//로그인 한 아이디의 회원상태 확인 
	public int memberStatusCheck(String memid);
	
	//내 댓글 목록
	public List<Comm_BoardDTO> myComments(@Param("writer") String writer, @Param("startRow") int startRow , @Param("endRow") int endRow);
	
	//댓글 카운트
	public int commentsCount(String writer);
	
	//로그인시 로그인 한 시간으로 컬럼값을 변경(휴면 계정 가려내기 위함)
	public void updateTime(String memid);
	
	//아이디와 이메일 조건에 따른 회원의 존재여부
	public int pwCheck(MemberInfoDTO dto);
	
	//아이디와 이메일 조건에 따른 회원의 모든 정보
	public MemberInfoDTO pwFind(MemberInfoDTO dto);
	
	//휴면 계정에서 일반 계정으로 update
	public void domancyUpdate(MemberInfoDTO dto);
}
