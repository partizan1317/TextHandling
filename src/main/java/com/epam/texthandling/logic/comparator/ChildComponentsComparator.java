package com.epam.texthandling.logic.comparator;

import com.epam.texthandling.entity.Composite;

import java.util.Comparator;

public class ChildComponentsComparator implements Comparator<Composite> {
    @Override
    public int compare(Composite firstComposite, Composite secondComposite) {
        int firstCompositeComponentsSize = firstComposite.getComponentsSize();
        int secondCompositeComponentsSize = secondComposite.getComponentsSize();
        return firstCompositeComponentsSize - secondCompositeComponentsSize;
    }
}
