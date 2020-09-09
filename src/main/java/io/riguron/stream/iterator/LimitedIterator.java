package io.riguron.stream.iterator;

import java.util.Iterator;

public class LimitedIterator<T> implements Iterator<T> {

    private Iterator<T> delegate;
    private long limit;
    private long current;

    public LimitedIterator(Iterator<T> delegate, long limit) {
        this.delegate = delegate;
        this.limit = limit;
    }

    @Override
    public boolean hasNext() {
        return current < limit && delegate.hasNext();
    }

    @Override
    public T next() {
        T item = delegate.next();
        current++;
        return item;
    }
}
