package com.apple.calculator.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Expression {
	
	@NotEmpty(message = "Expression cannot be empty.")
	@NotNull(message = "Expression cannot be null.")
	private String expression;

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}
	
	
}
