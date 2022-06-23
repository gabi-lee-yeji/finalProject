package spring.project.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.project.mapper.AdminMapper;
import spring.project.model.CertiDetailDTO;
import spring.project.model.CertiInfoDTO;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	AdminMapper mapper;
	
	@Transactional
	@Override
	public int addCerti(CertiInfoDTO info, CertiDetailDTO detail) {
		String cnum = "";
		String sequence = "";
		
		if(info.getCategory().equals("�������")) {
			cnum = "N";
			sequence = "NAT_SEQ";
		}else if(info.getCategory().equals("���ιΰ�")) {
			cnum = "P";
			sequence = "PRV_SEQ";
		}else if(info.getCategory().equals("����")) {
			cnum = "L";
			sequence = "LANG_SEQ";
		}
		
		//���������� 0�� ��� �ʱⰪ ���� (+1)
		if(mapper.findCurrseq(sequence)==0) {
			mapper.findNextseq(sequence);
		}
		
		cnum += String.format("%05d", mapper.findCurrseq(sequence));
		
		info.setCnum(cnum); detail.setCnum(cnum);
		
		int result = mapper.addCerti(info);
		result += mapper.addCertiDetail(detail);
		
		if(result==2) mapper.findNextseq(sequence);
		return result;
	}

	@Override
	public int modCerti(CertiInfoDTO info, CertiDetailDTO detail) {
		String cnum = info.getCnum();
		int result = mapper.modCertInfo(info, cnum);
		System.out.println("===info==="+result);
		result += mapper.modCertDetail(info, cnum);
		System.out.println("===detail==="+result);
		return result;
	}

	@Override
	public int delCerti(String cnum) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CertiInfoDTO> getCertList() {
		return mapper.getCertList();
	}

	@Override
	public List<Object> getCertiInfo(String cnum) {
		List<Object> list = new ArrayList<Object>();
		list.add(mapper.getCertiInfo(cnum));
		list.add(mapper.getCertiDetail(cnum));
		
		//��������ڰ��� ��� qnetdate���� �������� ��������
		if(cnum.substring(0,1).equals("N")) {
			list.add(mapper.getQnetdate(mapper.getCertiInfo(cnum)));
		}
		return list;
	}

}
