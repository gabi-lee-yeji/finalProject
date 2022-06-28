package spring.project.pagination;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class PagingDTO {
	private String pageNum;
	private int PageN;
	
	private int pageSize;
	
	private int currentPage;
	
	private int startRow;
	private int endRow;
	
}
