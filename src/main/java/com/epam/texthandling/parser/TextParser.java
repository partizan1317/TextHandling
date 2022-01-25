package com.epam.texthandling.parser;

import com.epam.texthandling.entity.Composite;

public class TextParser extends AbstractParser {

    private static final String TEXT_DELIMITER_REGEX = "\n";

    public TextParser(Parser successor) {
        super(successor);
    }

    public Composite parse(String text){
        return templateParse(text, TEXT_DELIMITER_REGEX);
    }
}
