package com.epam.texthandling.parser;

import com.epam.texthandling.entity.Component;
import com.epam.texthandling.entity.Composite;

public class ParagraphParser extends AbstractParser {

    private static final String PARAGRAPH_DELIMITER_REGEX = "(?<=[?!.])\\s";

    public ParagraphParser(Parser successor) {
        super(successor);
    }

    @Override
    public Composite parse(String text) {
        return templateParse(text, PARAGRAPH_DELIMITER_REGEX);
    }
}
