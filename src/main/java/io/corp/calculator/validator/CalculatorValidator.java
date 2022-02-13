package io.corp.calculator.validator;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import io.corp.calculator.model.Operation;

public class CalculatorValidator {

	List<String> validOperators = Arrays.asList("+", "-");

	private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

	public boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}
		return pattern.matcher(strNum).matches();
	}

	public boolean isValidOperator(String stOp) {
		if (stOp == null) {
			return false;
		}
		return validOperators.stream().parallel().filter(x -> stOp.equalsIgnoreCase(x)).findAny().isPresent();
	}

	public boolean validateOperation(Operation operator) {

		return (isNumeric(operator.getFirstNumber()) && isNumeric(operator.getSecondNumber())
				&& isValidOperator(operator.getOperator()));

	}

}
