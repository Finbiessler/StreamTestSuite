package io.riguron.stream.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIterator<T> implements Iterator<T> {

    private T[] array;
    private int cursor;

    public ArrayIterator(T[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return cursor < array.length;
    }

    @Override
    public T next() {
        if (cursor == array.length) {
            throw new NoSuchElementException();
        }
        return array[cursor++];
    }
}
