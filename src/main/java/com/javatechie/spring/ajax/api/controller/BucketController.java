package com.javatechie.spring.ajax.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javatechie.spring.ajax.api.service.S3Services;

@RestController
@RequestMapping("/storage")
public class BucketController {

	@Autowired
    private S3Services amazonClient;
	
	

//	@PostMapping("/uploadFile")
//    public String uploadFile(@RequestParam(value = "uploadfile") MultipartFile file) {
//        return this.amazonClient.uploadFile(file);
//    }
	
    @PostMapping("/rest/uploadMultiFiles")
    public ResponseEntity<?> multiUploadFileModel(@ModelAttribute UploadForm form) {
    	String result="";  
    	int count = 0;
    	for (MultipartFile file : form.getFiles()) {
    		if (file.isEmpty()) {
    			count++;
                continue;
            }
    		result = this.amazonClient.uploadFile(file,count,form.getFiles().length);
    		count++;
    	}
    	
    	return new ResponseEntity<String>("Uploaded to: <br/>" + result, HttpStatus.OK);
    }
    

    @DeleteMapping("/deleteFile")
    public String deleteFile(@RequestPart(value = "url") String fileUrl) {
        return this.amazonClient.deleteFileFromS3Bucket(fileUrl);
    }
}
    
    class UploadForm {
    	private MultipartFile[] files;
    	private String description;
    	
    	 public String getDescription() {
    	        return description;
    	    }
    	 
    	    public void setDescription(String description) {
    	        this.description = description;
    	    }
    	 
    	
    	public MultipartFile[] getFiles() {
            return files;
        }
     
        public void setFiles(MultipartFile[] files) {
            this.files = files;
        }
    }
