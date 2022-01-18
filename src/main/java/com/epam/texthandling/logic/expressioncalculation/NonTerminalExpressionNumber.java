package com.epam.texthandling.logic.expressioncalculation;

import com.epam.texthandling.logic.expressioncalculation.AbstractMathExpression;
import com.epam.texthandling.logic.expressioncalculation.Context;

public class NonTerminalExpressionNumber extends AbstractMathExpression {

    private final int number;

    public NonTerminalExpressionNumber(int number) {
        this.number = number;
    }

    @Override
    public void interpret(Context context) {
        context.pushValue(number);
    }

}
