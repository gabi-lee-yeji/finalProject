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
import spring.project.model.CertiFilterDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.CertiRequirementDTO;
import spring.project.model.MemberInfoDTO;
import spring.project.model.SearchAccessible;
import spring.project.pagination.PagingDTO;

@Service
public class UserMainServiceImpl implements UserMainService{
	
	@Autowired
	UserMainMapper mapper;
	@Autowired
	AdminService adminService;
	@Autowired
	CertiService certiService;
	
	//조회한 상세정보 목록의 날짜를 fullCalendar에 적용가능한 pattern으로 변경
	public List<CertiDateDTO> calFormatList(List<CertiDateDTO> list){
		List<CertiDateDTO> formatedList = new ArrayList<CertiDateDTO>();
		for(CertiDateDTO dto : list) {
			//List에 담긴 dto의 모든 필드의 정보를 Field배열에 저장 
			//Field 객체 = dto의 변수 하나의 정보를 담고 있음
			Field[] allFields = dto.getClass().getDeclaredFields();

			for(Field field : allFields) {
				field.setAccessible(true);  //private 변수도 접근 가능하게 설정
				try {
					Object value = field.get(dto); //해당 변수의 value 조회
					if(value!=null) { 
						String fieldValue = field.get(dto).toString(); 
						if(fieldValue.contains("T")) { //value가 T(날짜,시간 구분자)를 포함한 경우
							String[] valueArry = fieldValue.split("T");
							if(valueArry.length > 1) {
								if(fieldValue.split("T")[1].startsWith("00")) { //시간이 저장되지 않은 경우
									value = fieldValue.split("T")[0];  //일정에 시간을 뺀 날짜만 저장
									field.set(dto, value);  
								}
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
	
	//민간, 어학 시험의 전체 일정 조회 - main calendar
	@Override
	public List<CertiDateDTO> getCertiSchedules() {
		List<CertiDateDTO> list = mapper.getCertiSchedules(null);
		List<CertiDateDTO> formatedList = null;   
		
		if(list.size()>0) {
			formatedList = calFormatList(list);
		}
		
		if(formatedList.size()>0) {
			return formatedList;
		}
		
		return list;
	}
	
	//국가기술자격증의 전체 일정 조회 - main calendar
	@Override
	public List<CertiDateDTO> getNatSchedules() {
		List<CertiDateDTO> list = mapper.getNatSchedules();
		List<CertiDateDTO> formatedList = calFormatList(list);
		if(formatedList.size()>0) {
			return formatedList;
		}
		return list;
	}

	@Override
	public List<CertiInfoDTO> getClientTopCerti(String memid) {
		String order= getClientGenAge(memid);
		return mapper.getClientTopCerti(order);
	}
	@Override
	public String getClientGenAge(String memid) {
		//사용자 정보(나이, 성별 조회)
		MemberInfoDTO dto = adminService.getMemberInfo(memid);
		String gender = dto.getGender();
		int age = adminService.getMemberAge(memid);
		age = (int) Math.floor(age/10) * 10;
		if(0 < age && age < 10) age = 10;
		if(60 < age) age = 60;
		String order = gender+age;
		return order;
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
		
		List<CertiDateDTO> list = new ArrayList<CertiDateDTO>();
		List<CertiDateDTO> prvlist = null;
		List<CertiDateDTO> natlist = null;
		if(prvCnum.size() > 0) {
			prvlist = calFormatList(mapper.getCertiSchedules(prvCnum));
			list.addAll(prvlist);
		}
		if(natCnum.size() > 0) {
			natlist = calFormatList(mapper.getMemberNatSchedules(natCnum));
			list.addAll(natlist);
		}
		
		return list;
	}

	@Override
	public List<CertiDateDTO> getCertiDate(String cnum) {
		if(cnum.startsWith("N")) {
			if(certiService.findDateCount(cnum) == 0) {
				return calFormatList(mapper.getNatCertiDate(cnum));
			}
		}
		return calFormatList(mapper.getCertiDate(cnum));
	}

	@Override
	public List<CertiRequirementDTO> getCertiRequirement(String cnum) {
		//System.out.println(cnum);
		List<CertiRequirementDTO> list = mapper.getCertiRequirement(cnum);
		if(list.size()==0 && cnum.charAt(0) == 'N') {
			String clevel = mapper.checkClevel(cnum);
			list = mapper.getNatCertiRequirement(clevel);
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> getNcsCodeList() {
		return mapper.getNcsCodeList();
	}

	@Override
	public List<CertiInfoDTO> getCertiFilteredList(CertiFilterDTO dto, PagingDTO page) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("category", dto.getCategory());
		parameterMap.put("ncs_cat", dto.getNcs_cat());
		parameterMap.put("clevel", dto.getClevel());
		parameterMap.put("startRow", page.getStartRow());
		parameterMap.put("endRow", page.getEndRow());
		return mapper.getCertiFilteredList(parameterMap);
	}

	@Override
	public int getCertiFilteredCnt(CertiFilterDTO dto) {
		return mapper.getCertiFilteredCnt(dto);
	}

	@Override
	public List<String> getNcsName(CertiFilterDTO dto) {
		return mapper.getNcsName(dto);
	}

	@Override
	public CertiDateDTO getClosestNatSchedule() {
		return mapper.getClosestNatSchedule();
	}

	@Override
	public List<Map<String, Object>> getClosePrvTests() {
		return mapper.getClosePrvTests();
	}

	@Override
	public List<Map<String, Object>> getCloseNatTests() {
		return mapper.getCloseNatTests();
	}

	@Override
	public int getCloseTestCnt(String category) {
		int checkIfNat = 1;
		if(!category.equals("national")) checkIfNat = 0;
		return mapper.getCloseTestCnt(checkIfNat);
	}

	@Override
	public List<String> getCnumOfCloseTests() {
		List<String> list = new ArrayList<String>();
		list.addAll(mapper.getCloseNatCnumList());
		list.addAll(mapper.getClosePrvCnumList());
		
		return list;
	}

	@Override
	public List<Map<String,Object>> getLanguageList() {
		return mapper.getLanguageList();
	}

	@Override
	public List<CertiInfoDTO> getLangFilteredList(PagingDTO page, int ncs_cat) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("ncs_cat", ncs_cat);
		parameterMap.put("startRow", page.getStartRow());
		parameterMap.put("endRow", page.getEndRow());
		return mapper.getLangFilteredList(parameterMap);
	}

	@Override
	public int getLangFilterCnt(int ncs_cat) {
		return mapper.getLangFilterCnt(ncs_cat);
	}

	@Override
	public List<String> getCloseLangCnumList() {
		return mapper.getCloseLangCnumList();
	}

	@Override
	public String getLangTestName(int ncs_cat) {
		return mapper.getLangTestName(ncs_cat);
	}

	@Override
	public List<SearchAccessible> getCnumSearchList(PagingDTO page, String cnum) {
		// TODO Auto-generated method stub
		return null;
	}

}
