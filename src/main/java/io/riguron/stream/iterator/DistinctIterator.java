package io.riguron.stream.iterator;


import lombok.experimental.Delegate;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class DistinctIterator<T> implements Iterator<T> {

    private Iterator<T> setIterator;
    private Iterator<T> original;
    private boolean distinctionPerformed;

    public DistinctIterator(Iterator<T> delegate) {
        this.original = delegate;
    }

    private Iterator<T> iterator() {
        if (!distinctionPerformed) {
            Set<T> distinctSet = new HashSet<>();
            while (original.hasNext()) {
                distinctSet.add(original.next());
            }
            this.setIterator = distinctSet.iterator();
            distinctionPerformed = true;
        }
        return setIterator;
    }

    @Override
    public boolean hasNext() {
        return iterator().hasNext();
    }

    @Override
    public T next() {
        return iterator().next();
    }
}
