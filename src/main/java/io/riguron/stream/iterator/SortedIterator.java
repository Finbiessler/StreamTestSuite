package io.riguron.stream.iterator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class SortedIterator<T> implements Iterator<T> {

    private Iterator<T> original;
    private Iterator<T> sorted;
    private Comparator<? super T> comparator;

    private boolean sortPerformed;

    public SortedIterator(Iterator<T> original, Comparator<? super T> comparator) {
        this.comparator = comparator;
        this.original = original;
    }


    @Override
    public boolean hasNext() {
        return iterator().hasNext();
    }

    @Override
    public T next() {
        return iterator().next();
    }

    private Iterator<T> iterator() {
        if (!sortPerformed) {
            List<T> list = new ArrayList<>();
            while (original.hasNext()) {
                list.add(original.next());
            }
            list.sort(comparator);
            this.sorted = list.iterator();
            this.sortPerformed = true;
        }
        return sorted;
    }
}
