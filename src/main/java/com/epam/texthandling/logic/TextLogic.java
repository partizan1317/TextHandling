package com.epam.texthandling.logic;

import com.epam.texthandling.entity.Composite;
import com.epam.texthandling.parser.Parser;
import com.epam.texthandling.parser.ParserBuilder;

import java.util.Map;

public class TextLogic {

    public Composite parse(String text) {
        ParserBuilder parserBuilder = new ParserBuilder();
        Parser parser = parserBuilder.build();
        return parser.parse(text);
    }

    public Composite calculate(Composite text, Map<String, Double> parameters) {
        return null;
    }

    public Composite reverse(Composite text) {
        return null;
    }

    public String restore(Composite text){
        return null;
    }
}
