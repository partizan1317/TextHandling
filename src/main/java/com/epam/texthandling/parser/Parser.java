package com.epam.texthandling.parser;

import com.epam.texthandling.entity.Component;

public interface Parser {

    Component parse(String text);
}
