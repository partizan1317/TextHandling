package com.epam.texthandling.logic;

public class NonTerminalExpressionNumber extends AbstractMathExpression {

    private final double number;

    public NonTerminalExpressionNumber(double number) {
        this.number = number;
    }

    @Override
    public void interpret(Context context) {
        context.pushValue(number);
    }

}
