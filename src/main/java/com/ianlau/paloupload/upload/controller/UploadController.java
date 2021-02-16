package com.ianlau.paloupload.upload.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ianlau.paloupload.upload.service.UploadService;

@Controller
@CrossOrigin("http://localhost:4200")
public class UploadController {
	@Autowired
	private UploadService uploadService;
	
	@PostMapping("/upload")
	  public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
	    String message = "";
	    String[] fileFrags = file.getOriginalFilename().split("\\.");
	    String extension = fileFrags[fileFrags.length-1];
	    
	    if(!"CSV".equalsIgnoreCase(extension)) {
	    	return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
	    }

	    
	    try {
	      message = uploadService.processOrder(file);

	      //message = "Uploaded the file successfully: " + file.getOriginalFilename();
	      return ResponseEntity.status(HttpStatus.OK).body(message);
	    } catch (Exception e) {
	      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
	      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
	    }
	  }
}
