package com.javatechie.spring.ajax.api.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.jboss.jandex.Main;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javatechie.spring.ajax.api.config.EncDecutil;
import com.javatechie.spring.ajax.api.dto.AuthenticationResponse;
import com.javatechie.spring.ajax.api.dto.TSLogin;
import com.javatechie.spring.ajax.api.dto.TSOtpRequest;
import com.javatechie.spring.ajax.api.dto.TSRiskScore;
import com.javatechie.spring.ajax.api.entity.CustomerAccountsDetails;
import com.javatechie.spring.ajax.api.entity.Users;
import com.javatechie.spring.ajax.api.repository.CustAccountDtlRepository;
import com.javatechie.spring.ajax.api.repository.TSRiskScoreRepository;
import com.javatechie.spring.ajax.api.repository.UsersRepository;
import com.javatechie.spring.ajax.api.service.CustAccountDtlService;
import com.javatechie.spring.ajax.api.service.JwtUtil;
import com.javatechie.spring.ajax.api.service.TSRiskScoreService;
import com.javatechie.spring.ajax.api.service.UsersService;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.ClassPathResource;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.twilio.rest.api.v2010.account.Message;

@CrossOrigin
@RestController
@RequestMapping("/comm/api")
public class SmsController {
	
	@Autowired
    private UsersService userService;
	
	@Autowired
    private JwtUtil jwtUtil;
	
	@Autowired
	 private UsersRepository repository;
	
	@Autowired
	private EncDecutil encDecutil;
	
	@Autowired
	private TSRiskScoreService tSRiskScoreService;
	
	@Autowired
	private TSRiskScoreRepository riskRepository;
	
	@Autowired
	private  CustAccountDtlRepository custAccountDtlRepository;
	
	@Autowired
	private  CustAccountDtlService custAccountDtlService;
	
	@Autowired
	ResourceLoader resourceLoader;
//	@Autowired	
//	public SmsService smsService;
//	
//	
//	public SmsController(SmsService smsService) {
//		this.smsService = smsService;
//	}
//	
//
//	@RequestMapping("/sms")
//	public String sendSms (@RequestBody SmsRequest smsRequest) {
//		smsService.sendSms(smsRequest);
//		return "index";
//	}
	
	private Map <String, OTPService> otp_data = new HashMap();
	private static final String ACCOUNT_SID="ACd50c298ac9ae52f02e7a6878edbfe673"; 
	private static final String AUTH_TOKEN= "8b11d4990f3e26fa5767059f0a438ab4";
	private  final String TRAIL_NUMBER = "+13344909602";
	
	static {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
	}
	
