package spring.project.model;

import lombok.Data;

@Data
public class PagingDTO {
	private int pageNum; 
	private int pageSize;
	
	private int currentPage;
	
	private int startRow;
	private int endRow;
	
	public PagingDTO(int pageSize, String pageNum) {
		this.pageSize = pageSize;
		if (pageNum == null) {
			pageNum = "1";
		}
		this.pageNum = Integer.parseInt(pageNum);
		this.startRow = (this.pageNum - 1) * pageSize + 1;
		this.endRow = this.pageNum * pageSize;
		this.currentPage = this.pageNum;
	}
	
}
