package spring.project.pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PagingService {
	
	@Autowired
	private PagingDTO page;
	
	public PagingDTO getPaging(int pageSize, String pageNum) {
		
		page.setPageSize(pageSize);
		
		if (pageNum == null) {
			pageNum = "1";
		}
		page.setPageNum(pageNum);
		
		int pageN = Integer.parseInt(pageNum);
		page.setCurrentPage(pageN);
		page.setPageN(pageN);
		
		int startRow = (pageN - 1) * pageSize + 1;
		int endRow = pageN * pageSize;
		
		page.setStartRow(startRow);
		page.setEndRow(endRow);
		
		return page;
	}
}
