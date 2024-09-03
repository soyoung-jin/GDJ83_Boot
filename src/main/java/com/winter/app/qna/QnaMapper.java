package com.winter.app.qna;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.winter.app.util.Pager;

@Mapper
public interface QnaMapper {
	//조회
	public List<QnaVO> getList(Pager pager) throws Exception;
	
	//추가
	public int add(QnaVO qnaVO) throws Exception;
}
