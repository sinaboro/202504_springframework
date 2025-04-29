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
	
	@GetMapping("/boardList")
	public String boardList(Model model) {
		
		List<BoardVO> list = boardService.selectListBoard();
		
		model.addAttribute("boardList", list);
		
		return "boardList";
	}
	
	@GetMapping("/register")
	public String register() {
		return "boardRegister";
	}
	
	@PostMapping("/register")
	public String insertBoard(BoardVO vo) {
		boardService.insertBoard(vo);
		return "redirect:/board/boardList";		
	}
	
	@GetMapping("/view")
	public String viewBoard(@RequestParam int num , Model model) {
		             //DB에서 num(기본키) 62번 전체 데이타 가져와서 vo 저장(62는 DB존재하는 기본키)
		BoardVO vo = boardService.selectOneByNum(num);
		
		//vo 저장된 num(62)번 데이타를 board변수 담아서 boardView.jsp전달
		model.addAttribute("board", vo);  
		
		return "boardView";
	}
	
	@GetMapping("/check")
	public String checkGet(@RequestParam int num, Model model) {
		model.addAttribute("num", num);
		return "checkBoard";
	}
	
	
	
	@PostMapping("/check")
	public String CheckPost(@RequestParam int num, @RequestParam String pass) {
		log.info("check Post => " + num + " : "  + pass);
		return null;
	}
	
	
	
	
	@PostMapping("/check2")
	public String checkPost2(@RequestParam int num, @RequestParam String pass, 
			Model model) {
		
		boolean check = boardService.checkPassword(num, pass);
		
		if(check) {
			return "checkSuccess";
		}else {
			model.addAttribute("message", "패스워드가 틀립니다.");
			return "checkBoard";
		}		
	}
}
