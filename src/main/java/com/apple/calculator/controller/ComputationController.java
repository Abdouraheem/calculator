package com.apple.calculator.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apple.calculator.domain.Expression;
import com.apple.calculator.domain.Result;
import com.apple.calculator.service.ComputationService;

@RestController
@RequestMapping("/calculator")
public class ComputationController {

	private ComputationService service;
	
	@Autowired
	public ComputationController(ComputationService service) {
		this.service = service;
	}
	
	@GetMapping("/compute")
	public Result compute(@RequestBody @Valid Expression expression) {
		
		return service.compute(expression);
	}
}
