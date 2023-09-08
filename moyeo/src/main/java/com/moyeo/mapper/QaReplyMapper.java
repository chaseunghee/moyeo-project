package com.moyeo.mapper;

import java.util.List;

import com.moyeo.dto.QaReply;

public interface QaReplyMapper {
	int insertQaReply(QaReply qaReply);//댓글작성
	int updateQaReply(QaReply qaReply);//댓글수정
	int deleteQaReply(QaReply qaReply);//댓글삭제
	
	List<QaReply> selectQaReplyList(int qaIdx);//댓글 조회
}
