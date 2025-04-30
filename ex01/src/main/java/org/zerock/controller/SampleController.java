package org.zerock.controller;

import java.text.SimpleDateFormat;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.TodoDTO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample")
@Log4j
public class SampleController {

	/*
	 * 반환타입 : void
	 *  / : /view/sample.jsp
	 *  /basic :  /view/sample/basic.jsp
	 *  /basicOnlyGet : /view/sample//basicOnlyGet.jsp
	 */
	@RequestMapping("/") //
	public void basic() {
		log.info("basic.............");
	}
	
	@RequestMapping(value="/basic", method= {
			RequestMethod.GET, RequestMethod.POST})
	public void basicGet(){
		log.info("basic get..........");
	}
	
	@GetMapping("/basicOnlyGet")
	public void basicGet2() {
		log.info("basic get only get........");
//		return "aaa";  //view/aaa.jsp
		
		//   view/sample/basic.jsp
	}
	
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		log.info(dto);
		return "ex01";
	}

	@GetMapping("/ex02")
	public String ex02(@RequestParam String name, 
			@RequestParam int age, Model model) {
		log.info(name);
		log.info(age);
		model.addAttribute("name", name);
		model.addAttribute("age", age);		
		return "ex02";
	}
	
//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		binder.registerCustomEditor(java.util.Date.class, 
//				new CustomDateEditor(dateFormat, false));
//	}
	
	@GetMapping("/ex03")
	public String ex03(TodoDTO todoDTO) {
		log.info(todoDTO);
		return "ex03";
	}
	
	/*
	@GetMapping("/ex04")
	public String ex04(SampleDTO dto, 
//			@ModelAttribute("page") int page, 
			@RequestParam("page") int page, 
			Model model) {
		log.info(dto);
		log.info(page);
		model.addAttribute("page", page);
		model.addAttribute("sample", dto);
		return "sample/ex04";
	}*/

	@GetMapping("/ex04")
	public String ex04(@ModelAttribute("sampleDTO") SampleDTO dto) {
		log.info(dto);
		return "sample/ex04";
	}
	
	@GetMapping("/rttr")
	public String rttr(SampleDTO dto , RedirectAttributes rttr) {
		rttr.addFlashAttribute("sampleDTO", dto);
		return "redirect:/sample/ex04";
	}
	
	@GetMapping("/ex06")
	// @ResponseBody => java객체를 json으로 변환
	public @ResponseBody SampleDTO ex06() {
		log.info("/ex06..........");
		SampleDTO dto = new SampleDTO();
		dto.setAge(10);
		dto.setName("홍길동");
		
		return dto;
	}

	@GetMapping("/ex06_1")
	//@RequestBody => json을 java 객체로 변환
	public String ex06_1(@RequestBody SampleDTO dto) {
		log.info("/ex06_1..........");
		log.info(dto);		
		return "ex06_1";
	}
}







































