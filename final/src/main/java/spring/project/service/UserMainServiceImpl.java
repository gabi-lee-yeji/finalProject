package spring.project.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.project.mapper.UserMainMapper;
import spring.project.model.CertiDateDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.MemberInfoDTO;
import spring.project.model.SearchAccessible;
import spring.project.pagination.PagingDTO;

@Service
public class UserMainServiceImpl implements UserMainService{
	
	@Autowired
	UserMainMapper mapper;
	@Autowired
	AdminService adminService;
	
	public List<CertiDateDTO> calFormatList(List<CertiDateDTO> list){
		List<CertiDateDTO> formatedList = new ArrayList<CertiDateDTO>();
		for(CertiDateDTO dto : list) {
			Field[] allFields = dto.getClass().getDeclaredFields();

			for(Field field : allFields) {
				field.setAccessible(true);  
				try {
					Object value = field.get(dto); 
					if(value!=null) { 
						String fieldValue = field.get(dto).toString(); 
						if(fieldValue.contains("T")) { 
							if(fieldValue.split("T")[1].startsWith("00")) {
								value = fieldValue.split("T")[0];  
								field.set(dto, value);  
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			formatedList.add(dto);
		}
		return formatedList;
	}
	
	@Override
	public List<CertiDateDTO> getCertiSchedules() {
		List<CertiDateDTO> list = mapper.getCertiSchedules(null);
		List<CertiDateDTO> newList = new ArrayList<CertiDateDTO>();   
		
		if(list.size()>0) {
			for(CertiDateDTO dto : list) {
				Field[] allFields = dto.getClass().getDeclaredFields();
	
				for(Field field : allFields) {
					field.setAccessible(true);  
					try {
						Object value = field.get(dto); 
						if(value!=null) { 
							String fieldValue = field.get(dto).toString(); 
							if(fieldValue.contains("T")) { 
								if(fieldValue.split("T")[1].startsWith("00")) {
									value = fieldValue.split("T")[0];  
									field.set(dto, value);  
								}
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				newList.add(dto);
			}
		}
		
		if(newList.size()>0) {
			return newList;
		}
		
		return list;
	}
	
	@Override
	public List<CertiDateDTO> getNatSchedules() {
		List<CertiDateDTO> list = new ArrayList<CertiDateDTO>();
		for(CertiDateDTO dto : mapper.getNatSchedules()) {
			//List�� ���� dto�� ��� ���� ���� Field��ü �迭�� ���� 
			Field[] allFields = dto.getClass().getDeclaredFields();

			for(Field field : allFields) {
				field.setAccessible(true);  //private�ʵ忡 ���ٰ����ϰ� ����
				try {
//					System.out.println("field Value :"+field.get(dto));
//					System.out.println("field Value String:"+field.get(dto).toString());
					Object value = field.get(dto); //�ʵ忡 ����� ���� ������ 
					if(value!=null) { 
						String fieldValue = field.get(dto).toString(); 
						if(fieldValue.contains("T")) { 
							if(fieldValue.split("T")[1].startsWith("00")) {
								value = fieldValue.split("T")[0];  //����� �ð��� ���� ��� ��¥�� ����
								field.set(dto, value);  
								//System.out.println("field Value :"+field.get(dto));
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			list.add(dto);
			//System.out.println(dto);
		}
		if(list.size()>0) {
			return list;
		}
		
		return mapper.getNatSchedules();
	}

	@Override
	public List<CertiInfoDTO> getClientTopCerti(String memid) {
		//사용자 정보(나이, 성별 조회)
		MemberInfoDTO dto = adminService.getMemberInfo(memid);
		String gender = dto.getGender();
		int age = adminService.getMemberAge(memid);
		age = (int) Math.floor(age/10) * 10;
		
		String order = gender+age;
		
		return mapper.getClientTopCerti(order);
	}

	@Override
	public List<CertiInfoDTO> getNatTopCerti() {
		return mapper.getNatTopCerti();
	}

	@Override
	public List<String> getPrvTopCerti() {
		return mapper.getPrvTopCerti();
	}

	@Override
	public List<SearchAccessible> getCertiSearchList(PagingDTO page, String keyword) {
		return mapper.getCertiSearchList(page.getStartRow(), page.getEndRow(), keyword);
	}

	@Override
	public Map<String, List<SearchAccessible>> getSearchList(PagingDTO page, String keyword) {
		int startRow = page.getStartRow();
		int endRow = page.getEndRow();
		Map<String, List<SearchAccessible>> map = new HashMap<String, List<SearchAccessible>>();
		map.put("certi", mapper.getCertiSearchList(startRow, endRow, keyword));
		map.put("help", mapper.getHelpBoardSearchList(startRow, endRow, keyword));
		map.put("community", mapper.getCommBoardSearchList(startRow, endRow, keyword));
		map.put("comment", mapper.getCommentSearchList(startRow, endRow, keyword));
		return map;
	}

	@Override
	public int getCertiSearchCnt(String keyword) {
		return mapper.getCertiSearchCnt(keyword);
	}

	@Override
	public int getHelpBoardSearchCnt(String keyword) {
		return mapper.getHelpBoardSearchCnt(keyword);
	}

	@Override
	public int getCommBoardSearchCnt(String keyword) {
		return mapper.getCommBoardSearchCnt(keyword);
	}

	@Override
	public int getCommentSearchCnt(String keyword) {
		return mapper.getCommentSearchCnt(keyword);
	}

	@Override
	public List<CertiDateDTO> getMemberCertiSchedules(String memid) {
		List<String> cnum = mapper.getMemberLike(memid);
		
		List<String> natCnum = new ArrayList<String>();
		List<String> prvCnum = new ArrayList<String>();
		
		for(String s : cnum) {
			if(s.startsWith("N")) {
				natCnum.add(s);
			}else {
				prvCnum.add(s);
			}
		}
		
		List<CertiDateDTO> prvlist = calFormatList(mapper.getCertiSchedules(prvCnum));
		List<CertiDateDTO> natlist = calFormatList(mapper.getMemberNatSchedules(natCnum));
		
		List<CertiDateDTO> list = new ArrayList<CertiDateDTO>();
		list.addAll(prvlist);
		list.addAll(natlist);
		return list;
	}

	@Override
	public List<CertiDateDTO> getCertiDate(String cnum) {
		if(cnum.startsWith("N")) {
			return calFormatList(mapper.getNatCertiDate(cnum));
		}
		return calFormatList(mapper.getCertiDate(cnum));
	}

}
