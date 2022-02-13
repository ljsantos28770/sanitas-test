package io.corp.calculator.exception;

public class CalculatorException extends RuntimeException {
	
	private final String className;
	private final String methodName;
	
	
	private static final long serialVersionUID = 1L;

	public CalculatorException (final String message, final Throwable cause) {
		super(message,cause);
		this.className=cause.getClass().getName();
		this.methodName=cause.getClass().getTypeName();
	}
	
	public CalculatorException (String message, String className, String methodName) {
		super(message);
		this.className=className;
		this.methodName=methodName;
	}

	public String getClassName() {
		return className;
	}

	public String getMethodName() {
		return methodName;
	}

	
}