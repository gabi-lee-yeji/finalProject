package spring.project.service;

import spring.project.model.LikeDTO;

public interface LikeService{
	
	// �� �߰�
	public int addLike(LikeDTO like);
	
	// �� ����
	public int deleteLikePro(LikeDTO like);

	public LikeDTO check(LikeDTO like);
//	public int count(String num, String did);
	
	public int count(String cnum, String memid);
	
}
