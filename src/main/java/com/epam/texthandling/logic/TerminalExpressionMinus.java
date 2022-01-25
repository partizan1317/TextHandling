package com.epam.texthandling.logic;

public class TerminalExpressionMinus extends AbstractMathExpression {

    @Override
    public void interpret(Context context) {
        Double firstNumber = context.popValue();
        Double secondNumber = context.popValue();
        context.pushValue(-firstNumber + secondNumber);
    }
}
