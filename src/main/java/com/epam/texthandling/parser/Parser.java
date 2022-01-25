package com.epam.texthandling.parser;

import com.epam.texthandling.entity.Component;
import com.epam.texthandling.entity.Composite;

public interface Parser {

    Composite parse(String text);
}