package com.epam.texthandling.logic;

import com.epam.texthandling.entity.Component;
import com.epam.texthandling.entity.Composite;
import com.epam.texthandling.entity.Lexeme;
import com.epam.texthandling.entity.LexemeType;
import com.epam.texthandling.exception.ExpressionCalculatorException;
import com.epam.texthandling.exception.UnsupportedComponentTypeException;
import com.epam.texthandling.logic.comparator.ChildComponentsComparator;
import com.epam.texthandling.parser.Parser;
import com.epam.texthandling.parser.ParserBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TextLogic {

    private static final String PARAGRAPHS_DELIMITER = "\n";
    private static final String LEXEMES_AND_SENTENCES_DELIMITER = " ";

    private static final Logger LOGGER = LogManager.getLogger(TextLogic.class);

    private final ExpressionCalculator expressionCalculator;

    public TextLogic() {
        this.expressionCalculator = new ExpressionCalculator();
    }

    public TextLogic(ExpressionCalculator expressionCalculator) {
        this.expressionCalculator = expressionCalculator;
    }

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

    public String parseTextToString(Composite text) {
        return parseComponentToString(text, PARAGRAPHS_DELIMITER);
    }

    private String parseComponentToString(Component text, String componentDelimiter) {
        StringBuilder resultString = new StringBuilder();
        if (text.getClass() == Composite.class) {
            Composite textComposite = (Composite) text;
            List<Component> textCompositeComponents = textComposite.getComponents();
            for (Component component : textCompositeComponents) {
                resultString.append(parseComponentToString(component, LEXEMES_AND_SENTENCES_DELIMITER));
                if (textCompositeComponents.indexOf(component) != (textCompositeComponents.size() - 1)) {
                    resultString.append(componentDelimiter);
                }
            }
        }
        else if (text.getClass() == Lexeme.class) {
            Lexeme textLexeme = (Lexeme) text;
            String lexemeText = textLexeme.getValue();
            resultString.append(lexemeText);
        }
        return resultString.toString();
    }

    public Composite calculateExpressionsInText(Composite text, Map<Character, Double> variables) throws UnsupportedComponentTypeException {
        List<Component> calculatedTextComponents = new ArrayList<>();
        List<Component> textComponents = text.getComponents();
        for (Component component : textComponents) {
            Component calculatedComponent;
            try {
                calculatedComponent = calculateExpressionsInComponent(component, variables);
            }
            catch (UnsupportedComponentTypeException | ExpressionCalculatorException e) {
                throw new UnsupportedComponentTypeException(e);
            }
            calculatedTextComponents.add(calculatedComponent);
        }
        return new Composite(calculatedTextComponents);
    }

    public Component calculateExpressionsInComponent(Component component, Map<Character, Double> variables) throws ExpressionCalculatorException, UnsupportedComponentTypeException {
        if(component.getClass() == Composite.class) {
            Composite composite = (Composite) component;
            List<Component> calculatedComponents = new ArrayList<>();
            List<Component> components = composite.getComponents();
            for(Component componentIterator : components){
                Component calculatedComponentIterator = calculateExpressionsInComponent(componentIterator, variables);
                calculatedComponents.add(calculatedComponentIterator);
            }
            return new Composite(calculatedComponents);
        }
        if (component.getClass() == Lexeme.class) {
            Lexeme lexeme = (Lexeme) component;
            String lexemeValue = lexeme.getValue();
            String calculatedValueString;
            if (lexeme.getLexemeType() == LexemeType.EXPRESSION) {
                double calculatedValue = expressionCalculator.calculate(lexemeValue, variables);
                calculatedValueString = Double.toString(calculatedValue);
            }
            else {
                calculatedValueString = lexemeValue;
            }
            return Lexeme.word(calculatedValueString);
        }
        UnsupportedComponentTypeException unsupportedComponentTypeException =
                new UnsupportedComponentTypeException("An unsupported Component was passed to method");
        LOGGER.throwing(unsupportedComponentTypeException);
        throw unsupportedComponentTypeException;
    }
}
