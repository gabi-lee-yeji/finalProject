package spring.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import spring.project.model.CertiAccessible;
import spring.project.model.CertiDateDTO;
import spring.project.model.CertiInfoDTO;
import spring.project.model.CertiRequirementDTO;
import spring.project.model.MypageNewsDTO;
import spring.project.model.PassDetailDTO;
import spring.project.model.PassRateAccessible;

public interface CertiService {
	
	// ��ü �ڰ��� ���
	public List<CertiInfoDTO> getCertiList(String cnum,int startRow, int endRow, String category, String req_degree,String req_age,
			String req_exp,String clevel);
	
	//��ϵ� �ڰ��� ��ü ����
	public int getCertCnt();
	
	// �ڰ��� ������
	public Map<String, CertiAccessible> getCertiInfo(String cnum);
	
	// �ڰ����� �������� ��� ��ȸ �� ���� �˻�
	public List<CertiDateDTO> searchPeriod(String cnum);
	public List<CertiDateDTO> searchNatPeriod(String cnum);
	
	// ���� �ڰ��� ���
	public List<CertiInfoDTO> getCertiLangList();

	// RServe이용해서 자격증 관련 뉴스 가져오기
	public ArrayList<MypageNewsDTO> getNews(String cnum) throws Exception;

	// cnum에 해당하는 pass_detail테이블 정보 가져오기
	public ArrayList<PassDetailDTO> pyramidGraph(String cnum);
	
	// cnum에 해당하는 pass_rate(_nat)테이블 정보 가져오기
	public ArrayList<? extends PassRateAccessible> lineGraph(CertiInfoDTO dto);
}
