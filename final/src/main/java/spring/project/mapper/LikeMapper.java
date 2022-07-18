package spring.project.mapper;

import java.util.List;

import spring.project.model.LikeDTO;

public interface LikeMapper {
	
	// �� �߰�
	public int addLike(LikeDTO like);
	
	// �� ����
	public int deleteLike(int memid);
	
	// �� ���
	public List<LikeDTO> getLike(String memid);	
	
	// �� Ȯ��
	public LikeDTO checkLike(LikeDTO like);

}
