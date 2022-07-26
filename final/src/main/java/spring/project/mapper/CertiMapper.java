package spring.project.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import spring.project.model.*;

public interface CertiMapper {
	
	// ��ü �ڰ��� ���
	public List<CertiInfoDTO> getCertiList (@Param("startRow") int startRow, @Param("endRow") int endRow,@Param("category")String category);
	
	
	// ���� �ڰ��� ���
	public List<CertiInfoDTO> getCertiLangList();
	
	// �ڰ��� ������
	public CertiInfoDTO getCertiInfo(String cnum);
	public CertiRequirementDTO getCertiReqInfo(String cnum);
	
	public List<CertiDateDTO> searchPeriod(String cnum);
	public List<CertiScheduleDTO> getQnetDateInfo(String cnum);
	public List<CertiDateDTO> searchNatPeriod(@Param("clevel")String clevel,
											@Param("cyear_list")List<Integer> cyear_list,
											@Param("cround_list")List<Integer> cround_list);
	
	// �ڰ��� ����
	public int getCertCnt();
	
/*	// �ش� �ڰ��� �� ���� Ȯ�ο� ������ ��������
	public MemberLikeDTO getLike(String memid);

	// �� ������ ���ϱ�
	public void setLike(MemberLikeDTO cnum);

	// �� ������ �� ��� ���
	public int deleteLike(MemberLikeDTO cnum); */		
	
	public PassDetailDTO pyramidGraph(String cnum);
	public ArrayList<PassRateNatDTO> lineGraphNat(CertiInfoDTO dto);
	public ArrayList<PassRateNatDTO> lineGraphPrv(CertiInfoDTO dto);
	//���͸� ��� ��ȸ
	public List<CertiInfoDTO> getreqList(@Param("req_age")String req_age,@Param("req_degree")String req_degree,@Param("req_exp")String req_exp);
	public List<CertiInfoDTO> getNcsList();
	
	//��Ȯ��
	public LikeDTO checkLike(LikeDTO like);
	
	public int likeCheck(@Param("cnum")String cnum, @Param("memid")String memid);
	
	public List<String> getLikeList(String memid);
	
	List<Map<String,Object>> getNcsCodeList();
	List<CertiInfoDTO> getCertiFilteredList(Map map);
	int getCertiFilteredCnt(CertiFilterDTO dto);
	List<String> getNcsName(CertiFilterDTO dto);
	
	
}
