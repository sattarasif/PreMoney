package com.javatechie.spring.ajax.api.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.javatechie.spring.ajax.api.dto.AdhaarExtraction;
import com.javatechie.spring.ajax.api.dto.KycResult;
import com.javatechie.spring.ajax.api.dto.UpdateCustomerInfo;
import com.javatechie.spring.ajax.api.entity.AddressDetails;
import com.javatechie.spring.ajax.api.entity.CaptureKyc;
import com.javatechie.spring.ajax.api.entity.CustomerAccountsDetails;
import com.javatechie.spring.ajax.api.entity.CustomerBankDetails;
import com.javatechie.spring.ajax.api.entity.CustomerDetails;
import com.javatechie.spring.ajax.api.entity.CustomerEmployerDetails;
import com.javatechie.spring.ajax.api.entity.CustomerReferenceDetails;
import com.javatechie.spring.ajax.api.entity.Users;
import com.javatechie.spring.ajax.api.repository.AddressDtlRepository;
import com.javatechie.spring.ajax.api.repository.CaptureKycRepository;
import com.javatechie.spring.ajax.api.repository.CustAccountDtlRepository;
import com.javatechie.spring.ajax.api.repository.CustBankDtlRepository;
import com.javatechie.spring.ajax.api.repository.CustEmployerDtlRepository;
import com.javatechie.spring.ajax.api.repository.CustRefDtlRepository;
import com.javatechie.spring.ajax.api.repository.CustomerDtlRepository;

@Service
public class AdminService {

	@Autowired
	private  CaptureKycService captureKycService;
	
	@Autowired
	private CaptureKycRepository captureRepository;
	
	@Autowired
	private CustomerDtlService customerDtlService;
	
	@Autowired
	private AddressDtlService addressDtlService;
	
	@Autowired
	private  CustAccountDtlService custAccountDtlService;
	
	@Autowired
	private  CustEmployerDtlService custEmployerDtlService;
	
	@Autowired
	private  CustRefDtlService custRefDtlService;
	
	@Autowired
	private CustomerDtlRepository customerDtlRepository;
	
	@Autowired
	private AddressDtlRepository addressDtlRepository;
	
	@Autowired
	private  CustAccountDtlRepository custAccountDtlRepository;
	
	@Autowired
	private  CustEmployerDtlRepository custEmployerDtlRepository;
	
	@Autowired
	private  CustRefDtlRepository custRefDtlRepository;

	public void saveCaptureKyc(Map<String, Object> mapToJson,int userId) {
		
		CaptureKyc caputureKyc = new CaptureKyc();
		Map config = (Map) mapToJson.get("config");
		Map profile_data = (Map) mapToJson.get("profile_data");
		List list = (ArrayList) mapToJson.get("tasks");
		Map tasks = (Map)  list.get(0);
		Map xml_output = (Map) ((Map<String, Object>) ((Map<String, Object>) tasks.get("result")).get("automated_response")).get("xml_output");
		
		
		caputureKyc.setUserId(userId);
		caputureKyc.setConfig_id(config.get("id").toString());
		caputureKyc.setCompleted_at(profile_data.get("completed_at").toString());
		caputureKyc.setCreated_at(profile_data.get("created_at").toString());
		caputureKyc.setProfile_id(mapToJson.get("profile_id").toString());
		caputureKyc.setReference_id(mapToJson.get("reference_id").toString());
		
		KycResult kycResult = new KycResult();
		AdhaarExtraction adhaarExtraction = new AdhaarExtraction();
		adhaarExtraction.setAddress(xml_output.get("address").toString());
		adhaarExtraction.setDate_of_birth(xml_output.get("date_of_birth").toString());
		adhaarExtraction.setGender(xml_output.get("gender").toString());
		adhaarExtraction.setGenerated_at(xml_output.get("generated_at").toString());
		adhaarExtraction.setName(xml_output.get("name").toString());
		adhaarExtraction.setReference_number(xml_output.get("reference_number").toString());
		adhaarExtraction.setYear_of_birth(xml_output.get("year_of_birth").toString());
		adhaarExtraction.setStatus(tasks.get("status").toString());
		adhaarExtraction.setTask_id(tasks.get("task_id").toString());
		adhaarExtraction.setTast_type(tasks.get("task_type").toString());
		
		kycResult.setExtraction_output(adhaarExtraction);
		caputureKyc.setResult(kycResult);
		CaptureKyc updateCaputureKyc = captureKycService.getCaputureKycByUserId(userId);
		if(updateCaputureKyc!=null) 
			caputureKyc.setId(updateCaputureKyc.getId());
		captureRepository.save(caputureKyc);
		
	}
	
