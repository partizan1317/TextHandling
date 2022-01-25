package com.epam.texthandling.logic.comparator;

import com.epam.texthandling.entity.Lexeme;

import java.util.Comparator;

public class LexemesComparator implements Comparator<Lexeme> {
    @Override
    public int compare(Lexeme firstLexeme, Lexeme secondLexeme) {
        int firstLexemeSize = firstLexeme.getValueSize();
        int secondLexemeSize = secondLexeme.getValueSize();
        return firstLexemeSize - secondLexemeSize;
    }
}
