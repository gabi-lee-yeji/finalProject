package spring.project.mapper;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;

import spring.project.model.LikeDTO;

public interface LikeMapper {
	
	// �� �߰�
	public int addLike(LikeDTO like);
	
	// �� ����
	public int deleteLike(LikeDTO like);
	
	// �� ���
	public List<LikeDTO> getLike(String memid);	
	
	// �� Ȯ��
	public LikeDTO checkLike(LikeDTO like);
	
	public int likeCheck(@Param("cnum")String cnum, @Param("memid")String memid);
	
}
