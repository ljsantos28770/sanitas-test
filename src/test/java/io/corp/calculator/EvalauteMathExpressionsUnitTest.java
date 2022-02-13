package io.corp.calculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class EvalauteMathExpressionsUnitTest {

    @Test
    public void givenSimpleExpression_whenCallEvaluateMethod_thenSuccess() {
        Expression expression = new ExpressionBuilder("3+2").build();
        double result = expression.evaluate();
        Assertions.assertEquals(5, result);
    }

    @Test
    public void givenTwoVariables_whenCallEvaluateMethod_thenSuccess() {
        Expression expression = new ExpressionBuilder("3x+2y").variables("x", "y")
          .build()
          .setVariable("x", 2)
          .setVariable("y", 3);
        double result = expression.evaluate();
        Assertions.assertEquals(12, result);
    }
    
    @Test
    public void givenMathFunctions_whenCallEvaluateMethod_thenSuccess() {
        Expression expression = new ExpressionBuilder("sin(x)*sin(x)+cos(x)*cos(x)").variables("x")
          .build()
          .setVariable("x", 0.5);
        double result = expression.evaluate();
        Assertions.assertEquals(1, result);
    }


    
}