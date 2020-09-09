package io.riguron.stream.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class FilteringIterator<T> implements Iterator<T> {

    private Iterator<T> iterator;
    private Predicate<? super T> filter;
    private boolean hasNext;
    private T nextElement;
    private boolean firstMatch;

    public FilteringIterator(Iterator<T> iterator, Predicate<? super T> filter) {
        this.iterator = iterator;
        this.filter = filter;
    }

    private T nextMatch() {
        T oldMatch = nextElement;
        while (iterator.hasNext()) {
            T o = iterator.next();
            if (filter.test(o)) {
                hasNext = true;
                nextElement = o;
                return oldMatch;
            }
        }
        hasNext = false;
        return oldMatch;
    }


    @Override
    public boolean hasNext() {
        tryInitialMatch();
        return hasNext;
    }

    @Override
    public T next() {
        tryInitialMatch();
        if (!hasNext) {
            throw new NoSuchElementException();
        }
        return nextMatch();
    }

    private void tryInitialMatch() {
        if (!firstMatch) {
            nextMatch();
            firstMatch = true;
        }
    }
}
