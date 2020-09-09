package io.riguron.stream.comparator;

import java.util.Comparator;

@SuppressWarnings("unchecked")
public class ArbitraryTypeComparator<T> implements Comparator<T> {

    @Override
    public int compare(T o1, T o2) {
        if (!isComparable(o1)) {
            throw new IllegalArgumentException("Non-comparable stream");
        }
        return ((Comparable<T>) o1).compareTo(o2);
    }

    private boolean isComparable(T object) {
        return Comparable.class.isAssignableFrom(object.getClass());
    }
}
