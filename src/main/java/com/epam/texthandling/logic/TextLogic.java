package com.epam.texthandling.logic;

import com.epam.texthandling.entity.Component;
import com.epam.texthandling.entity.Composite;
import com.epam.texthandling.logic.comparator.ChildComponentsComparator;
import com.epam.texthandling.parser.Parser;
import com.epam.texthandling.parser.ParserBuilder;

import java.util.ArrayList;
import java.util.List;

public class TextLogic {

    public Composite parse(String text) {
        ParserBuilder parserBuilder = new ParserBuilder();
        Parser parser = parserBuilder.build();
        return parser.parse(text);
    }

    public Composite sortParagraphsBySentenceNumber(Composite text) {
        List<Composite> paragraphs = new ArrayList<>();
        for (Component component : text.getComponents()) {
            paragraphs.add((Composite) component);
        }
        paragraphs.sort(new ChildComponentsComparator());
        return new Composite(paragraphs);
    }
}
