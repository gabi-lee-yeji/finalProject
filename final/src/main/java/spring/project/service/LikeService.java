package spring.project.service;

import spring.project.model.LikeDTO;

public interface LikeService{
	
	// Âò Ãß°¡
	public int addLike(LikeDTO like);
	
	// Âò »èÁ¦
	public void deleteLikePro(LikeDTO like);
}
