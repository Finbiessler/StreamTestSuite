package io.riguron.stream.iterator;

import java.util.Iterator;
import java.util.function.Consumer;

public class PeekingIterator<T> implements Iterator<T> {

    private Iterator<T> iterator;
    private Consumer<? super T> consumer;

    public PeekingIterator(Iterator<T> iterator, Consumer<? super T> consumer) {
        this.iterator = iterator;
        this.consumer = consumer;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public T next() {
        T item = iterator.next();
        consumer.accept(item);
        return item;
    }
}
