package spring.project.model;

import lombok.Data;

@Data
public class MypageNewsDTO {
	private String press;
	private String info;
	private String link;
	private String title;
	private String content;
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
