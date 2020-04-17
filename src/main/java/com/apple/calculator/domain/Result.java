package com.apple.calculator.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Result {

	private double result;

	public double getResult() {
		return result;
	}

	public void setResult(double result) {
		this.result = result;
	}
	
}
