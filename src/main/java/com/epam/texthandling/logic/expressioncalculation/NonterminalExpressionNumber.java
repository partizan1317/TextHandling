package com.epam.texthandling.logic.expressioncalculation;

import com.epam.texthandling.logic.expressioncalculation.AbstractMathExpression;
import com.epam.texthandling.logic.expressioncalculation.Context;

public class NonterminalExpressionNumber extends AbstractMathExpression {

    private final int number;

    public NonterminalExpressionNumber(int number) {
        this.number = number;
    }

    @Override
    public void interpret(Context context) {
        context.pushValue(number);
    }

}
