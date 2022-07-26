package spring.project.service;

import spring.project.model.LikeDTO;

public interface LikeService{
	
	// Âò Ãß°¡
	public int addLike(LikeDTO like);
	
	// Âò »èÁ¦
	public int deleteLikePro(LikeDTO like);

	public LikeDTO check(LikeDTO like);
//	public int count(String num, String did);
	
	public int count(String cnum, String memid);
	
}
