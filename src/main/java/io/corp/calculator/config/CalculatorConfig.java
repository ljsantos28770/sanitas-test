package io.corp.calculator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.corp.calculator.trace.TracerAPI;
import io.corp.calculator.trace.TracerImpl;
import io.corp.calculator.validator.CalculatorValidator;

@Configuration
public class CalculatorConfig {

	@Bean
	public TracerAPI tracerAPI() {
		return new TracerImpl();
	}

	@Bean
	CalculatorValidator calculatorValidator() {
		return new CalculatorValidator();
	}
}
