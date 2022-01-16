package com.epam.texthandling.entity;

import java.util.ArrayList;
import java.util.List;

public class Composite implements Component {

    private final List<Component> children = new ArrayList<Component>();

    public void addChild(Component child) {
        children.add(child);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Composite composite = (Composite) o;
        return children.equals(composite.children);
    }

    @Override
    public int hashCode() {
        return children.hashCode();
    }

    @Override
    public String toString() {
        return "Composite{" +
                "components= " + children +
                "}";
    }
}
