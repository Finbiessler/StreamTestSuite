package io.riguron.stream.iterator;

import lombok.RequiredArgsConstructor;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

@RequiredArgsConstructor
public class TakeWhileIterator<T> implements Iterator<T> {

    private final Iterator<T> delegate;
    private final Predicate<T> predicate;
    private boolean firstAdvance;
    private boolean hasNext;
    private T nextElement;

    @Override
    public boolean hasNext() {
        initialAdvance();
        return hasNext;
    }

    @Override
    public T next() {
        initialAdvance();
        if (!hasNext) {
            throw new NoSuchElementException();
        }
        return nextMatch();
    }

    private T nextMatch() {
        T oldMatch = nextElement;
        if (delegate.hasNext()) {
            T next = delegate.next();
            if (predicate.test(next)) {
                hasNext = true;
                nextElement = next;
            } else {
                hasNext = false;
            }
        } else {
            hasNext = false;
        }
        return oldMatch;
    }


    private void initialAdvance() {
        if (!firstAdvance) {
            nextMatch();
            firstAdvance = true;
        }
    }
}
