package com.winter.app.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.util.Pager;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/qna/")
@Slf4j
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	@Value("${board.qna}")
	private String board;

	@ModelAttribute("board") //board이름이 qna가 된다. 프로퍼티스 파일의 내용이 바뀌면 얘도 바뀜 // board.qna=qna
	public String getboard() {
		return this.board;
	}
	
	//리스트 조회
	@GetMapping("list")
	public void getList(Pager pager, Model model) throws Exception {
		List<QnaVO> ar = qnaService.getList(pager);
		model.addAttribute("qnalist", ar);
		model.addAttribute("pager", pager);
		
		log.info("Pager:{} :{}", pager,pager.getKind());
	}
	
	//글 추가
	@GetMapping("add")
	public void add() throws Exception{
		
	}
	
	@PostMapping("add")
	//파일의 모든 정보들이 담겨져 있음(폴더, 확장자 등등)
	public String add(QnaVO qnaVO, MultipartFile [] attaches) throws Exception{
		qnaService.add(qnaVO, attaches);
		
		return "redirect:./list";
		
	}
	
	@GetMapping("detail")
	public void getDetail(QnaVO qnaVO, Model model) throws Exception{
		qnaVO = qnaService.getDetail(qnaVO);
		model.addAttribute("qnaDetail",qnaVO);
		
	}
	
	//파일 다운
	@GetMapping("fileDown")
	public String fileDown(QnaFileVO qnaFileVO, Model model) throws Exception{
		//하나의 fileVO에 모아서 상속받아서 사용하기 --프로젝트 때
		qnaFileVO= qnaService.getFileDetail(qnaFileVO);
		model.addAttribute("fileDown",qnaFileVO);
		//view의 이름은 void라서 url의 주소가 view의 이름이 됨, qna/fileDown이 주소가 됨, jsp를 찾으러 가는게 일반적임
		//그래서 리턴을 fileDownView라고 바꿈, 빈의 이름이 fileDownView라는 것을 찾아가서 실행하겠다는 것
		return "fileDownView";
	}
	
}