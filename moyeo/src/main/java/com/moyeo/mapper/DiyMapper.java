package com.moyeo.mapper;

import java.util.List; 
import java.util.Map;

import com.moyeo.dto.Diy;

public interface DiyMapper {
	
	int insertDiy(Diy diy);
	int updateDiy(Diy diy);
	int loveCheck(Diy diy);
	int loveCancel(Diy diy);
	int deleteDiy(int diyIdx);
	
	// DIY 작성 디테일 페이지
	Diy selectDiy(int diyIdx);
	
	// 글에 저장된 아이디를 출력해서 로그인한 사용자와 비교해 수정 권한을 준다.
	Diy getUserinfoById(int diyIdx);
	
	// 전체 글 검색
	List<Diy> selectDiyList(Map<String, Object> map);
	
	// 제목으로 글 검색 
	List<Diy> selectDiyListByTitle(String diyTitle);
	
	// *** 내용으로 검색도 추가하기 
	
	// 전체 글 개수 
	int selectDiyCount(String selectKeyword);
	
	// *** 맵 추가하기
	
	// String userinfo, Userinfo userinfo
	
	// String id => 아이디 컬럼 하나만 가져와서 다른 테이블 아이디 where로 사용할수있게하고
		// 				User user로 다 가져오면모든걸 쓸수있음..?
	
	//userinfo-details
	int selectMyDiyCount(String accountId);
	List<Diy> selectMyDiyList(Map<String, Object> map);
	
	//count 관리자
	int selectDiyCount1(String selectKeyword);
	List<Diy> selectDiyList1(Map<String, Object> map);


	
}
