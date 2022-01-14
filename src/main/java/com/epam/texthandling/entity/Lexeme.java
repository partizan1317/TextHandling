package com.epam.texthandling.entity;

public class Lexeme implements Component {

    private String value;
    private LexemeType type;

    public Lexeme(String value, LexemeType type) {
        this.value = value;
        this.type = type;
    }

    public static Lexeme word(String value) {
        return new Lexeme(value, LexemeType.WORD);
    }

    private static Lexeme expression(String value) {
        return new Lexeme(value, LexemeType.EXPRESSION);
    }
}
