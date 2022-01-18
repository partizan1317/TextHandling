package com.epam.texthandling.parser;

import com.epam.texthandling.entity.Composite;

public abstract class AbstractParser implements Parser {

    private final Parser successor;

    public AbstractParser() {
        successor = null;
    }

    public AbstractParser(Parser successor) {
        this.successor = successor;
    }

    protected Parser getSuccessor() {
        return successor;
    }

    protected Composite templateParse(String text, String delimiterRegex) {
        Composite composite = new Composite();
        String[] parts = text.split(delimiterRegex);
        for (String part : parts) {
            Composite paragraph = getSuccessor().parse(part);
            composite.addComponent(paragraph);
        }
        return composite;
    }

}
