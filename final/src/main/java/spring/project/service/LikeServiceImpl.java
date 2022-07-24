package spring.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.project.mapper.LikeMapper;
import spring.project.model.LikeDTO;

@Service
public class LikeServiceImpl implements LikeService{

	@Autowired
	private LikeMapper mapper;
	
	@Override
	public int addLike(LikeDTO like) {
		
		LikeDTO checkLike = mapper.checkLike(like);
		
		if(checkLike != null) {
			return 2;
		}
		
		return mapper.addLike(like);
	}
	
	@Override
	public LikeDTO check(LikeDTO like) {
		return mapper.checkLike(like);
	}
	
	@Override
	public int count(String cnum, String memid) {
		return mapper.likeCheck(cnum,memid);
			};
			
	@Override
	public int deleteLikePro(LikeDTO like) {
		return mapper.deleteLike(like);
	}
}