package spring.project.mapper;

import java.util.HashMap;
import java.util.List;

import spring.project.model.LikeDTO;

public interface LikeMapper {
	
	// Âò Ãß°¡
	public int addLike(LikeDTO like);
	
	// Âò »èÁ¦
	public int deleteLike(LikeDTO like);
	
	// Âò ¸ñ·Ï
	public List<LikeDTO> getLike(String memid);	
	
	// Âò È®ÀÎ
	public LikeDTO checkLike(LikeDTO like);
	
	public int likeCheck(String cnum, String memid);
	
}
