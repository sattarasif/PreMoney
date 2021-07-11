package com.javatechie.spring.ajax.api.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.javatechie.spring.ajax.api.entity.AdditionalFields;
import com.javatechie.spring.ajax.api.entity.CommonDBFields;
import com.javatechie.spring.ajax.api.entity.KYCDetails;
import com.javatechie.spring.ajax.api.entity.Users;
import com.javatechie.spring.ajax.api.repository.KYCDtlRepository;
import com.javatechie.spring.ajax.api.repository.UsersRepository;

@Service
public class S3Services{
  
  
	@Autowired
	private AmazonS3 s3client;
	@Autowired
	 private UsersRepository repository;
	@Autowired
    private KYCDtlService kycService;
	
	@Autowired
    private KYCDtlRepository kYCDtlRepository;

    @Value("${aws.endpointUrl}")
    private String endpointUrl;
    @Value("${aws.s3.bucket}")
    private String bucketName;
    @Value("${aws.access_key_id}")
    private String accessKey;
    @Value("${aws.secret_access_key}")
    private String secretKey;
    @Value("${aws.s3.region}")
    private String region;

    @Bean
    public AmazonS3 getAmazonS3Cient() {
        final BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(accessKey, secretKey);
        // Get AmazonS3 client and return the s3Client object.
        return AmazonS3ClientBuilder
                .standard()
                .withRegion(Regions.fromName(region))
                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
                .build();
    }
 
  
  private File convertMultiPartToFile(MultipartFile file) throws IOException {
	    File convFile = new File(file.getOriginalFilename());
	    FileOutputStream fos = new FileOutputStream(convFile);
	    fos.write(file.getBytes());
	    fos.close();
	    return convFile;
	}
  
  private String generateFileName(MultipartFile multiPart,int count, int size, KYCDetails kycDtl, String fileUrl) {
	  String name = new Date().getTime() + "_" + "";
	  String nameWithUrl = fileUrl+new Date().getTime() + "_" + "";
	  if(size==4) {
		  if(count==0) {
			  nameWithUrl = nameWithUrl+"CustomerImg";
			  name = name+"CustomerImg";
			  kycDtl.setCustomerImg(nameWithUrl);
		  }
		  if(count==1) {
			  nameWithUrl = nameWithUrl+"AdhaarFront";
			  name = name+"AdhaarFront";
			  kycDtl.setAdhaarFrontImg(nameWithUrl);
		  }
		  if(count==2) {
			  nameWithUrl = nameWithUrl+"AdhaarBack";
			  name = name+"AdhaarBack";
			  kycDtl.setAdhaarBackImg(nameWithUrl);
		  }
		  if(count==3) {
			  nameWithUrl = nameWithUrl+"PAN";
			  name = name+"PAN";
			  kycDtl.setPanCardImg(nameWithUrl);
		  }
	  } else if(size==2){
		  if(count==0) {
			  nameWithUrl = nameWithUrl+"CancelCheck";
			  name = name+"CancelCheck";
			  kycDtl.setCancelCheckImg(nameWithUrl);
		  }
		  if(count==1) {
			  nameWithUrl = nameWithUrl+"Passbook";
			  name = name+"Passbook";
			  kycDtl.setPassBookImg(nameWithUrl);
		  }
	  } else if(size==1){
		  nameWithUrl = nameWithUrl+"paymentSuccessDoc";
		  name = name+"paymentSuccessDoc";
		  AdditionalFields additionalField = new AdditionalFields();
		  additionalField.setAddStrField1(nameWithUrl);
		  kycDtl.setAdditionalFields(additionalField);;
	  }
	    //return new Date().getTime() + "_" + multiPart.getOriginalFilename().replace(" ", "_");
	  return name;
	}
 
  private void uploadFileTos3bucket(String fileName, File file) {
	  
	  s3client.putObject(new PutObjectRequest(bucketName, fileName, file).withCannedAcl(CannedAccessControlList.PublicRead));
	}
  
  public String uploadFile(MultipartFile multipartFile, int count, int size) {

	  
	  org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	  
	  Users user = repository.findByphoneNumber(principal.getUsername());
	  KYCDetails updateKycDtl = kycService.getKycDtlByUserId(user.getId());
	  if(updateKycDtl==null)
	  updateKycDtl = new KYCDetails();
	   getKYCDtls(updateKycDtl);
	    String fileUrl = "";
	    try {
	    	
	        File file = convertMultiPartToFile(multipartFile);
	        fileUrl = endpointUrl + "/" + bucketName +"/"+user.getId()+ "/";
	        String fileName = generateFileName(multipartFile,count,size, updateKycDtl, fileUrl);
	        uploadFileTos3bucket(user.getId()+"/"+fileName, file);
	        updateKycDtl.setUserId(user.getId());
	    //	kycDtl.setAdhaarFrontImg(fileName);
	    //	AdditionalFields additionalField = new AdditionalFields();
	    //	additionalField.setAddStrField1(fileUrl);
	    //	kycDtl.setAdditionalFields(additionalField);
	    	kYCDtlRepository.save(updateKycDtl);
	        file.delete();
	    } catch (Exception e) {
	       e.printStackTrace();
	       fileUrl = "File Not Uploaded ";
	    }
	    return fileUrl;
	}
  
  public String deleteFileFromS3Bucket(String fileUrl) {
	    String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
	    s3client.deleteObject(new DeleteObjectRequest(bucketName + "/", fileName));
	    return "Successfully deleted";
	}
  
  private void getKYCDtls (KYCDetails kycDtl) {
	  
	  
	  CommonDBFields commonField = new CommonDBFields();
//		AdditionalFields additionalField = new AdditionalFields();
//		additionalField.setAddStrField1(endpointUrl);
//		kycDtl.setAdditionalFields(additionalField);
		commonField.setApprovalStatus("0");
		commonField.setStatus("A");
		kycDtl.setCommonDBFields(commonField);
	  
  }
}
