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
				
		if(checkLike == null) {
			return 1;
		}else if(checkLike != null) {
			return 2;
		}
				
		// Âò µî·Ï
		try {
			return mapper.addLike(like);
		} catch (Exception e) {
			return 0;
		}
		
		
		
	}
	@Override
	public void deleteLikePro(LikeDTO like) {
		mapper.deleteLike(like);
	}
}
