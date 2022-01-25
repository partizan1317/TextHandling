package com.epam.texthandling.logic;

import java.util.ArrayDeque;
import java.util.Deque;

public class Context {

    private final Deque<Double> stack = new ArrayDeque<>();

    public Double popValue() {
        return stack.pop();
    }

    public void pushValue(Double value) {
        stack.push(value);
    }
}
