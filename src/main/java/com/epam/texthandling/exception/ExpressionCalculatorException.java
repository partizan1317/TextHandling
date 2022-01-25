package com.epam.texthandling.exception;

public class ExpressionCalculatorException extends Exception {
    public ExpressionCalculatorException() {
    }

    public ExpressionCalculatorException(String message) {
        super(message);
    }

    public ExpressionCalculatorException(Throwable cause){
        super(cause);
    }

    public ExpressionCalculatorException(String message, Throwable cause) {
        super(message, cause);
    }
}
