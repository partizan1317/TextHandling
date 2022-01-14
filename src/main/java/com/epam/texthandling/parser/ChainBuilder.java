package com.epam.texthandling.parser;

public class ChainBuilder {

    public Parser build() {
        return new TextParser(new ParagraphParser(null));
    }
}
