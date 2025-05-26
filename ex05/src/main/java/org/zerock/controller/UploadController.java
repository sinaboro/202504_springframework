package org.zerock.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class UploadController {

	@GetMapping("/uploadForm")
	public void uploadForm() {
		log.info("upload from.........");
	}
	
	@PostMapping("/uploadForm")
	public void uploadFormPost(MultipartFile[] uploadFile, Model model){
	
		String uploadFolder = "c:\\upload";
		
		for(MultipartFile multipartFile  : uploadFile) {
			log.info("--------------------------");
			log.info("Upload File Name : " + multipartFile.getOriginalFilename());
			log.info("Upload File size : " + multipartFile.getSize());
			            
			                          //c:\\upload\\000001.jpg			
			File savedFile = new File(uploadFolder, multipartFile.getOriginalFilename());
			
			try {
				multipartFile.transferTo(savedFile);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		log.info("uploadAjax......");
	}
	
	@PostMapping("/uploadAjaxAction")
	public @ResponseBody String uploadAjaxAction(MultipartFile[] uploadFile, Model model){
	
		String uploadFolder = "c:\\upload";
		
		for(MultipartFile multipartFile  : uploadFile) {
			log.info("--------------------------");
			log.info("Upload File Name : " + multipartFile.getOriginalFilename());
			log.info("Upload File size : " + multipartFile.getSize());
			            
			                          //c:\\upload\\000001.jpg			
			File savedFile = new File(uploadFolder, multipartFile.getOriginalFilename());
			
			try {
				multipartFile.transferTo(savedFile);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return "success";
	}
}
