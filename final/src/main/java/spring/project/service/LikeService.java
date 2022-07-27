package spring.project.service;

import spring.project.model.LikeDTO;

public interface LikeService{
	
	// 찜 등록
	public int addLike(LikeDTO like);
	
	// 찜 삭제
	public int deleteLikePro(LikeDTO like);

	// 찜 등록 확인
	public LikeDTO check(LikeDTO like);
	public int count(String cnum, String memid);
	
}
