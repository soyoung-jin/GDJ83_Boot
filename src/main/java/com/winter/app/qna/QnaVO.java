package com.winter.app.qna;

import java.sql.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QnaVO {
	private Long boardNum;
	private String boardWriter;
	private String boardTitle;
	private String boardContents;
	private Date createDate;
	private Long ref;
	private Long step;
	private Long depth;
	private List<QnaFileVO> ar;

}