	public void generatePDFFromHTML(String filename) throws FileNotFoundException, IOException, DocumentException {
		
	    Document document = new Document();
	    PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("/Users/asifsattar/Desktop/Node/html.pdf"));
	    document.open();
	    XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream("/Users/asifsattar/Desktop/Node/Sanction_Letter.html"));
	    document.close();
	}

	public void updateCustomer(int id, UpdateCustomerInfo updateCustomer) {

		CustomerDetails custDtl = customerDtlService.getCustomerByUserId(id);
		setCustDtl(updateCustomer, custDtl);
		AddressDetails addressDtl = addressDtlService.getAddressUserById(id);
		setAddressDtl(updateCustomer, addressDtl);
		CustomerAccountsDetails accountDtl = custAccountDtlService.getCustomerAccountByUserId(id);
		setAccountDtl(updateCustomer, accountDtl);
		CustomerEmployerDetails employerDtl = custEmployerDtlService.getCustomerEmployerByUserId(id);
		setEmploymentDtl(updateCustomer, employerDtl);
		CustomerReferenceDetails refDtl = custRefDtlService.getCustRefDtlByUserId(id);
		setRefDtl(updateCustomer, refDtl);
		addressDtlRepository.save(addressDtl);
		custAccountDtlRepository.save(accountDtl);
		custEmployerDtlRepository.save(employerDtl);
		customerDtlRepository.save(custDtl);
		custRefDtlRepository.save(refDtl);
		
		
	}

	private void setRefDtl(UpdateCustomerInfo updateCustomer, CustomerReferenceDetails refDtl) {

		refDtl.setRefAddress1(updateCustomer.getRefAddress1());
		refDtl.setRefName1(updateCustomer.getRefName1());
		refDtl.setRefContactNo1(updateCustomer.getRefContactNo1());
		refDtl.setRelation1(updateCustomer.getRelation1());
		refDtl.setRefAddress2(updateCustomer.getRefAddress2());
		refDtl.setRefName2(updateCustomer.getRefName2());
		refDtl.setRefContactNo2(updateCustomer.getRefContactNo2());
		refDtl.setRelation2(updateCustomer.getRelation2());
	}

	private void setAccountDtl(UpdateCustomerInfo updateCustomer, CustomerAccountsDetails accountDtl) {
		
		
	}

	private void setEmploymentDtl(UpdateCustomerInfo updateCustomer, CustomerEmployerDetails employerDtl) {
		
		employerDtl.setEmployerType(updateCustomer.getEmployerType());
		employerDtl.setEmployerName(updateCustomer.getEmployerName());
		employerDtl.setEmployerContactNo(updateCustomer.getEmployerContactNo());
		employerDtl.setFullAddress(updateCustomer.getEmpfullAddress());
		employerDtl.setCity(updateCustomer.getEmpcity());
		employerDtl.setState(updateCustomer.getEmpstate());
		employerDtl.setZipCode(updateCustomer.getEmpzipCode());
		
	}

	private void setAddressDtl(UpdateCustomerInfo updateCustomer, AddressDetails addressDtl) {
		
		addressDtl.setAddressLine1(updateCustomer.getAddressLine1());
		addressDtl.setAddressLine2(updateCustomer.getAddressLine2());
		addressDtl.setAddressLine3(updateCustomer.getAddressLine3());
		addressDtl.setFullAddress(updateCustomer.getFullAddress());
		addressDtl.setIsPrimaryAddress(updateCustomer.getIsPrimaryAddress());
		addressDtl.setAddressType(updateCustomer.getAddressType());
		addressDtl.setCity(updateCustomer.getCity());
		addressDtl.setState(updateCustomer.getState());
		addressDtl.setZipCode(updateCustomer.getZipCode());
		
	}

	private void setCustDtl(UpdateCustomerInfo updateCustomer, CustomerDetails custDtl) {
	
		custDtl.setFirstName(updateCustomer.getFirstName());
		custDtl.setMiddleName(updateCustomer.getMiddleName());
		custDtl.setLastName(updateCustomer.getLastName());
		custDtl.setFullName(updateCustomer.getFullName());
		custDtl.setDob(updateCustomer.getDob());
		custDtl.setEmailAddress(updateCustomer.getEmailAddress());
		custDtl.setGender(updateCustomer.getGender());
		custDtl.setAdhaarNo(updateCustomer.getAdhaarNo());
		custDtl.setPanNo(updateCustomer.getPanNo());
		custDtl.setFatherName(updateCustomer.getFatherName());
		
		
	}

}
