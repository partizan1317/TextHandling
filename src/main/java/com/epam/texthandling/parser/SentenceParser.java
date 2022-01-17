package com.epam.texthandling.parser;

import com.epam.texthandling.entity.Component;
import com.epam.texthandling.entity.Composite;
import com.epam.texthandling.entity.Lexeme;

public class SentenceParser extends AbstractParser {

    private static final String SENTENCE_DELIMITER_REGEX = "\\s(?=\\[)|(?<=[]])\\s|\\s(?=[a-zA-Z-])";

    public SentenceParser() {
        super();
    }

    @Override
    public Component parse(String text) {
        Composite composite = new Composite();
        String[] lexemes = text.split(SENTENCE_DELIMITER_REGEX);
        for (String lexeme : lexemes) {
            Lexeme convertedLexeme;
            if (lexeme.contains("[")){
                convertedLexeme = Lexeme.expression(lexeme);
            }
            else {
                convertedLexeme = Lexeme.word(lexeme);
            }
            composite.addChild(convertedLexeme);
        }
        return composite;
    }
}
