package com.epam.texthandling.entity;

import java.util.ArrayList;
import java.util.List;

public class Composite implements Component {

    private List<Component> children = new ArrayList<Component>();

    public void addChild(Component child) {
        children.add(child);
    }
}
