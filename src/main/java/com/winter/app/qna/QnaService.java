package com.winter.app.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winter.app.util.Pager;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QnaService {
	@Autowired
	private QnaMapper qnaMapper;

	public List<QnaVO> getList(Pager pager) throws Exception{
		//여기서 페이징 메서드가 실행되고, 글이 10개씩 나눠지게 되는데, mapper로 넘어가기 전에 얘를 처리해야해서 controller에서 하던지, service에서 페이징 메서드를 실행하던지 해야함
		//근데 역할을 분리하기 위해 페이징 하는 메서드를 여기에 선언함.
		//예를 들면 controller는 url 매핑, service는 페이징, dao(mapper)는 db와의 연동 이렇게 역할 분리를 위함
		//그리고 여기서 페이징을 선언했을 때 현재 브라우저에는 100-109번의 글만 나오는데, 내가 내림차순 정리해서 가장 마지막번호들이 첫번째에 오는 것이고,
		//첫번째 페이지만 보여주는 것이다. 그리고 Pager 클래스에 가보면 page를 1L로 설정해두었기 때문에 1페이지가 나온다. 만약에 여기서 기본값을 설정해주지 않으면 null로 startnum이 계산되므로
		//에러가 뜰 것이다. 따라서 에러가 뜨게 하지 않기 위해 page에 1L이라는 숫자를 우선적으로 넣어준 것이고, 내가 후에 page를 계산하는 메서드를 추가해줄 것이다.
		pager.makeRow();
		
		return qnaMapper.getList(pager);
	}
	
	//qnaVo에 있는 정보 가져와야 하니까 파라미터 줌
	public int add(QnaVO qnaVO) throws Exception{
		log.info("======== Before After BoardNum: {}", qnaVO.getBoardNum());
		int result = qnaMapper.add(qnaVO);
		log.info("======== Insert After BoardNum: {}", qnaVO.getBoardNum());
		//ref값에 boardNum을 넣기 위해 update를 침
		result = qnaMapper.refUpdate(qnaVO);
		
		
		return result;
	}
	
	//detail
	public QnaVO getDetail(QnaVO qnaVO) throws Exception{
		return qnaMapper.getDetail(qnaVO);
	}
}
