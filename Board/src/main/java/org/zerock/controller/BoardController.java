package org.zerock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.dto.BoardVO;
import org.zerock.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

/*
/board/boardList -> 전체데이타반환
/board/view -> 상세 페이지 
 */

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Log4j
public class BoardController {
	
	private final BoardService boardService;
	
	//localhost:8008/board/boardList -> 전체 리스트 목록
	//localhost:8008/board/ -> 전체 리스트 목록
	@GetMapping({ "/",  "/boardList" })
	public String boardList(Model model) {
		
		List<BoardVO> list = boardService.selectListBoard();
		
		model.addAttribute("boardList", list);
		
		return "boardList";
	}
	
	//localhost:8008/board/register(get) -> 멤버 등록 화면 출력
	@GetMapping("/register")
	public String register() {
		return "boardRegister";
	}
	
	//localhost:8008/board/register(post) -> 등록화면에서 입력한 데이타를 기반으로 DB등록 
	@PostMapping("/register")
	public String insertBoard(BoardVO vo) {
		boardService.insertBoard(vo);
		return "redirect:/board/boardList";		
	}
	
	//localhost:8008/board/view -> num(기본키) 해당하는 상세 페이지
	@GetMapping("/view")
	public String viewBoard(@RequestParam int num , Model model) {
		             //DB에서 num(기본키) 62번 전체 데이타 가져와서 vo 저장(62는 DB존재하는 기본키)
		boardService.updateReadCount(num); //조회수 증가
		BoardVO vo = boardService.selectOneByNum(num);
		
		//vo 저장된 num(62)번 데이타를 board변수 담아서 boardView.jsp전달
		model.addAttribute("board", vo);  
		
		return "boardView";
	}
	
	//localhost:8008/board/check(get) -> 삭제, 수정시 입력화면 출력 
	@GetMapping("/check")
	public String checkGet(@RequestParam int num, Model model) {
		model.addAttribute("num", num);
		return "checkBoard";
	}
	
	//localhost:8008/board/check(post) -> 삭제, 수정시 DB조회해서 비밀번호 체크
	@PostMapping("/check")
	public String CheckPost(@RequestParam int num, @RequestParam String pass,
			Model model) {
		//서비스 호출해서 true(비밀번호 맞음), false(비밀번호 틀림) 반환받는다.
		boolean check = boardService.checkPassword(num, pass);
		
		if(check) {
			//비밀번호 맞음
			model.addAttribute("num", num);
			return "checkSuccess";
		}else {
			//비밀번호 틀렸다.
			model.addAttribute("message", "비밀번호가 틀립니다.");
			model.addAttribute("num", num);
			return "checkBoard";
		}
	}
	
	//localhost:8008/board/delete => num해당 하는 데이타(DB) 삭제
	@GetMapping("/delete")
	public String deleteGet(@RequestParam int num) {
		boardService.deleteBoard(num);
		return "redirect:/board/boardList";
	}
	
	//localhost:8008/board/update(get) -> 수정화면 출력
	@GetMapping("/update")
	public String updateGet(@RequestParam int num, Model model) {
		BoardVO vo = boardService.selectOneByNum(num);
		model.addAttribute("board", vo);
		return "boardUpdate";
	}
	
	//localhost:8008/board/update(post) -> 수정화면서 입력한 내용을 DB 반영
	@PostMapping("/update")
	public String updatePost(BoardVO vo) {
		boardService.updateBoard(vo);
		return "redirect:/board/view?num="+ vo.getNum();
	}
}












