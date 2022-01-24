package com.epam.texthandling.parsing;

import com.epam.texthandling.entity.Composite;
import com.epam.texthandling.entity.Lexeme;
import com.epam.texthandling.parser.SentenceParser;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class SentenceParserTest {
    private static final String SENTENCE_TO_PARSE = "I want to calculate [56 4 -] this.";
    private static final Composite EXPECTED_SENTENCE_COMPOSITE = new Composite(Arrays.asList(Lexeme.word("I"),
            Lexeme.word("want"),
            Lexeme.word("to"),
            Lexeme.word("calculate"),
            Lexeme.expression("[56 4 -]"),
            Lexeme.word("this.")));

    private final SentenceParser sentenceParser = new SentenceParser();

    @Test
    public void testParseShouldParseASentenceWithBothExpressionsAndWords() {
        //given
        //when
        Composite actualSentenceComposite = sentenceParser.parse(SENTENCE_TO_PARSE);
        //then
        Assert.assertEquals(EXPECTED_SENTENCE_COMPOSITE, actualSentenceComposite);
    }
}
