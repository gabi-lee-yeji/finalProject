package spring.project.model;

import lombok.Data;

@Data
public class MypageNewsDTO {
	private String press;	//언론사
	private String info;	//작성시간(~일전 등)
	private String link;	//href 링크
	private String title;	//기사제목
	private String content;	//기사내용
	
	//생성자 오버로딩
	public MypageNewsDTO(String press, String info, String link, String title, String content) {
		super();
		this.press = press;
		this.info = info;
		this.link = link;
		this.title = title;
		this.content = content;
	}
	public MypageNewsDTO() {
		super();
	}
	
	
}
