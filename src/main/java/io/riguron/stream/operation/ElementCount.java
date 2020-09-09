package io.riguron.stream.operation;

import java.util.Iterator;

public class ElementCount {

    private Iterator<?> iterator;

    public ElementCount(Iterator<?> iterator) {
        this.iterator = iterator;
    }

    public int evaluate() {
        int total = 0;
        while (iterator.hasNext()) {
            total++;
            iterator.next();
        }
        return total;
    }
}
