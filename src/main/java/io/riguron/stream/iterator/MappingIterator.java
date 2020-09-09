package io.riguron.stream.iterator;

import java.util.Iterator;
import java.util.function.Function;

public class MappingIterator<S, T> implements Iterator<S> {

    private Iterator<T> delegate;
    private Function<? super T, ? extends S> mapper;

    public MappingIterator(Iterator<T> delegate, Function<? super T, ? extends S> mapper) {
        this.delegate = delegate;
        this.mapper = mapper;
    }

    @Override
    public boolean hasNext() {
        return delegate.hasNext();
    }

    @Override
    public S next() {
        return mapper.apply(delegate.next());
    }
}
