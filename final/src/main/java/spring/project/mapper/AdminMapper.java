package spring.project.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import spring.project.model.CertiDateDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.CertiRequirementDTO;
import spring.project.model.CertiScheduleDTO;
import spring.project.model.MemberInfoDTO;

public interface AdminMapper {
	
	//�ڰ��� ��� = ������ ���̺� ���ÿ� insert
	public int addCertiInfo(CertiInfoDTO dto);
	public int addCertiSchedule(CertiScheduleDTO dto);
	public int addCertiDate(CertiDateDTO dto);
	public int addCertiReq(CertiRequirementDTO dto);
	
	public int findNextseq(String sequence);
	public int findCurrseq(String sequence);
	
	//�ڰ��� ��� 
	public List<CertiInfoDTO> getCertList(Map map);
	//��ϵ� �ڰ��� ����
	public int getCertCnt();
	
	//�ڰ��� ������ ������
	public CertiInfoDTO getCertiInfo(String cnum);
	public CertiRequirementDTO getCertiReqInfo(String cnum);
	
	public List<CertiDateDTO> searchPeriod(String cnum);
	public List<CertiScheduleDTO> getQnetDateInfo(String cnum);
	public List<CertiDateDTO> searchNatPeriod(@Param("clevel")String clevel,
											@Param("cyear_list")List<Integer> cyear_list,
											@Param("cround_list")List<Integer> cround_list);
	
	public CertiDateDTO getQnetDate(CertiScheduleDTO dto);
	public CertiDateDTO getCertiDate(String cnum);
	
	
	
	
	//�ڰ��� ����
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
	
	public List<CertiDateDTO> searchPeriod(Map<String,String> map);
	
	
	
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
