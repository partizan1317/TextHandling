package com.epam.texthandling.parser;

import com.epam.texthandling.entity.Component;

public class SentenceParser extends AbstractParser {

    private static final String SENTENCE_DELIMITER_REGEX = " ";

    public SentenceParser() {
        super();
    }

    @Override
    public Component parse(String text) {
        return templateParse(text, SENTENCE_DELIMITER_REGEX);
    }
}
