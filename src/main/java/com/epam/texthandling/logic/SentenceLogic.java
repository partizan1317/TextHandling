package com.epam.texthandling.logic;

import com.epam.texthandling.entity.Component;
import com.epam.texthandling.entity.Composite;
import com.epam.texthandling.entity.Lexeme;

import java.util.ArrayList;
import java.util.List;

public class SentenceLogic {

    public Composite sortWordsBySize(Composite sentence) {
        List<Lexeme> lexemes = new ArrayList<>();
        for (Component component : sentence.getComponents()) {
            lexemes.add((Lexeme) component);
        }

        throw new UnsupportedOperationException();
    }
}
