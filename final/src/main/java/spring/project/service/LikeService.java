package spring.project.service;

import spring.project.model.LikeDTO;

public interface LikeService{
	
	// �� �߰�
	public int addLike(LikeDTO like);
	
	// �� ����
	public void deleteLikePro(LikeDTO like);
}
