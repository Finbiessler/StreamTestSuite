package io.riguron.stream.iterator;

import java.util.Iterator;

public class SkippingIterator<T> implements Iterator<T> {

    private Iterator<T> iterator;
    private long toSkip;
    private boolean skipped;

    public SkippingIterator(Iterator<T> iterator, long toSkip) {
        this.iterator = iterator;
        this.toSkip = toSkip;
    }

    @Override
    public boolean hasNext() {
        trySkip();
        return iterator.hasNext();
    }

    @Override
    public T next() {
        trySkip();
        return iterator.next();
    }

    private void trySkip() {
        if (!skipped) {
            skipped = true;
            long skippedCount = Math.max(0, toSkip);
            while (skippedCount > 0 && hasNext()) {
                skippedCount--;
                iterator.next();
            }
        }
    }
}
