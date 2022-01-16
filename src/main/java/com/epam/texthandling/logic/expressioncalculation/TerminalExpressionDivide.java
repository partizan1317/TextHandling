package com.epam.texthandling.logic.expressioncalculation;

import com.epam.texthandling.logic.expressioncalculation.AbstractMathExpression;
import com.epam.texthandling.logic.expressioncalculation.Context;

public class TerminalExpressionDivide extends AbstractMathExpression {

    @Override
    public void interpret(Context context) {
        context.pushValue(context.popValue() / context.popValue());
    }
}
