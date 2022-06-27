package spring.project.service;

import spring.project.model.HelpNoticeDTO;

public interface HelpService {
	
	// 공지사항 글 등록
	public void noticeAdd(HelpNoticeDTO dto);

}
