package com.epam.texthandling.logic.expressioncalculation;

import com.epam.texthandling.exception.ExpressionCalculatorException;
import com.epam.texthandling.logic.ExpressionCalculator;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ExpressionCalculatorTest {

    private static final String ADDITION_EXPRESSION = "[13 2 +]";
    private static final Double ANSWER_TO_ADDITION = 15.0;
    private static final String SUBTRACTION_EXPRESSION = "[5 2 -]";
    private static final Double ANSWER_TO_SUBTRACTION = 3.0;
    private static final String MULTIPLICATION_EXPRESSION = "[3 3 *]";
    private static final Double ANSWER_TO_MULTIPLICATION = 9.0;
    private static final String DIVISION_EXPRESSION = "[15 3 /]";
    private static final Double ANSWER_TO_DIVISION = 5.0;
    private static final String EXPRESSION_WITH_VARIABLES = "[2 3 * y +]";
    private static final Double ANSWER_TO_EXPRESSION_WITH_VARIABLES = 7.0;
    private static final String EXPRESSION_WITH_UNKNOWN_VARIABLES = "[c a + 3 /]";

    private static final Map<Character, Double> EMPTY_EXPRESSION_MAP = new HashMap<>();
    private static final Map<Character, Double> VARIABLES_MAP = new HashMap<>();

    static {
        VARIABLES_MAP.put('y', 1.0);
    }

    private final ExpressionCalculator expressionCalculator = new ExpressionCalculator();




    @Test
    public void testCalculateShouldReturnCalculatedAdditionExpression() throws ExpressionCalculatorException {
        //given
        //when
        Double actualAnswer = expressionCalculator.calculate(ADDITION_EXPRESSION, EMPTY_EXPRESSION_MAP);
        //then
        Assert.assertEquals(ANSWER_TO_ADDITION, actualAnswer);
    }

    @Test
    public void testCalculateShouldReturnCalculatedSubtractionExpression() throws ExpressionCalculatorException {
        //given
        //when
        Double actualAnswer = expressionCalculator.calculate(SUBTRACTION_EXPRESSION, EMPTY_EXPRESSION_MAP);
        //then
        Assert.assertEquals(ANSWER_TO_SUBTRACTION, actualAnswer);
    }

    @Test
    public void testCalculateShouldReturnCalculatedMultiplicationExpression() throws ExpressionCalculatorException {
        //given
        //when
        Double actualAnswer = expressionCalculator.calculate(MULTIPLICATION_EXPRESSION, EMPTY_EXPRESSION_MAP);
        //then
        Assert.assertEquals(ANSWER_TO_MULTIPLICATION, actualAnswer);
    }

    @Test
    public void testCalculateShouldReturnCalculatedDivisionExpression() throws ExpressionCalculatorException {
        //given
        //when
        Double actualAnswer = expressionCalculator.calculate(DIVISION_EXPRESSION, EMPTY_EXPRESSION_MAP);
        //then
        Assert.assertEquals(ANSWER_TO_DIVISION, actualAnswer);
    }

    @Test
    public void testCalculateShouldReturnCalculatedExpressionWithVariables() throws ExpressionCalculatorException {
        //given
        //when
        Double actualAnswer = expressionCalculator.calculate(EXPRESSION_WITH_VARIABLES, VARIABLES_MAP);
        //then
        Assert.assertEquals(ANSWER_TO_EXPRESSION_WITH_VARIABLES, actualAnswer);
    }

    @Test(expected = ExpressionCalculatorException.class)
    public void testCalculateShouldThrowExceptionCalculatorExceptionWithUnknownVariables() throws ExpressionCalculatorException {
        //given
        //when
        Double actualAnswer = expressionCalculator.calculate(EXPRESSION_WITH_UNKNOWN_VARIABLES, EMPTY_EXPRESSION_MAP);
        //then
    }




}
