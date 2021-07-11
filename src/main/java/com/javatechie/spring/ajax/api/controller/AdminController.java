package com.javatechie.spring.ajax.api.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javatechie.spring.ajax.api.dto.IdfyProfileResponse;
import com.javatechie.spring.ajax.api.dto.KycExtraction;
import com.javatechie.spring.ajax.api.dto.KycLinkData;
import com.javatechie.spring.ajax.api.dto.LoginDto;
import com.javatechie.spring.ajax.api.dto.PanExtraction;
import com.javatechie.spring.ajax.api.dto.UpdateCustomerInfo;
import com.javatechie.spring.ajax.api.entity.AddressDetails;
import com.javatechie.spring.ajax.api.entity.CaptureKyc;
import com.javatechie.spring.ajax.api.entity.CommonDBFields;
import com.javatechie.spring.ajax.api.entity.CustomerAccountsDetails;
import com.javatechie.spring.ajax.api.entity.CustomerBankDetails;
import com.javatechie.spring.ajax.api.entity.CustomerDetails;
import com.javatechie.spring.ajax.api.entity.CustomerEmployerDetails;
import com.javatechie.spring.ajax.api.entity.CustomerReferenceDetails;
import com.javatechie.spring.ajax.api.entity.KYCDetails;
import com.javatechie.spring.ajax.api.entity.TSRiskScore;
import com.javatechie.spring.ajax.api.entity.Users;
import com.javatechie.spring.ajax.api.repository.CustAccountDtlRepository;
import com.javatechie.spring.ajax.api.repository.UsersRepository;
import com.javatechie.spring.ajax.api.service.AddressDtlService;
import com.javatechie.spring.ajax.api.service.AdminService;
import com.javatechie.spring.ajax.api.service.CaptureKycService;
import com.javatechie.spring.ajax.api.service.CustAccountDtlService;
import com.javatechie.spring.ajax.api.service.CustBankDtlService;
import com.javatechie.spring.ajax.api.service.CustEmployerDtlService;
import com.javatechie.spring.ajax.api.service.CustRefDtlService;
import com.javatechie.spring.ajax.api.service.CustomerDtlService;
import com.javatechie.spring.ajax.api.service.JwtUtil;
import com.javatechie.spring.ajax.api.service.KYCDtlService;
import com.javatechie.spring.ajax.api.service.PanExtractionService;
import com.javatechie.spring.ajax.api.service.TSRiskScoreService;
import com.javatechie.spring.ajax.api.service.UsersService;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.http.RealResponseBody;


@Controller
@RequestMapping("/account")
public class AdminController {
	
	@Autowired
	private UsersService service;
	
	@Autowired
	private CustomerDtlService customerDtlService;
	
	@Autowired
	private AddressDtlService addressDtlService;
	
	@Autowired
	private  CustAccountDtlService custAccountDtlService;
	
	@Autowired
	private  CustBankDtlService custBankDtlService;
	
	@Autowired
	private  CustEmployerDtlService custEmployerDtlService;
	
	@Autowired
	private  CustRefDtlService custRefDtlService;
	
	@Autowired
	private  PanExtractionService panExtractionService;
	
	@Autowired
	private  CustAccountController custAccountController;
	
	@Autowired
	private  AdminService adminService;
	
	@Autowired
	private TSRiskScoreService tSRiskScoreService;
	
	@Autowired
	private  CaptureKycService captureKycService;
	
	@Autowired
	private  KYCDtlService kYCDtlService;
	
	@Autowired
	private  SmsController smsController ;
	
	@Autowired
	private  CustAccountDtlRepository custAccountDtlRepository;
	
	@Autowired
    private JwtUtil jwtUtil;
	
	@Autowired
	 private UsersRepository repository;
	
	
	@GetMapping("/auth")
	public String login(Model model, HttpServletRequest request) {
		return "login";
	}
	
