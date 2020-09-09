package io.riguron.stream.iterator;

import lombok.RequiredArgsConstructor;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

@RequiredArgsConstructor
public class DropWhileIterator<T> implements Iterator<T> {

    private final Iterator<T> delegate;
    private final Predicate<T> predicate;
    private boolean firstAdvance;
    private T initial;
    private boolean beginning;


    @Override
    public boolean hasNext() {
        initialAdvance();
        return delegate.hasNext();
    }

    @Override
    public T next() {
        initialAdvance();

        if (!delegate.hasNext()) {
            throw new NoSuchElementException();
        }

        if (beginning) {

            beginning = false;
            return initial;
        } else {
            return delegate.next();
        }

    }

    private void initialAdvance() {
        if (!firstAdvance) {
            while (delegate.hasNext()) {
                T next = delegate.next();
                if (!predicate.test(next)) {
                    initial = next;
                    beginning = true;
                    break;
                }
            }
            firstAdvance = true;
        }
    }
}