	@RequestMapping(value="/token/{authToken}", method=RequestMethod.POST)
	public ResponseEntity<Object> verifyAuth (@PathVariable("authToken") String authToken) {
		
	     String userName = null;
	     String token = null;

	        if (authToken != null && authToken.startsWith("Bearer ")) {
	            token = authToken.substring(7);
	            userName = jwtUtil.extractUsername(token);
	        }
	        Users user = repository.findByphoneNumber(userName);
	        if(user!=null) {
	        	return new ResponseEntity<>(new AuthenticationResponse("Exist",HttpStatus.OK), HttpStatus.OK);
	        } else {
	        	return new ResponseEntity<>(new AuthenticationResponse("Does't Exist",HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
	        }
		
		
	}
	
	
	@RequestMapping(value="/mobile/{mobileNumber}/otp", method=RequestMethod.POST)
	public ResponseEntity<Object> sendOTP (@PathVariable("mobileNumber") String mobileNumber) {
		int requestId = 0;
		try {
			String accessToken = getAccessToken();
		if(!isValidMobile(mobileNumber))
			return new ResponseEntity<>("Mobile number is not a valid number", HttpStatus.NOT_FOUND);
		
		mobileNumber = "91"+mobileNumber;
		Users existingUser = repository.findByphoneNumber(mobileNumber);
		if(existingUser!=null) 
			return new ResponseEntity<>("User Already exist", HttpStatus.CONFLICT);
		
		System.out.println("Risk API requestId before : "+accessToken);
		requestId = intiateTSOtp(accessToken, mobileNumber);
		System.out.println("Risk API requestId after: "+requestId);
		OTPService otpService = new OTPService();
		otpService.setMobileNumber(mobileNumber);
		if(requestId==0) {
			otpService.setRequest_id(requestId+"");
			otpService.setOtp(getRandomNumberString());
			otpService.setExpiryTime(System.currentTimeMillis()+180000);
	
		Message.creator(new PhoneNumber("+"+mobileNumber),new PhoneNumber(TRAIL_NUMBER), "Your Premoney Registration OTP is : "+otpService.getOtp()).create();
		} else {
			otpService.setAccessToken(accessToken);
			otpService.setRequest_id(requestId+"");
			otpService.setExpiryTime(System.currentTimeMillis()+180000);
		}
		otp_data.put(mobileNumber, otpService);
		return new ResponseEntity<>("OTP is send successfully", HttpStatus.OK);
		} catch (Exception e) {
		return new ResponseEntity<>("Mobile number is not a valid number", HttpStatus.NOT_FOUND);
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/mobile/{mobileNumber}/otp/{otpNumber}/verify", method=RequestMethod.POST,produces = "application/json")
	public ResponseEntity<Object> verifyOTP ( @PathVariable("mobileNumber") String rawMobileNumber, @PathVariable("otpNumber") String otpNumber) throws IOException {
		
		String authToken = "";
		int riskScore = 0;
		Users user = null;
		TSRiskScore tsRiskSocre = null;
		String mobileNumber = "91"+rawMobileNumber;
		final HttpHeaders httpHeaders= new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		if(otpNumber==null || otpNumber.trim().length()<=0) {
			return new ResponseEntity<>("Please provide OTP", HttpStatus.BAD_REQUEST);
		}
		
		if(otp_data.containsKey(mobileNumber)) {
			
			OTPService otpService = otp_data.get(mobileNumber);
			if(otpService!=null) {
				if(otpService.getExpiryTime()>=System.currentTimeMillis()) {
					try {
						tsRiskSocre = getRiskScore(otpService.getRequest_id(),rawMobileNumber,otpService.getAccessToken(), otpNumber);
					} catch (Exception e) {
						e.printStackTrace();
					}
					if((otpService.getRequest_id().equals("0") && otpNumber.equals(otpService.getOtp())) 
							|| (!otpService.getRequest_id().equals("0")  && (tsRiskSocre.getVerdict().equals("success"))) ) {
						user = new Users();
						user.setPhoneNumber(mobileNumber);
						user.setStatus("Active");
						user.setDisable("0");
						user.setVerified(0);
						user.setCreated_date(new Date());
						user.setOtp(otpNumber);
						authToken = jwtUtil.generateToken(mobileNumber);
						user.setAuth_token(authToken);
						userService.saveUser(user);
						if(tsRiskSocre.getVerdict().equals("success")) 
						saveTSRiskScoreData(tsRiskSocre,mobileNumber);
						return new ResponseEntity<>(new AuthenticationResponse(authToken,HttpStatus.OK), HttpStatus.OK);
					} return new ResponseEntity<>("Invalid OTP", HttpStatus.GATEWAY_TIMEOUT);
				} return new ResponseEntity<>("OTP is expired", HttpStatus.BAD_REQUEST);
			} return new ResponseEntity<>("Something went wrong..!!", HttpStatus.BAD_REQUEST);
		}return new ResponseEntity<>("Mobile Number Not found", HttpStatus.NOT_FOUND);
		
	}

	
	private void saveTSRiskScoreData(TSRiskScore tsRiskSocre, String mobileNumber) throws IOException {
		
		String score = "";
		com.javatechie.spring.ajax.api.entity.TSRiskScore tSRiskScoreEntity = new com.javatechie.spring.ajax.api.entity.TSRiskScore();
		com.javatechie.spring.ajax.api.entity.TSRiskScoreData tSRiskScoreDataEntity = new com.javatechie.spring.ajax.api.entity.TSRiskScoreData();
		com.javatechie.spring.ajax.api.entity.TSConsentInfo TSConsentInfoEntity = new com.javatechie.spring.ajax.api.entity.TSConsentInfo();
		com.javatechie.spring.ajax.api.entity.TSOther TSOtherEntity = new com.javatechie.spring.ajax.api.entity.TSOther();
		com.javatechie.spring.ajax.api.entity.TSScoreInfo TSScoreInfoEntity = new com.javatechie.spring.ajax.api.entity.TSScoreInfo();
		
		BeanUtils.copyProperties(tsRiskSocre.getData().getConsentInfo(), TSConsentInfoEntity);
		BeanUtils.copyProperties(tsRiskSocre.getData().getOther(), TSOtherEntity);
		BeanUtils.copyProperties(tsRiskSocre.getData().getScoreInfo(), TSScoreInfoEntity);
		BeanUtils.copyProperties(tsRiskSocre.getData(), tSRiskScoreDataEntity);
		BeanUtils.copyProperties(tsRiskSocre, tSRiskScoreEntity);
		tSRiskScoreEntity.setData(tSRiskScoreDataEntity);
		tSRiskScoreEntity.getData().setConsentInfo(TSConsentInfoEntity);
		tSRiskScoreEntity.getData().setOther(TSOtherEntity);
		tSRiskScoreEntity.getData().setScoreInfo(TSScoreInfoEntity);
		Users user = repository.findByphoneNumber(mobileNumber);
		tSRiskScoreEntity.setUserId(user.getId());
		InputStream inputStream = Main.class.getResourceAsStream("/private.key.rsa");
		File file = new File("temp"+new Date());
				try(OutputStream outputStream = new FileOutputStream(file)){
				    IOUtils.copy(inputStream, outputStream);
				} catch (FileNotFoundException e) {
				    // handle exception here
				} catch (IOException e) {
				    // handle exception here
				}
		try {
			 score = EncDecutil.EncryptDecrypt(tsRiskSocre.getData().getScoreInfo().getScore(), file, "DECRYPT");
			 file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		tSRiskScoreEntity.getData().getScoreInfo().setScore(score);
		com.javatechie.spring.ajax.api.entity.TSRiskScore updateTSRiskScoreEntity = tSRiskScoreService.getTSRiskScoreUserById(user.getId());
		if(updateTSRiskScoreEntity!=null)
			tSRiskScoreEntity.setId(updateTSRiskScoreEntity.getId());
		riskRepository.save(tSRiskScoreEntity);
		
	}


	public String getRandomNumberString() {
		
	    Random rnd = new Random();
	    int number = rnd.nextInt(999999);
	    return String.format("%06d", number);
	}
	
	public  boolean isValidMobile(String s) { 
		
	    // 1) Begins with 0 or 91 
	    // 2) Then contains 7 or 8 or 9. 
	    // 3) Then contains 9 digits 
	    Pattern p = Pattern.compile("(0/91)?[6-9][0-9]{9}"); 
	    java.util.regex.Matcher m = p.matcher(s); 
	    return (m.find() && m.group().equals(s)); 
	}
	
	private TSRiskScore getRiskScore (String requestId, String mobileNumber, String accessToken, String otp) throws Exception {
		
		
		String riskScoreUrl = "https://creditinsight-staging-in.trustingsocial.com/score/v2/credit_score_requests/create/with_otp";
		String mapToJson="";
		TSRiskScore tsRiskScore = null;
//		Resource resource = new ClassPathResource("vil-staging.public.rsa.pub");
//		Resource resource1 = new ClassPathResource("airtel-staing-public.key.rsa.pub");
//		File file = resource.getFile();
//		File file1 = resource1.getFile();
		InputStream inputStream = Main.class.getResourceAsStream("/vil-staging.public.rsa.pub");
		File file = new File("temp"+new Date());
				try(OutputStream outputStream = new FileOutputStream(file)){
				    IOUtils.copy(inputStream, outputStream);
				} catch (FileNotFoundException e) {
				    // handle exception here
				} catch (IOException e) {
				    // handle exception here
				}
				InputStream inputStream1 = Main.class.getResourceAsStream("/airtel-staing-public.key.rsa.pub");
				File file1 = new File("temp"+new Date());
						try(OutputStream outputStream = new FileOutputStream(file)){
						    IOUtils.copy(inputStream1, outputStream);
						} catch (FileNotFoundException e) {
						    // handle exception here
						} catch (IOException e) {
						    // handle exception here
						}
		String encryptedmsisdnVdi = EncDecutil.EncryptDecrypt(mobileNumber, file, "ENCRYPT");
		String encryptedmsisdnAir = EncDecutil.EncryptDecrypt(mobileNumber, file1, "ENCRYPT");
		file.delete();
		file1.delete();
		Map<String, Object> mapRequest = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String,Object>();
		Map<String, Object> data = new HashMap<String,Object>();
		param.put("client_code", "plutofintech_client");
		data.put("vil", encryptedmsisdnVdi);
		data.put("airtel", encryptedmsisdnAir);
		param.put("requested_msisdns", data);
		param.put("delegated_client_alias", "pft_pl");
		param.put("otp", otp);
		param.put("request_id", requestId);
		ObjectMapper objectMapper = new ObjectMapper();
		mapToJson = objectMapper.writeValueAsString(param);
		 OkHttpClient client = new OkHttpClient().newBuilder().build();
		 okhttp3.MediaType mediaType = okhttp3.MediaType.parse("application/json");
				okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, mapToJson);
				Request request = new Request.Builder()
				  .url(riskScoreUrl)
				  .method("POST", body)
				  .addHeader("Content-Type", "application/json")
				  .addHeader("Authorization", "Bearer " +accessToken)
				  .build();
				try {
					Response response = client.newCall(request).execute();
					String responseBody = response.body().string();
					tsRiskScore = objectMapper.readValue(responseBody, TSRiskScore.class);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
		
		return tsRiskScore;
		
	}
	
	private String getAccessToken() throws IOException {

		String accessToken="";
		String mapToJson="";
		String loginUrl = "https://creditinsight-staging-in.trustingsocial.com/auth/users/login";
		Map<String, Object> mapRequest = new HashMap<String, Object>();
		Map<String, Object> data = new HashMap<String,Object>();
		data.put("user_name", "pft_user");
		data.put("password", "Test@12#$");
		ObjectMapper objectMapper = new ObjectMapper();
		 mapToJson = objectMapper.writeValueAsString(data);
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		okhttp3.MediaType mediaType = okhttp3.MediaType.parse("application/json");
		okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, mapToJson);
				Request request = new Request.Builder()
				  .url(loginUrl)
				  .method("POST", body)
				  .addHeader("Content-Type", "application/json")
				  .build();
				try {
					Response response = client.newCall(request).execute();
					String responseBody = response.body().string();
					TSLogin tsLogin = objectMapper.readValue(responseBody, TSLogin.class);
					accessToken = tsLogin.getData().getAccessToken();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return accessToken;
		
	}
	
	private int intiateTSOtp(String accessToken, String mobileNumber) throws Exception {

		String genrateOtpUrl = "https://creditinsight-staging-in.trustingsocial.com/score/v2/otp_sms_requests/create";
		String mapToJson="";
		int requestId=0;
		
		System.out.println("Risk API requestId inside before resource");
		InputStream inputStream = Main.class.getResourceAsStream("/vil-staging.public.rsa.pub");
		File file = new File("temp"+new Date());
				try(OutputStream outputStream = new FileOutputStream(file)){
				    IOUtils.copy(inputStream, outputStream);
				} catch (FileNotFoundException e) {
				    // handle exception here
				} catch (IOException e) {
				    // handle exception here
				}
				InputStream inputStream1 = Main.class.getResourceAsStream("/airtel-staing-public.key.rsa.pub");
				File file1 = new File("temp"+new Date());
						try(OutputStream outputStream = new FileOutputStream(file)){
						    IOUtils.copy(inputStream1, outputStream);
						} catch (FileNotFoundException e) {
						    // handle exception here
						} catch (IOException e) {
						    // handle exception here
						}
		String encryptedmsisdnVdi = EncDecutil.EncryptDecrypt(mobileNumber, file, "ENCRYPT");
		String encryptedmsisdnAir = EncDecutil.EncryptDecrypt(mobileNumber, file1, "ENCRYPT");
		file.delete();
		file1.delete();
		System.out.println("Risk API requestId inside after encryption");
		Map<String, Object> mapRequest = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String,Object>();
		Map<String, Object> data = new HashMap<String,Object>();
		param.put("client_code", "plutofintech_client");
		data.put("vil", encryptedmsisdnVdi);
		data.put("airtel", encryptedmsisdnAir);
		param.put("requested_msisdns", data);
		param.put("delegated_client_alias", "pft_pl");
		param.put("external_source_id", "test");
		ObjectMapper objectMapper = new ObjectMapper();
		mapToJson = objectMapper.writeValueAsString(param);
		 OkHttpClient client = new OkHttpClient().newBuilder().build();
		 okhttp3.MediaType mediaType = okhttp3.MediaType.parse("application/json");
				okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, mapToJson);
				Request request = new Request.Builder()
				  .url(genrateOtpUrl)
				  .method("POST", body)
				  .addHeader("Content-Type", "application/json")
				  .addHeader("Authorization", "Bearer " +accessToken)
				  .build();
				try {
					Response response = client.newCall(request).execute();
					String responseBody = response.body().string();
					TSOtpRequest tSOtpRequest = objectMapper.readValue(responseBody, TSOtpRequest.class);
					if(tSOtpRequest.getVerdict().equals("success"))
					requestId = tSOtpRequest.getData().getRequestId();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return requestId;
		
	}

	public void sendConsentNotification(CustomerAccountsDetails accountDtl) {
		
		String str = accountDtl.getId()+"";
		String loanNumber = str.length() > 2 ? str.substring(str.length() - 2) : str;
		Users user = userService.getUserById(accountDtl.getUserId());
		String consentUrl = "http://premoneydev-env.eba-z7stmxeg.us-east-2.elasticbeanstalk.com/account/customerLetter/"+jwtUtil.generateToken(user.getPhoneNumber());
		Message.creator(new PhoneNumber("+"+user.getPhoneNumber()),new PhoneNumber(TRAIL_NUMBER), "Dear Premoney Customer, your loan XXXXX"+loanNumber
				+" is approved. Kindly provide consent for loan disbursal. Click on "+consentUrl).create();
	}

	public void sendPaymentReciptNotification(CustomerAccountsDetails accountDtl) {
		
		String str = accountDtl.getId()+"";
		String loanNumber = str.length() > 2 ? str.substring(str.length() - 2) : str;
		Users user = userService.getUserById(accountDtl.getUserId());
		String paymentReciptUrl = "http://premoneydev-env.eba-z7stmxeg.us-east-2.elasticbeanstalk.com/account/customerLetter/"+jwtUtil.generateToken(user.getPhoneNumber());
		Message.creator(new PhoneNumber("+"+user.getPhoneNumber()),new PhoneNumber(TRAIL_NUMBER), "Dear Premoney Customer, your loan XXXXX"+loanNumber
				+" is disbursed. Click on "+paymentReciptUrl+" view the recipt ").create();
	}


	public void sendConsentOtp(CustomerAccountsDetails accountDtl) {
		
		String str = accountDtl.getId()+"";
		String loanNumber = str.length() > 2 ? str.substring(str.length() - 2) : str;
		Users user = userService.getUserById(accountDtl.getUserId());
		OTPService otpService = new OTPService();
		otpService.setMobileNumber(user.getPhoneNumber());
		otpService.setRequest_id("0");
		otpService.setOtp(getRandomNumberString());
		otpService.setExpiryTime(System.currentTimeMillis()+1800000);
		otp_data.put(user.getPhoneNumber(), otpService);
		Message.creator(new PhoneNumber("+"+user.getPhoneNumber()),new PhoneNumber(TRAIL_NUMBER), "OTP consent for loan XXXXX"+ loanNumber +" disbursal is : "+otpService.getOtp()+" Please "
				+ "do not share this with anyone.").create();
	}
	
	@PostMapping("/otpConsent/{otp}/{jwt}/verify")
	public ResponseEntity<Object> rejectLoan(@PathVariable(value="otp") String otp,@PathVariable(value="jwt") String jwt) {
		
		CustomerAccountsDetails customerAccountsDetails = null;
		String userName=null;
		userName = jwtUtil.extractUsername(jwt);
		Users user = repository.findByphoneNumber(userName);
		if(otp==null || otp.trim().length()<=0) {
			return new ResponseEntity<>("Please provide OTP", HttpStatus.BAD_REQUEST);
		}
		if(otp_data.containsKey(user.getPhoneNumber())) {
			OTPService otpService = otp_data.get(user.getPhoneNumber());
			if(otpService!=null) {
				if(otpService.getExpiryTime()>=System.currentTimeMillis()) {
					if(otp.equals(otpService.getOtp())) {
						customerAccountsDetails = custAccountDtlService.getCustomerAccountByUserId(user.getId());
						customerAccountsDetails.setLetterConsentOtp(otp);
						customerAccountsDetails.setLetterConsentDate(new Date());
						custAccountDtlRepository.save(customerAccountsDetails);
						return new ResponseEntity<>(new AuthenticationResponse("Verify",HttpStatus.OK), HttpStatus.OK);
					} return new ResponseEntity<>("Invalid OTP", HttpStatus.GATEWAY_TIMEOUT);
				} return new ResponseEntity<>("OTP is expired", HttpStatus.BAD_REQUEST);
			} return new ResponseEntity<>("Something went wrong..!!", HttpStatus.BAD_REQUEST);
		}return new ResponseEntity<>("Mobile Number Not found", HttpStatus.NOT_FOUND);
	}
	
}
