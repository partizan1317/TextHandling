package com.epam.texthandling.parsing;

import com.epam.texthandling.entity.Composite;
import com.epam.texthandling.entity.Lexeme;
import com.epam.texthandling.parser.ParagraphParser;
import com.epam.texthandling.parser.TextParser;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;

public class TextParserTest {
    private static final String FIRST_PARAGRAPH = "It was amazing. I learn a lot of new!";
    private static final String SECOND_PARAGRAPH = "I was in Paris last May. Paris is nice place.";
    private static final String THIRD_PARAGRAPH = "How do you think?";
    private static final String TEXT_TO_PARSE = FIRST_PARAGRAPH + "\n" + SECOND_PARAGRAPH + "\n" + THIRD_PARAGRAPH;
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
    private static final Composite FOURTH_SENTENCE_COMPOSITE = new Composite(Arrays.asList(Lexeme.word("Paris"),
            Lexeme.word("is"),
            Lexeme.word("nice"),
            Lexeme.word("place.")));
    private static final Composite FIFTH_SENTENCE_COMPOSITE = new Composite(Arrays.asList(Lexeme.word("How"),
            Lexeme.word("do"),
            Lexeme.word("you"),
            Lexeme.word("think?")));
    private static final Composite FIRST_PARAGRAPH_COMPOSITE = new Composite(Arrays.asList(FIRST_SENTENCE_COMPOSITE, SECOND_SENTENCE_COMPOSITE));
    private static final Composite SECOND_PARAGRAPH_COMPOSITE = new Composite(Arrays.asList(THIRD_SENTENCE_COMPOSITE, FOURTH_SENTENCE_COMPOSITE));
    private static final Composite THIRD_PARAGRAPH_COMPOSITE = new Composite(Collections.singletonList(FIFTH_SENTENCE_COMPOSITE));
    private static final Composite EXPECTED_TEXT_COMPOSITE = new Composite(Arrays.asList(FIRST_PARAGRAPH_COMPOSITE, SECOND_PARAGRAPH_COMPOSITE, THIRD_PARAGRAPH_COMPOSITE));

    @Test
    public void testParseShouldParseTextWithMultipleParagraphs() {
        //given
        ParagraphParser paragraphParser = Mockito.mock(ParagraphParser.class);
        Mockito.when(paragraphParser.parse(FIRST_PARAGRAPH)).thenReturn(FIRST_PARAGRAPH_COMPOSITE);
        Mockito.when(paragraphParser.parse(SECOND_PARAGRAPH)).thenReturn(SECOND_PARAGRAPH_COMPOSITE);
        Mockito.when(paragraphParser.parse(THIRD_PARAGRAPH)).thenReturn(THIRD_PARAGRAPH_COMPOSITE);
        TextParser textParser = new TextParser(paragraphParser);
        //when
        Composite actualTextComposite = textParser.parse(TEXT_TO_PARSE);
        //then
        Assert.assertEquals(EXPECTED_TEXT_COMPOSITE, actualTextComposite);
    }
}
