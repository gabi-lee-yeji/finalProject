package spring.project.mapper;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;

import spring.project.model.LikeDTO;

public interface LikeMapper {
	
	// 찜 등록
	public int addLike(LikeDTO like);
	
	// 찜 삭제
	public int deleteLike(LikeDTO like);
	
	// 찜 리스트 불러오기
	public List<LikeDTO> getLike(String memid);	
	
	// 찜 등록 확인
	public LikeDTO checkLike(LikeDTO like);
	
	public int likeCheck(@Param("cnum")String cnum, @Param("memid")String memid);
	
}
