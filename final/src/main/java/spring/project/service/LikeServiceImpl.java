package spring.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.project.mapper.LikeMapper;
import spring.project.model.LikeDTO;

@Service
public class LikeServiceImpl implements LikeService{

	@Autowired
	private LikeMapper likeMapper;
	
	@Override
	public int addLike(LikeDTO like) {
		
		LikeDTO checkLike = likeMapper.checkLike(like);
				
		if(checkLike != null) {
			return 1;
		}
				
		// Âò µî·Ï
		try {
			return likeMapper.addLike(like);
		} catch (Exception e) {
			return 0;
		}

	}
}
