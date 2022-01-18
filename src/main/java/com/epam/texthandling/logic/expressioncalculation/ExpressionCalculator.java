package com.epam.texthandling.logic.expressioncalculation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExpressionCalculator {

    private static final String LEXEME_DELIMITER = " ";
    
    private final List<AbstractMathExpression> listExpression = new ArrayList<>();

    private Integer parseLexeme(String expression) {
        for (String lexeme : expression.split(LEXEME_DELIMITER)) {
            if (lexeme.isEmpty() || lexeme.length() > 1 && addNumberToExpression(lexeme)) {
                continue;
            }
            char temp = lexeme.charAt(0);
            switch(temp) {
                case '+':
                    listExpression.add(new TerminalExpressionPlus());
                    break;
                case '-':
                    listExpression.add(new TerminalExpressionMinus());
                    break;
                case '/':
                    listExpression.add(new TerminalExpressionDivide());
                    break;
                case '*':
                    listExpression.add(new TerminalExpressionMultiply());
                    break;
                default:
                    addNumberToExpression(lexeme);

            }

        }
        return calculate();
    }

    private boolean addNumberToExpression(String lexeme) {
        Scanner scan = new Scanner(lexeme);
        if(scan.hasNextInt()) {
            listExpression.add(new NonTerminalExpressionNumber(scan.nextInt()));
        } else {
            return false;
        }
        return true;
    }

    public Integer calculate() {
        Context context = new Context();
        for (AbstractMathExpression expression : listExpression) {
            expression.interpret(context);
        }
        return context.popValue();
    }

}
