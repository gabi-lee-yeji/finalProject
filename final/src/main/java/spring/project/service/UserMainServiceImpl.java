package spring.project.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.project.mapper.UserMainMapper;
import spring.project.model.CertiDateDTO;

@Service
public class UserMainServiceImpl implements UserMainService{
	
	@Autowired
	UserMainMapper mapper;

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

}
