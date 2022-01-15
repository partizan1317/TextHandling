package com.epam.texthandling.parser;

import com.epam.texthandling.entity.Component;
import com.epam.texthandling.entity.Composite;

public class ParagraphParser extends AbstractParser {

    public ParagraphParser(Parser successor) {
        super(successor);
    }

    @Override
    public Component parse(String text) {
        Composite composite = new Composite();
        String[] parts = text.split("\n");
        for (String part : parts) {
            Component paragraph = getSuccessor().parse(part);
            composite.addChild(paragraph);
        }
        return composite;
    }
}
