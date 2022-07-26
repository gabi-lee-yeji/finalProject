package spring.project.model;


import java.util.Date;

import lombok.Data;

@Data
//자격증 정보 DTO
public class CertiInfoDTO implements CertiAccessible{
	private String cnum;	//자격증 분류 PK
	private String cname;	//자격증 이름
	private String category; //자격증 종류(국가기술, 공인민간, 어학)
	private String clevel; // 자격증 등급(급수, 기사,산업기사 등)
	
	private String company; //주관사 
	private String website; //주관사 홈페이지
	
	private String requirement; //응시조건(certi_requirement테이블로 변경)
	
	private String cmethod; //응시방법 eg. 필기 실기..
	private String subject; //시험과목
	
	private String cutline; //합격기준
	private String cinfo; //개요
	
	private String cjob; //취득 후 정보(전망 등)
	
	private String expiry; //공인만료일
	private String status; //현행자격 여부(Y/N/D) 
	
	private String price; //시험 응시료
	
	private Integer ncs_cat;//NCS카테고리
	
	private String notice; //기타 
	
	private String registDate; //DB에 등록된 날짜	
	
	//보유자격증 정보를 가져오기 위한 변수 추가 
	//resultMap을 쓰기에 겹치는 변수가 많고, 2개만 추가하면되므로 같은 DTO 사용
	private String cert_name;    //member_cert 테이블의 자격증명 (cnum 존재시 필요 x)
	private Date expiryDate;	//보유자격증의 만료일자
}
