package io.corp.calculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.corp.calculator.exception.CalculatorException;
import io.corp.calculator.model.Operation;
import io.corp.calculator.model.Result;
import io.corp.calculator.service.CalculatorService;
import io.corp.calculator.validator.CalculatorValidator;

@RestController
@RequestMapping("/api/v1/calculator")
public class CalculatorControllerV1 {

	@Autowired
	CalculatorService calculatorService;
	
	@Autowired
	CalculatorValidator calculatorValidator;

    @PostMapping("/calculate")
    public Result calculate(@RequestBody final Operation operation) {
		if (!calculatorValidator.validateOperation(operation))
			throw new CalculatorException("Invalid Operation", "CalculatorControllerV1", "operate");
		
    	return calculatorService.operate(operation);
    }

}