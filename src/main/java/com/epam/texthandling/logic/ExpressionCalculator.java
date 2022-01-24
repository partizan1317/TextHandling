package com.epam.texthandling.logic;

import com.epam.texthandling.exception.ExpressionCalculatorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ExpressionCalculator {

    private static final String LEXEME_DELIMITER = " ";
    private static final String FIRST_REPLACE_EXPRESSION = "[\\[\\]]";
    private static final String SECOND_REPLACE_EXPRESSION = "";

    private static final Logger LOGGER = LogManager.getLogger(ExpressionCalculator.class);
    
    private final List<AbstractMathExpression> listExpression = new ArrayList<>();

    public Double calculate(String expression, Map<Character, Double> variables) throws ExpressionCalculatorException {
        LOGGER.info("Started calculating " + expression + "with variables " + variables.toString());
        listExpression.clear();
        expression = expression.replaceAll(FIRST_REPLACE_EXPRESSION, SECOND_REPLACE_EXPRESSION);
        for (String lexeme : expression.split(LEXEME_DELIMITER)) {
            if (lexeme.isEmpty()) {
                continue;
            }
            char temp = lexeme.charAt(0);
            switch(temp) {
                case '+':
                    if (lexeme.length() == 1) {
                        listExpression.add(new TerminalExpressionPlus());
                    } else {
                        addNumberToExpression(lexeme, variables);
                    }
                    break;
                case '-':
                    if (lexeme.length() == 1) {
                        listExpression.add(new TerminalExpressionMinus());
                    } else {
                        addNumberToExpression(lexeme, variables);
                    }
                    break;
                case '/':
                    listExpression.add(new TerminalExpressionDivide());
                    break;
                case '*':
                    listExpression.add(new TerminalExpressionMultiply());
                    break;
                default:
                    addNumberToExpression(lexeme, variables);

            }

        }
        Double resultValue = calculateExpression();
        LOGGER.info("Calculated " + expression + " with variables " + variables + "\nAnswer: " + resultValue);
        return calculateExpression();
    }

    private void addNumberToExpression(String lexeme, Map<Character, Double> variables) throws ExpressionCalculatorException {
        Scanner scan = new Scanner(lexeme);
        if(scan.hasNextDouble()) {
            listExpression.add(new NonTerminalExpressionNumber(scan.nextDouble()));
        } else {
            Double value = variables.get(lexeme.charAt(0));
            if (value != null) {
                listExpression.add(new NonTerminalExpressionNumber(value));
            } else {
                ExpressionCalculatorException expressionCalculatorException = new ExpressionCalculatorException("Could not calculate expression..");
                LOGGER.throwing(expressionCalculatorException);
                throw expressionCalculatorException;
            }
        }
    }

    private Double calculateExpression() {
        Context context = new Context();
        for (AbstractMathExpression expression : listExpression) {
            expression.interpret(context);
        }
        return context.popValue();
    }

}
