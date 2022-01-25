package com.epam.texthandling.parser;

public class ParserBuilder {

    public Parser build() {
        return new TextParser(new ParagraphParser(new SentenceParser()));
    }
}
