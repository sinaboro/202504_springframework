package org.zerock.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.domain.BoardVO;
import org.zerock.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Log4j
public class BoardController {
	private final BoardService service;
	
	@GetMapping("/list")
	public void list(Model model) {
		log.info("list..........");
		List<BoardVO> list = service.getList();
		model.addAttribute("list", list);
		
	}
	
	@PostMapping("/register")
	public void register(BoardVO board) {
		log.info("register.......");
	}
	
	@GetMapping("/get")
	public void get(Long bno) {
		log.info("get..........");
	}
	
	@PostMapping("/remove")
	public void remove(Long bno) {
		log.info("remove......");
	}
	
	@PostMapping("/modify")
	public void modify(BoardVO board) {
		log.info("modify.........");
	}
}