	@PostMapping("/auth/dashboard")
	public ResponseEntity<Object>  authenticate(@RequestBody LoginDto loginDto) {
		
		if((loginDto.getUserName().equals("admin") && loginDto.getPassword().equals("admin@123")) || (loginDto.getUserName().equals("nbfc") && loginDto.getPassword().equals("nbfc@123")))
			return new ResponseEntity<Object>(loginDto, HttpStatus.OK);
		return new ResponseEntity<Object>("Error", HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("{id}")
	public String findById(@PathVariable("id") int id, Model model) {
		if (!(service.getUserById(id)!=null)) {
			return "index1";
		}
		Users user = service.getUserById(id);
		CustomerDetails custDtl = customerDtlService.getCustomerByUserId(id);
		AddressDetails addressDtl = addressDtlService.getAddressUserById(id);
		CustomerAccountsDetails accountDtl = custAccountDtlService.getCustomerAccountByUserId(id);
		CustomerBankDetails bankDtl = custBankDtlService.getCustomerBankByUesrId(id);
		CustomerEmployerDetails employerDtl = custEmployerDtlService.getCustomerEmployerByUserId(id);
		CustomerReferenceDetails refDtl = custRefDtlService.getCustRefDtlByUserId(id);
		TSRiskScore tSRiskScore = tSRiskScoreService.getTSRiskScoreUserById(id);
		com.javatechie.spring.ajax.api.entity.PanExtraction panExtraction = panExtractionService.getAddressUserById(id);
		KYCDetails kyctDtl = kYCDtlService.getKycDtlByUserId(id);
		SummaryResponse loanCalc  = custAccountController.calculateSummary(accountDtl.getDurationInDays(), accountDtl.getAppliedAmt());
		CaptureKyc captureKyc = captureKycService.getCaputureKycByUserId(id);
		model.addAttribute("user", user);
		model.addAttribute("custDtl", custDtl);
		model.addAttribute("addressDtl", addressDtl);
		model.addAttribute("accountDtl", accountDtl);
		model.addAttribute("bankDtl", bankDtl);
		model.addAttribute("employerDtl", employerDtl);
		model.addAttribute("refDtl", refDtl);
		model.addAttribute("loanCalc", loanCalc);
		model.addAttribute("tSRiskScore", tSRiskScore);
		model.addAttribute("panExtraction", panExtraction);
		model.addAttribute("adhaarExtraction", captureKyc);
		model.addAttribute("kyctDtl", kyctDtl);
		return "account/info";
	}
	
	@GetMapping("/view/{id}")
	public String findByIdView(@PathVariable("id") int id, Model model) {
		if (!(service.getUserById(id)!=null)) {
			return "index2";
		}
		Users user = service.getUserById(id);
		CustomerDetails custDtl = customerDtlService.getCustomerByUserId(id);
		AddressDetails addressDtl = addressDtlService.getAddressUserById(id);
		CustomerAccountsDetails accountDtl = custAccountDtlService.getCustomerAccountByUserId(id);
		CustomerBankDetails bankDtl = custBankDtlService.getCustomerBankByUesrId(id);
		CustomerEmployerDetails employerDtl = custEmployerDtlService.getCustomerEmployerByUserId(id);
		CustomerReferenceDetails refDtl = custRefDtlService.getCustRefDtlByUserId(id);
		TSRiskScore tSRiskScore = tSRiskScoreService.getTSRiskScoreUserById(id);
		com.javatechie.spring.ajax.api.entity.PanExtraction panExtraction = panExtractionService.getAddressUserById(id);
		KYCDetails kyctDtl = kYCDtlService.getKycDtlByUserId(id);
		SummaryResponse loanCalc  = custAccountController.calculateSummary(accountDtl.getDurationInDays(), accountDtl.getAppliedAmt());
		CaptureKyc captureKyc = captureKycService.getCaputureKycByUserId(id);
		model.addAttribute("user", user);
		model.addAttribute("custDtl", custDtl);
		model.addAttribute("addressDtl", addressDtl);
		model.addAttribute("accountDtl", accountDtl);
		model.addAttribute("bankDtl", bankDtl);
		model.addAttribute("employerDtl", employerDtl);
		model.addAttribute("refDtl", refDtl);
		model.addAttribute("loanCalc", loanCalc);
		model.addAttribute("tSRiskScore", tSRiskScore);
		model.addAttribute("panExtraction", panExtraction);
		model.addAttribute("adhaarExtraction", captureKyc);
		model.addAttribute("kyctDtl", kyctDtl);
		return "account/info2";
	}
	
	@PostMapping("/{id}/api/kyc/{documentType}") 
	public String getPan(@PathVariable("id") int id, @PathVariable("documentType") String profileId, Model model) throws Exception {
		
		try {
			
			//adminService.generatePDFFromHTML("dfd");
			//KycExtraction kycExtraction = getKycAdhaarDtl(profileId);
			CustomerDetails custDtl = customerDtlService.getCustomerByUserId(id);
			String getRequestId = getRequestId(custDtl);
			getPanDtl(getRequestId,id);
			
		//	model.addAttribute("kycExtraction", kycExtraction);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "home";
	}
	
	@PostMapping("/{authToken}/api/kyc/adhaar/{profileId}") 
	public String getAdhaar(@PathVariable("authToken") String authToken, @PathVariable("profileId") String profileId, Model model) throws Exception {
		
		try {
			
		     String userName = null;
		     String token = null;

		        if (authToken != null && authToken.startsWith("Bearer ")) {
		            token = authToken.substring(7);
		            userName = jwtUtil.extractUsername(token);
		        }
		        Users user = repository.findByphoneNumber(userName);
			KycExtraction kycExtraction = getKycAdhaarDtl(profileId,user);
			
			model.addAttribute("kycExtraction", kycExtraction);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "home";
	}
	
	@PostMapping("/api/kyc") 
	public ResponseEntity<Object> getIdfyLink() throws Exception {
		
		KycLinkData kycLinkData = null;
		try {
			
			kycLinkData = getKycLink();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(new IdfyProfileResponse(kycLinkData.getCaptureLink(),kycLinkData.getProfileId(),HttpStatus.OK), HttpStatus.OK);
	}
	
	private KycExtraction getKycAdhaarDtl(String profileId, Users user) throws IOException {
		
		StringBuilder builder = new StringBuilder();
		Map<String, Object> mapRequest = new HashMap<String, Object>();
		OkHttpClient client = new OkHttpClient().newBuilder()
				  .build();
				Request request = new Request.Builder()
				  .url("https://api.kyc.idfy.com/profiles/"+profileId)
				  .method("GET", null)
				  .addHeader("api-key", "5d01e023-b5d6-49d5-9fb7-76ad3a7d8d88")
				  .addHeader("Content-Type", "application/json")
				  .addHeader("account-id", "cd7d8a16c46a/37cb4f7c-4fe9-4528-8600-ea8f4b453384")
				  .build();
				RealResponseBody response = (RealResponseBody) client.newCall(request).execute().body();
				ObjectMapper objectMapper = new ObjectMapper();
				int b=0;
				 while ((b = response.byteStream().read()) != -1) {
			         char ch = (char)b; 
			         System.out.print(ch);
			         builder.append(ch);
				 }
				 objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
				 mapRequest = objectMapper.readValue(builder.toString(), new TypeReference<Map<String, Object>>(){});
			//	KycExtraction kycExtraction = objectMapper.readValue(builder.toString(), KycExtraction.class);
				 KycExtraction kycExtraction = null;
				 adminService.saveCaptureKyc(mapRequest, user.getId());
				
				return kycExtraction;
				 
				 
				 
	}

	private KycLinkData getKycLink() throws JsonProcessingException {

		String profileLink = "https://api.kyc.idfy.com/sync/profiles";
		String mapToJson="";
		KycLinkData kycLinkData = null;
		String reference_id =  UUID.randomUUID().toString();
		Map<String, Object> name = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String,Object>();
		Map<String, Object> data = new HashMap<String,Object>();
		Map<String, Object> config = new HashMap<String,Object>();
		name.put("first_name", "A");
		name.put("last_name", "A");
		name.put("middle_name", "A");
		config.put("id", "fb1360aa-d494-439a-a3a2-c3a3f4251892");
		param.put("reference_id",reference_id);
		param.put("group_id", "8e16424a-58fc-4ba4-ab20-5bc8e7c3c41e");
		data.put("name", name);
		param.put("data", data);
		param.put("config", config);
		ObjectMapper objectMapper = new ObjectMapper();
		mapToJson = objectMapper.writeValueAsString(param);
		 OkHttpClient client = new OkHttpClient().newBuilder().build();
				MediaType mediaType = MediaType.parse("application/json");
				okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, mapToJson);
				Request request = new Request.Builder()
				  .url(profileLink)
				  .method("POST", body)
				  .addHeader("Content-Type", "application/json")
				  .addHeader("account-id", "cd7d8a16c46a/37cb4f7c-4fe9-4528-8600-ea8f4b453384")
				  .addHeader("api-key", "5d01e023-b5d6-49d5-9fb7-76ad3a7d8d88")
				  .build();
				try {
					Response response = client.newCall(request).execute();
					String responseBody = response.body().string();
					kycLinkData = objectMapper.readValue(responseBody, KycLinkData.class);
				} catch (IOException e) {
					e.printStackTrace();
				}
		

		return kycLinkData;

		
	}

	private void getPanDtl (String requestId,int userId) throws IOException {
		
		Map<String, Object> mapToJson = new HashMap<String, Object>();
		StringBuilder builder = new StringBuilder();
		String newStr = "";
		OkHttpClient client = new OkHttpClient().newBuilder()
				  .build();
				Request request = new Request.Builder()
				  .url("https://eve.idfy.com/v3/tasks?request_id="+requestId)
				  .method("GET", null)
				  .addHeader("api-key", "5d01e023-b5d6-49d5-9fb7-76ad3a7d8d88")
				  .addHeader("Content-Type", "application/json")
				  .addHeader("account-id", "cd7d8a16c46a/37cb4f7c-4fe9-4528-8600-ea8f4b453384")
				  .build();
				RealResponseBody response = (RealResponseBody) client.newCall(request).execute().body();
				ObjectMapper objectMapper = new ObjectMapper();
				System.out.println();
				int b=0;
				 while ((b = response.byteStream().read()) != -1) { 
			         char ch = (char)b; 
			         System.out.print(ch);
			         builder.append(ch);
				 }
				 
				 newStr = builder.toString().substring(1, builder.toString().length()-1);
				 PanExtraction panExtraction = objectMapper.readValue(newStr, PanExtraction.class);
				if(panExtraction.getStatus().equals("completed")) {
					
					com.javatechie.spring.ajax.api.entity.PanExtraction panExtractionEntity = new com.javatechie.spring.ajax.api.entity.PanExtraction();
					com.javatechie.spring.ajax.api.entity.PanSourceOutput panSourceOutputEntity = new com.javatechie.spring.ajax.api.entity.PanSourceOutput();
					com.javatechie.spring.ajax.api.entity.PanResult panResult = new com.javatechie.spring.ajax.api.entity.PanResult();
					BeanUtils.copyProperties(panExtraction.getResult().getSourceOutput(), panSourceOutputEntity);
					BeanUtils.copyProperties(panExtraction.getResult(), panResult);
					BeanUtils.copyProperties(panExtraction, panExtractionEntity);
					panExtractionEntity.setResult(panResult);
					panExtractionEntity.getResult().setSourceOutput(panSourceOutputEntity);;
					panExtractionEntity.setUserId(userId);
					panExtractionService.savePanExtraction(panExtractionEntity);
					
				}
		
	}
		
	private String getRequestId(CustomerDetails custDtl) throws JsonProcessingException {
		
		String panUrl = "https://eve.idfy.com/v3/tasks/async/verify_with_source/ind_pan";
		String mapToJson="";
		Map<String, Object> mapRequest = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String,Object>();
		Map<String, Object> data = new HashMap<String,Object>();
		data.put("id_number", custDtl.getPanNo());
		param.put("task_id", "74f4c926-250c-43ca-9c53-453e87ceacd1");
		param.put("group_id", "8e16424a-58fc-4ba4-ab20-5bc8e7c3c41e");
		param.put("data", data);
		ObjectMapper objectMapper = new ObjectMapper();
		mapToJson = objectMapper.writeValueAsString(param);
		 OkHttpClient client = new OkHttpClient().newBuilder().build();
				MediaType mediaType = MediaType.parse("application/json");
				okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, mapToJson);
				Request request = new Request.Builder()
				  .url(panUrl)
				  .method("POST", body)
				  .addHeader("Content-Type", "application/json")
				  .addHeader("account-id", "cd7d8a16c46a/37cb4f7c-4fe9-4528-8600-ea8f4b453384")
				  .addHeader("api-key", "5d01e023-b5d6-49d5-9fb7-76ad3a7d8d88")
				  .addHeader("x-consumer-custom-id", "21345")
				  .build();
				try {
					Response response = client.newCall(request).execute();
					String responseBody = response.body().string();
					mapRequest = objectMapper.readValue(responseBody, new TypeReference<Map<String, Object>>(){});
				} catch (IOException e) {
					e.printStackTrace();
				}
		

		return (String) mapRequest.get("request_id");
	}
	
	@GetMapping("{id}/edit")
	public String edit(@PathVariable("id") int id, Model model) {
		
		Users user = service.getUserById(id);
		CustomerDetails custDtl = customerDtlService.getCustomerByUserId(id);
		AddressDetails addressDtl = addressDtlService.getAddressUserById(id);
		CustomerAccountsDetails accountDtl = custAccountDtlService.getCustomerAccountByUserId(id);
		CustomerBankDetails bankDtl = custBankDtlService.getCustomerBankByUesrId(id);
		CustomerEmployerDetails employerDtl = custEmployerDtlService.getCustomerEmployerByUserId(id);
		CustomerReferenceDetails refDtl = custRefDtlService.getCustRefDtlByUserId(id);
		model.addAttribute("user", user);
		model.addAttribute("custDtl", custDtl);
		model.addAttribute("addressDtl", addressDtl);
		model.addAttribute("accountDtl", accountDtl);
		model.addAttribute("bankDtl", bankDtl);
		model.addAttribute("employerDtl", employerDtl);
		model.addAttribute("refDtl", refDtl);
		
		return "account/updateLoanForm";
	}
	
	@PostMapping("/edit")
	public String editCustomer( Model model,@RequestBody UpdateCustomerInfo updateCustomer) {
		
		adminService.updateCustomer(updateCustomer.getUserId(),updateCustomer);
		
		
		return "index1";
	}

	
	@GetMapping("/dashboard")
	public String home(Model model,
			@PageableDefault(size = 10, sort = { "id" }, direction = Direction.DESC) Pageable pageable,
			@RequestParam(name = "search", required = false) String search) {
		Page<Users> pagination = service.pagination(search, pageable);
		List<CustomerAccountsDetails> accountDtls = custAccountDtlService.getCustomerAccountDtls();
		model.addAttribute("page", pagination);
		model.addAttribute("key", search);
		model.addAttribute("accountDtls", accountDtls);
		return "index1";
	}
	
	@GetMapping("/dashboardView")
	public String dashboardView(Model model,
			@PageableDefault(size = 10, sort = { "id" }, direction = Direction.DESC) Pageable pageable,
			@RequestParam(name = "search", required = false) String search) {
		Page<Users> pagination = service.pagination("Approved", pageable);
		List<CustomerAccountsDetails> accountDtls = custAccountDtlService.getCustomerAccountDtls();
		model.addAttribute("page", pagination);
		model.addAttribute("key", search);
		model.addAttribute("accountDtls", accountDtls);
		return "index2";
	}
	
	@GetMapping
	public String index(Model model,
			@PageableDefault(size = 10, sort = { "id" }, direction = Direction.DESC) Pageable pageable,
			@RequestParam(name = "search", required = false) String search) {
		Page<Users> pagination = service.pagination(search, pageable);
		model.addAttribute("page", pagination);
		model.addAttribute("key", search);
		return "account/index";
	}
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model ,Pageable pageable,
			@RequestParam(name = "search", required = false) String search) {
	    int pageSize = 10;
	     pageable = PageRequest.of(pageNo - 1, pageSize);
	     Page<Users> page = service.pagination(search, pageable);
	    List < Users > listEmployees = page.getContent();
	    model.addAttribute("page", page);
	    model.addAttribute("currentPage", pageNo);
	    model.addAttribute("totalPages", page.getTotalPages());
	    model.addAttribute("totalItems", page.getTotalElements());
	    model.addAttribute("listEmployees", listEmployees);
	    return "index1";
	}
	
	@GetMapping("/page/view/{pageNo}")
	public String findPaginatedView(@PathVariable(value = "pageNo") int pageNo, Model model ,Pageable pageable,
			@RequestParam(name = "search", required = false) String search) {
	    int pageSize = 10;
	     pageable = PageRequest.of(pageNo - 1, pageSize);
	     Page<Users> page = service.pagination("Approved", pageable);
	    List < Users > listEmployees = page.getContent();
	    model.addAttribute("page", page);
	    model.addAttribute("currentPage", pageNo);
	    model.addAttribute("totalPages", page.getTotalPages());
	    model.addAttribute("totalItems", page.getTotalElements());
	    model.addAttribute("listEmployees", listEmployees);
	    return "index2";
	}
	
	@PostMapping("/{id}/api/rejectLoan/{remarks}")
	public String rejectLoan(@PathVariable(value="id") int id,@PathVariable(value="remarks") String remarks, Model model) {
		
		CustomerAccountsDetails accountDtl = custAccountDtlService.getCustomerAccountByUserId(id);
		CommonDBFields commonField = new CommonDBFields();
		commonField.setUpdatedDate(new Date());
		commonField.setApprovalStatus("Rejected");
		custAccountDtlRepository.save(accountDtl);
		return "account/info";
	}
	
	@PostMapping("/{id}/api/approveLoan/{remarks}")
	public String approveLoan(@PathVariable(value="id") int id,@PathVariable(value="remarks") String remarks, Model model) {
		
		Date date = new Date();
		Users user = service.getUserById(id);
		user.setUpdated_date(date);
		user.setApprovalStatus("Approved");
		repository.save(user);
		CustomerAccountsDetails accountDtl = custAccountDtlService.getCustomerAccountByUserId(id);
		CommonDBFields commonField = new CommonDBFields();
		commonField.setApprovalStatus("Approved");
		commonField.setUpdatedDate(new Date());
		accountDtl.setCommonDBFields(commonField);
		accountDtl.setRemarks(remarks);
		custAccountDtlRepository.save(accountDtl);
		smsController.sendConsentNotification(accountDtl);
		return "account/info";
	}
	
	@PostMapping("/{id}/disbursed/{cibilScore}/{equifax}/{remarks}")
	public String disbursedLoan(@PathVariable(value="cibilScore") String cibilScore,@PathVariable(value="id") int id,
			@PathVariable(value="equifax") String equifax,
			@PathVariable(value="remarks") String remarks,
			Model model) {
		
		Date date = new Date();
		Users user = service.getUserById(id);
		user.setApprovalStatus("Closed");
		user.setUpdated_date(date);
		repository.save(user);
		CustomerAccountsDetails accountDtl = custAccountDtlService.getCustomerAccountByUserId(id);
		CommonDBFields commonField = new CommonDBFields();
		commonField.setApprovalStatus("Closed");
		commonField.setUpdatedDate(date);
		accountDtl.setCommonDBFields(commonField);
		accountDtl.setCibilScore(cibilScore);
		accountDtl.setEquifax(equifax);
		accountDtl.setRemarks(remarks);
		custAccountDtlRepository.save(accountDtl);
		smsController.sendPaymentReciptNotification(accountDtl);
		return "account/info";
	}
	
	@GetMapping("/customerLetter/{jwt}")
	public String customerLetter(@PathVariable(value="jwt") String jwttoken, Model model) {
		String userName=null;
		userName = jwtUtil.extractUsername(jwttoken);
		Users user = repository.findByphoneNumber(userName);
		//Users user = service.getUserById(Integer.parseInt(jwttoken));
		CustomerDetails custDtl = customerDtlService.getCustomerByUserId(user.getId());
		AddressDetails addressDtl = addressDtlService.getAddressUserById(user.getId());
		CustomerAccountsDetails accountDtl = custAccountDtlService.getCustomerAccountByUserId(user.getId());
		SummaryResponse loanCalc  = custAccountController.calculateSummary(accountDtl.getDurationInDays(), accountDtl.getAppliedAmt());
		model.addAttribute("loanCalc", loanCalc);
		model.addAttribute("user", user);
		model.addAttribute("custDtl", custDtl);
		model.addAttribute("addressDtl", addressDtl);
		model.addAttribute("accountDtl", accountDtl);
		smsController.sendConsentOtp(accountDtl);
		return "account/Sanction_Agree_Letter";
	}
}

