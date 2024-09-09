package com.winter.app.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.util.Pager;

import jakarta.validation.Valid;
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
	public void add(QnaVO qnaVO) throws Exception{
		
	}
	
	@PostMapping("add")
	//객체를 만들어서 넘어오는 파라미터 값들을 QnaVO에 넣어준다.
	//파일의 모든 정보들이 담겨져 있음(폴더, 확장자 등등)
	//검증 결과를 bindingresult에 저장한다. 선언 순서를 반드시 지켜준다, valid 다음에 result
	public String add(@Valid QnaVO qnaVO, BindingResult bindingResult, MultipartFile[] attaches) throws Exception{
		//에러가 발생하면 qna/add 페이지로 다시 이동하도록 설정
		if(bindingResult.hasErrors()) {
			log.error("writer가 비어있음");
			return "qna/add";
		}
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
		//return "qnaService"; 
		//이렇게 return값을 바꾸면 포워드 방식임. 
		//1. 이렇게 생긴 빈의 이름을 찾으러 간다. 있지만 실행 안된다. view로 사용하고 싶으면 spring이 얘가 view라는 것을 알아야 하는데, 
		//이는 rendermergedOutputModel이 담당한다. 이 메서드를 먼저 찾아가기 때문에 AbstractView를 상속받아야 한다. 
		//FileDownView 클래스는 abstractView를 상속받았기 때문에 spring이 view로 인식할 수 있는 것이다.
	}
	
}