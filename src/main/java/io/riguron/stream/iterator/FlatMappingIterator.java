package io.riguron.stream.iterator;

import io.riguron.stream.Stream;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class FlatMappingIterator<T, R> implements Iterator<R> {

    private final Function<? super T, ? extends Stream<? extends R>> mapper;
    private final Iterator<T> delegate;
    private boolean hasNext;
    private Iterator<? extends R> current;
    private boolean firstMatch;

    public FlatMappingIterator(Function<? super T, ? extends Stream<? extends R>> mapper, Iterator<T> delegate) {
        this.mapper = mapper;
        this.delegate = delegate;
    }

    @Override
    public boolean hasNext() {
        tryInitialMatch();
        return hasNext;
    }

    @Override
    public R next() {
        tryInitialMatch();
        if (!hasNext) {
            throw new NoSuchElementException();
        }
        return nextElement();
    }

    private R nextElement() {
        R nextElement = current.next();
        if (!current.hasNext()) {
            advance();
            return nextElement;
        } else {
            hasNext = true;
        }
        return nextElement;
    }

    private void advance() {
        while (delegate.hasNext()) {
            Iterator<? extends R> iterator = mapper.apply(delegate.next()).iterator();
            if (iterator.hasNext()) {
                hasNext = true;
                current = iterator;
                return;
            }
        }
        hasNext = false;
    }


    private void tryInitialMatch() {
        if (!firstMatch) {
            advance();
            firstMatch = true;
        }
    }


}
