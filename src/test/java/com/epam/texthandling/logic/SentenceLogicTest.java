package com.epam.texthandling.logic;

import com.epam.texthandling.entity.Composite;
import com.epam.texthandling.entity.Lexeme;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class SentenceLogicTest {

    private static final Composite SENTENCE_FOR_SORT = new Composite(Arrays.asList(Lexeme.word("It"),
            Lexeme.word("was"),
            Lexeme.word("a"),
            Lexeme.word("difficult"),
            Lexeme.word("day")));

    private static final Composite EXPECTED_SORTED_SENTENCE = new Composite(Arrays.asList(Lexeme.word("a"),
            Lexeme.word("It"),
            Lexeme.word("was"),
            Lexeme.word("day"),
            Lexeme.word("difficult")));

    private final SentenceLogic sentenceLogic = new SentenceLogic();

    @Test
    public void testSortSentenceByWordsSizeShouldReturnSortedSentence() {
        //then
        //when
        Composite actualSortedSentence = sentenceLogic.sortWordsBySize(SENTENCE_FOR_SORT);
        //then
        Assert.assertEquals(EXPECTED_SORTED_SENTENCE, actualSortedSentence);
    }
}
