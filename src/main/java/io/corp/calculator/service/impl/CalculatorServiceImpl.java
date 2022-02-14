package io.corp.calculator.service.impl;

import org.springframework.stereotype.Service;

import io.corp.calculator.exception.CalculatorException;
import io.corp.calculator.model.Operation;
import io.corp.calculator.model.Result;
import io.corp.calculator.service.CalculatorService;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

@Service
public class CalculatorServiceImpl implements CalculatorService {

	@Override
	public Result operate(Operation operation) throws CalculatorException {
		Expression expression = new ExpressionBuilder(
				operation.getFirstNumber() + operation.getOperator() + operation.getSecondNumber()).build();
		return Result.builder().result(expression.evaluate()).build();
	}

	@Override
	public Result operate(String strExpression) throws CalculatorException {
		  Expression expression = new ExpressionBuilder(strExpression).build();
		  return Result.builder().result(expression.evaluate()).build();
	}

}
