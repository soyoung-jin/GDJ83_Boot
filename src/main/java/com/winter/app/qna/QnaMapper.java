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
	
	public int refUpdate(QnaVO qnaVO) throws Exception;
	
	//디테일
	public QnaVO getDetail(QnaVO qnaVO) throws Exception;
	
	//파일추가
	public int addFile(QnaFileVO qnaFileVO) throws Exception;
	
	//파일 다운
	public QnaFileVO getFileDetail(QnaFileVO qnaFileVO) throws Exception;
}
