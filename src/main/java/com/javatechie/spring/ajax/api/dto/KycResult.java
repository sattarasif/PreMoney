package com.javatechie.spring.ajax.api.dto;

import javax.persistence.Embedded;

public class KycResult {

	@Embedded
	private AdhaarExtraction extraction_output;

	public AdhaarExtraction getExtraction_output() {
		return extraction_output;
	}

	public void setExtraction_output(AdhaarExtraction extraction_output) {
		this.extraction_output = extraction_output;
	}
	
	
}
