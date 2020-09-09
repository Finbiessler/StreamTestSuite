package io.riguron.stream.comparator;

import java.util.Comparator;

public class InverseComparator<T> implements Comparator<T> {

    private Comparator<? super T> comparator;

    public InverseComparator(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public int compare(T o1, T o2) {
        return comparator.compare(o1, o2) * -1;
    }
}
