package io.corp.calculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.corp.calculator.model.Result;
import io.corp.calculator.service.CalculatorService;

@RestController
@RequestMapping("/api/v2/calculator")
public class CalculatorControllerV2 {

	@Autowired
	CalculatorService calculatorService;

    @PostMapping("/")
    public Result calculate(@RequestBody final String operation) {
    	return calculatorService.operate(operation);
    }

}