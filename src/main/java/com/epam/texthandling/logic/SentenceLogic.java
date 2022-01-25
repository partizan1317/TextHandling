package com.epam.texthandling.logic;

import com.epam.texthandling.entity.Component;
import com.epam.texthandling.entity.Composite;
import com.epam.texthandling.entity.Lexeme;
import com.epam.texthandling.logic.comparator.ChildComponentsComparator;
import com.epam.texthandling.logic.comparator.LexemesComparator;

import java.util.ArrayList;
import java.util.List;

public class SentenceLogic {

    public Composite sortWordsBySize(Composite sentence) {
        List<Lexeme> lexemes = new ArrayList<>();
        for (Component component : sentence.getComponents()) {
            lexemes.add((Lexeme) component);
        }
        lexemes.sort(new LexemesComparator());
        return new Composite(lexemes);
    }
}
