package com.epam.texthandling.logic;

import com.epam.texthandling.entity.Composite;
import com.epam.texthandling.entity.Lexeme;
import com.epam.texthandling.exception.ExpressionCalculatorException;
import com.epam.texthandling.exception.UnsupportedComponentTypeException;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.*;

public class TextLogicTest {

    private static final String FIRST_EXPRESSION = "[15 3 /]";
    private static final Double ANSWER_TO_FIRST_EXPRESSION = 5.0;
    private static final String ANSWER_TO_FIRST_EXPRESSION_STRING = Double.toString(ANSWER_TO_FIRST_EXPRESSION);
    private static final String SECOND_EXPRESSION = "[2 x * 5 /]";
    private static final Double ANSWER_TO_SECOND_EXPRESSION = 4.0;
    private static final String ANSWER_TO_SECOND_EXPRESSION_STRING = Double.toString(ANSWER_TO_SECOND_EXPRESSION);
    private static final Map<Character, Double> EXPRESSION_VARIABLES = new HashMap<>();

    static {
        EXPRESSION_VARIABLES.put('x', 10.0);
    }

    private static final Composite FIRST_SENTENCE_COMPOSITE = new Composite(Arrays.asList(Lexeme.word("I"),
            Lexeme.word("love"),
            Lexeme.word("programming.")));
    private static final Composite SECOND_SENTENCE_COMPOSITE = new Composite(Arrays.asList(Lexeme.word("I'm"),
            Lexeme.expression(FIRST_EXPRESSION),
            Lexeme.word("happy.")));
    private static final Composite THIRD_SENTENCE_COMPOSITE = new Composite(Arrays.asList(Lexeme.word("It's"),
            Lexeme.word("amazing!")));
    private static final Composite FOURTH_SENTENCE_COMPOSITE = new Composite(Arrays.asList(Lexeme.word("Is"),
            Lexeme.word("it"),
            Lexeme.expression(SECOND_EXPRESSION),
            Lexeme.word("enough?")));
    private static final Composite FIRST_PARAGRAPH_COMPOSITE = new Composite(Collections.singletonList(FIRST_SENTENCE_COMPOSITE));
    private static final Composite SECOND_PARAGRAPH_COMPOSITE = new Composite(Arrays.asList(SECOND_SENTENCE_COMPOSITE, THIRD_SENTENCE_COMPOSITE));
    private static final Composite THIRD_PARAGRAPH_COMPOSITE = new Composite(Collections.singletonList(FOURTH_SENTENCE_COMPOSITE));
    private static final Composite TEXT_COMPOSITE = new Composite(Arrays.asList(FIRST_PARAGRAPH_COMPOSITE, SECOND_PARAGRAPH_COMPOSITE, THIRD_PARAGRAPH_COMPOSITE));
    private static final String EXPECTED_TEXT_OF_COMPOSITE = "I love programming.\nI'm [15 3 /] happy. It's amazing!\nIs it [2 x * 5 /] enough?";
    private static final Composite EXPECTED_TEXT_COMPOSITE_WITH_SORTED_PARAGRAPHS = new Composite(Arrays.asList(FIRST_PARAGRAPH_COMPOSITE, THIRD_PARAGRAPH_COMPOSITE, SECOND_PARAGRAPH_COMPOSITE));

    private static final Composite SECOND_SENTENCE_WITH_CALCULATED_EXPRESSION = new Composite(Arrays.asList(Lexeme.word("I'm"),
            Lexeme.word(ANSWER_TO_FIRST_EXPRESSION_STRING),
            Lexeme.word("happy.")));
    private static final Composite FOURTH_SENTENCE_WITH_CALCULATED_EXPRESSION = new Composite(Arrays.asList(Lexeme.word("Is"),
            Lexeme.word("it"),
            Lexeme.word(ANSWER_TO_SECOND_EXPRESSION_STRING),
            Lexeme.word("enough?")));
    private static final Composite SECOND_PARAGRAPH_WITH_CALCULATED_EXPRESSION = new Composite(Arrays.asList(SECOND_SENTENCE_WITH_CALCULATED_EXPRESSION,THIRD_SENTENCE_COMPOSITE));
    private static final Composite THIRD_PARAGRAPH_WITH_CALCULATED_EXPRESSION = new Composite(Collections.singletonList(FOURTH_SENTENCE_WITH_CALCULATED_EXPRESSION));
    private static final Composite EXPECTED_CALCULATED_TEXT_COMPOSITE = new Composite(Arrays.asList(FIRST_PARAGRAPH_COMPOSITE, SECOND_PARAGRAPH_WITH_CALCULATED_EXPRESSION, THIRD_PARAGRAPH_WITH_CALCULATED_EXPRESSION));

    private final TextLogic textLogic = new TextLogic();

    @Test
    public void testParseTextToStringShouldReturnAStringValueEqualToTheNextBeforeParsing() {
        //given
        //when
        String actualTextOfComposite = textLogic.parseTextToString(TEXT_COMPOSITE);
        //then
        Assert.assertEquals(EXPECTED_TEXT_OF_COMPOSITE, actualTextOfComposite);
    }

    @Test
    public void testSortParagraphsBySentenceNumberShouldSortMultipleParagraphs() {
        //given
        //when
        Composite actualTextWithSortedParagraphs = textLogic.sortParagraphsBySentenceNumber(TEXT_COMPOSITE);
        //then
        Assert.assertEquals(EXPECTED_TEXT_COMPOSITE_WITH_SORTED_PARAGRAPHS, actualTextWithSortedParagraphs);
    }

    @Test
    public void testCalculateExpressionsInTextShouldCalculateIfThereAreMultipleExpressionsInDifferentParagraphs() throws ExpressionCalculatorException, UnsupportedComponentTypeException {
        //given
        ExpressionCalculator expressionCalculatorMock = Mockito.mock(ExpressionCalculator.class);
        Mockito.when(expressionCalculatorMock.calculate(FIRST_EXPRESSION,EXPRESSION_VARIABLES)).thenReturn(ANSWER_TO_FIRST_EXPRESSION);
        Mockito.when(expressionCalculatorMock.calculate(SECOND_EXPRESSION,EXPRESSION_VARIABLES)).thenReturn(ANSWER_TO_SECOND_EXPRESSION);
        TextLogic textLogic = new TextLogic(expressionCalculatorMock);
        //when
        Composite actualCalculatedTextComposite = textLogic.calculateExpressionsInText(TEXT_COMPOSITE, EXPRESSION_VARIABLES);
        //then
        Assert.assertEquals(EXPECTED_CALCULATED_TEXT_COMPOSITE, actualCalculatedTextComposite);
    }
}
