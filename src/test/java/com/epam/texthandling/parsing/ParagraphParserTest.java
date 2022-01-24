package com.epam.texthandling.parsing;

import com.epam.texthandling.entity.Composite;
import com.epam.texthandling.entity.Lexeme;
import com.epam.texthandling.parser.ParagraphParser;
import com.epam.texthandling.parser.SentenceParser;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;

public class ParagraphParserTest {
    private static final String FIRST_SENTENCE = "I learn a lot of new!";
    private static final String SECOND_SENTENCE = "I was in Paris last May.";
    private static final String THIRD_SENTENCE = "How do you think?";
    private static final String PARAGRAPH_TO_PARSE = FIRST_SENTENCE + " " + SECOND_SENTENCE + " " + THIRD_SENTENCE;
    private static final Composite FIRST_SENTENCE_COMPOSITE = new Composite(Arrays.asList(Lexeme.word("It"),
            Lexeme.word("was"),
            Lexeme.word("amazing.")));
    private static final Composite SECOND_SENTENCE_COMPOSITE = new Composite(Arrays.asList(Lexeme.word("I"),
            Lexeme.word("learn"),
            Lexeme.word("a"),
            Lexeme.word("lot"),
            Lexeme.word("of"),
            Lexeme.word("new!")));
    private static final Composite THIRD_SENTENCE_COMPOSITE = new Composite(Arrays.asList(Lexeme.word("I"),
            Lexeme.word("was"),
            Lexeme.word("in"),
            Lexeme.word("Paris"),
            Lexeme.word("last"),
            Lexeme.word("May.")));
    private static final Composite EXPECTED_PARAGRAPH_COMPOSITE = new Composite(Arrays.asList(FIRST_SENTENCE_COMPOSITE, SECOND_SENTENCE_COMPOSITE, THIRD_SENTENCE_COMPOSITE));

    @Test
    public void testParseShouldParseTextWithMultipleParagraphs() {
        //given
        SentenceParser sentenceParser = Mockito.mock(SentenceParser.class);
        Mockito.when(sentenceParser.parse(FIRST_SENTENCE)).thenReturn(FIRST_SENTENCE_COMPOSITE);
        Mockito.when(sentenceParser.parse(SECOND_SENTENCE)).thenReturn(SECOND_SENTENCE_COMPOSITE);
        Mockito.when(sentenceParser.parse(THIRD_SENTENCE)).thenReturn(THIRD_SENTENCE_COMPOSITE);
        ParagraphParser paragraphParser = new ParagraphParser(sentenceParser);
        //when
        Composite actualTextComposite = paragraphParser.parse(PARAGRAPH_TO_PARSE);
        //then
        Assert.assertEquals(EXPECTED_PARAGRAPH_COMPOSITE, actualTextComposite);
    }
}
