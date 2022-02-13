package io.corp.calculator.service;

import io.corp.calculator.exception.CalculatorException;
import io.corp.calculator.model.Operation;
import io.corp.calculator.model.Result;

public interface CalculatorService {
	
	public Result operate ( Operation operation) throws CalculatorException;
	
	public Result operate ( String expression) throws CalculatorException;

}
