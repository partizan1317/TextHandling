package com.epam.texthandling.logic;

public class TerminalExpressionDivide extends AbstractMathExpression {

    @Override
    public void interpret(Context context) {
        Double secondNumber = context.popValue();
        Double firstNumber = context.popValue();
        context.pushValue(firstNumber / secondNumber);
    }
}
