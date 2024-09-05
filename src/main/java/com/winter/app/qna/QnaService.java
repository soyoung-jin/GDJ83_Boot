package com.winter.app.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.util.FileManager;
import com.winter.app.util.Pager;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QnaService {
	@Autowired
	private QnaMapper qnaMapper;
	
	//이 두개의 변수를 합친 걸 FileManager로 보내주려 함
	@Value("${app.upload}") //D:/upload/
	private String upload;
	
	@Value("${board.qna}") //qna
	private String name;
	
	//파일매니저 클래스 객체 추가
	@Autowired
	private FileManager fileManager;

	
	public List<QnaVO> getList(Pager pager) throws Exception{
		//여기서 페이징 메서드가 실행되고, 글이 10개씩 나눠지게 되는데, mapper로 넘어가기 전에 얘를 처리해야해서 controller에서 하던지, service에서 페이징 메서드를 실행하던지 해야함
		//근데 역할을 분리하기 위해 페이징 하는 메서드를 여기에 선언함.
		//예를 들면 controller는 url 매핑, service는 페이징, dao(mapper)는 db와의 연동 이렇게 역할 분리를 위함
		//그리고 여기서 페이징을 선언했을 때 현재 브라우저에는 100-109번의 글만 나오는데, 내가 내림차순 정리해서 가장 마지막번호들이 첫번째에 오는 것이고,
		//첫번째 페이지만 보여주는 것이다. 그리고 Pager 클래스에 가보면 page를 1L로 설정해두었기 때문에 1페이지가 나온다. 만약에 여기서 기본값을 설정해주지 않으면 null로 startnum이 계산되므로
		//에러가 뜰 것이다. 따라서 에러가 뜨게 하지 않기 위해 page에 1L이라는 숫자를 우선적으로 넣어준 것이고, 내가 후에 page를 계산하는 메서드를 추가해줄 것이다.
		pager.makeRow();
		
		log.info("upload path: {}", upload);
		
		return qnaMapper.getList(pager);
	}
	
	//qnaVo에 있는 정보 가져와야 하니까 파라미터 줌
	public int add(QnaVO qnaVO, MultipartFile[] attaches) throws Exception{
		log.info("======== Before After BoardNum: {}", qnaVO.getBoardNum());
		int result = qnaMapper.add(qnaVO);
		log.info("======== Insert After BoardNum: {}", qnaVO.getBoardNum());
		//ref값에 boardNum을 넣기 위해 update를 침
		result = qnaMapper.refUpdate(qnaVO);
		
		
		if(result == 1) {
			//result가 1이면 예외 발생
			throw new Exception();
		}
		
		//파일을 하드디스크에 저장하고 DB에 정보를 insert하는 작업이 필요
		//경로를 보내주려함 filSave로 경로가 옴
		
		for(MultipartFile mf: attaches) {
			//파일을 올리지 않을 수도 있기 때문에
			if(mf == null || mf.isEmpty()) {
				continue;
			}
			
			//파일 저장, 저장된 파일명을 담아줌
			String fileName= fileManager.fileSave(upload+name, mf); //D:upload/qna 이렇게 경로 보내주게 됨 
//			log.info("저장된 파일명: {}", fileName);

			//저장된 파일명으로 QnaFile에 넣어줌
			QnaFileVO qnaFileVO = new QnaFileVO();
			
			//내가 저장하려는 파일의 정보를 qnaFile을 이용하여 set
			qnaFileVO.setFileName(fileName); //UDDI 사용한 파일 이름
			qnaFileVO.setOriName(mf.getOriginalFilename()); //원래 파일명
			qnaFileVO.setBoardNum(qnaVO.getBoardNum()); //어떤 게시글 번호에 올렸던 파일인지
			
			//qnaFile에 set 한 내용을 DB에 저장
			result = qnaMapper.addFile(qnaFileVO);
		}
		return result;
	}
	
	//detail
	public QnaVO getDetail(QnaVO qnaVO) throws Exception{
		return qnaMapper.getDetail(qnaVO);
	}
	
	//파일 다운
	public QnaFileVO getFileDetail(QnaFileVO qnaFileVO) throws Exception{
		return qnaMapper.getFileDetail(qnaFileVO);
	}
}
