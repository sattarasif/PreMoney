package com.javatechie.spring.ajax.api.entity;

import javax.persistence.Embedded;
public class PanResult {

	@Embedded
	private PanSourceOutput sourceOutput;

	public PanSourceOutput getSourceOutput() {
		return sourceOutput;
	}

	public void setSourceOutput(PanSourceOutput sourceOutput) {
		this.sourceOutput = sourceOutput;
	}
}