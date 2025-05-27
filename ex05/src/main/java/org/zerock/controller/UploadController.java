package org.zerock.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.AttchFileDTO;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

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
	
	
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = new Date();
		
		String str = sdf.format(date);  //2025-05-26
		return str.replace("-", File.separator);  // 윈도우->  2025-05-26 -> 2025\05\26
	}
	
	private boolean checkImageType(File file) {
		
		try {
			String contentType = Files.probeContentType(file.toPath());
			
			return contentType.startsWith("image");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		log.info("uploadAjax......");
	}
	
	
	@PostMapping(value =  "/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public  ResponseEntity<List<AttchFileDTO>> uploadAjaxAction(MultipartFile[] uploadFile, Model model){
	
		List<AttchFileDTO> list = new ArrayList<AttchFileDTO>();
		
		String uploadFolder = "c:\\upload";
		String uploadFolderPath = getFolder();  //2025\\05\\27
		
        //c:\\upload\\2025\\05\\27
		File uploadPath = new File(uploadFolder, uploadFolderPath);
		
		if(uploadPath.exists() == false) {  //폴더 생성
			log.info("-----------mkdir-----------");
			uploadPath.mkdirs();  //mkdir() -> 하위폴더 1개만 만들때, mkdirs() -> 복수개 하위폴더 생성시
		}
		
		for(MultipartFile multipartFile  : uploadFile) {
//			log.info("--------------------------");
//			log.info("Upload File Name : " + multipartFile.getOriginalFilename());
//			log.info("Upload File size : " + multipartFile.getSize());
			
			AttchFileDTO attchFileDTO = new AttchFileDTO();
			
			String uploadFileName = multipartFile.getOriginalFilename();
			
			UUID uuid = UUID.randomUUID();
			
			uploadFileName = uuid.toString() + "_" + uploadFileName;			
			
			attchFileDTO.setUuid(uuid.toString());
			attchFileDTO.setFileName(uploadFileName);  //원본파일명
			attchFileDTO.setUploadPath(uploadFolderPath);
			
			try {
				//c:\\upload\\2025\\05\\26\\001.jpg
				File savedFile = new File(uploadPath, uploadFileName );
				multipartFile.transferTo(savedFile);
				
				//셈네일 파일 생성 , c:\\upload\\2025\\05\\26\\s_001.jpg
				if(checkImageType(savedFile)) {
					
					attchFileDTO.setImage(true);
					
					FileOutputStream thumbnail = new FileOutputStream(
							new File(uploadPath , "s_" + uploadFileName)
					);
					
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100 );
					
					thumbnail.close();
				}
				
				list.add(attchFileDTO);
				
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} //end for
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@PostMapping("/uploadAjaxAction_old")
	public @ResponseBody String uploadAjaxAction_old(MultipartFile[] uploadFile, Model model){
	
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
