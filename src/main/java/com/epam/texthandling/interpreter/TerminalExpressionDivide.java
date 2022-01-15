package com.epam.texthandling.interpreter;

public class TerminalExpressionDivide extends AbstractMathExpression {

    @Override
    public void interpret(Context context) {
        context.pushValue(context.popValue() / context.popValue());
    }
}
