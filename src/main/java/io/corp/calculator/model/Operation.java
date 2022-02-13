package io.corp.calculator.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Operation {
	
	String firstNumber;
	String secondNumber;
	String operator;

}
