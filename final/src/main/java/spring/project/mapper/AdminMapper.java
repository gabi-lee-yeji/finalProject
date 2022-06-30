package spring.project.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import spring.project.model.CertiDateDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.CertiScheduleDTO;
import spring.project.model.MemberInfoDTO;

public interface AdminMapper {
	
	//�ڰ��� ��� = ������ ���̺� ���ÿ� insert
	public int addCertiInfo(CertiInfoDTO dto);
	public int addCertiSchedule(CertiScheduleDTO dto);
	public int addCertiDate(CertiDateDTO dto);
	
	public int findNextseq(String sequence);
	public int findCurrseq(String sequence);
	
	//�ڰ��� ��� 
	public List<CertiInfoDTO> getCertList(@Param("startRow")int startRow,
			@Param("endRow")int endRow,
			@Param("sort")String sort, 
			@Param("order")String order);
	//��ϵ� �ڰ��� ����
	public int getCertCnt();
	
	//�ڰ��� ������ ������
	public CertiInfoDTO getCertiInfo(String cnum);
	public CertiDateDTO getCertiDate(String cnum);
	
	public CertiScheduleDTO getQnetDateInfo(String cnum);
	public CertiDateDTO getQnetDate(CertiScheduleDTO dto);
	
	
	public int modCertInfo(CertiInfoDTO dto);
	public int modCertDetail(CertiInfoDTO dto);
	
	
	
	
	

	
	public List<CertiInfoDTO> getSearchList(@Param("startRow")int startRow,
											@Param("endRow")int endRow,
											@Param("search")String search, 
											@Param("keyword")String keyword);
	public int getSearchCnt(@Param("search")String search, 
							@Param("keyword")String keyword);
	
	public List<CertiInfoDTO> getDelList(String[] cnumList);
	public int delCertiInfo(String[] cnumList);
	public int delCertiDetail(String[] cnumList);
	
	
	public List<MemberInfoDTO> getMemberList(Map map);
	public int getMemberCnt();
	
	//ȸ�������� ��ȸ
	public MemberInfoDTO getMemberInfo(String memid);
	
	public List<MemberInfoDTO> getMemberFilter(Map map);
	
	//�Ű���� ȸ����� �������� 
	public List<MemberInfoDTO> getReportMemList(String status);
	//�Ű���� ȸ���� ���� ��������
	public List<Map<String, Object>> getreportMemInfo(String memid);
	//�Ű���� ȸ�� ���� ����
	public int updateRepMemStatus(@Param("memid")String memid, @Param("status")String status);
}
