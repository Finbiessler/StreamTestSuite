package io.riguron.stream.comparator;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;

public class Extreme<T> {

    private Iterator<T> iterator;
    private Comparator<? super T> comparator;

    public Extreme(Iterator<T> iterator, Comparator<? super T> comparator) {
        this.iterator = iterator;
        this.comparator = comparator;
    }

    public Optional<T> get() {
        if (!iterator.hasNext()) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(findExtreme());
        }
    }

    private T findExtreme() {
        T extreme = iterator.next();
        while (iterator.hasNext()) {
            T next = iterator.next();
            if (comparator.compare(extreme, next) > 0) {
                extreme = next;
            }
        }
        return extreme;
    }


}
