package io.riguron.stream.matcher;

import java.util.Iterator;
import java.util.function.Predicate;

public class AnyMatch<T> extends ListMatch<T> {

    public AnyMatch(Iterator<T> iterator, Predicate<? super T> match) {
        super(iterator, match, new Matching() {
            @Override
            public boolean inverseMatchingResult() {
                return false;
            }

            @Override
            public boolean whenMatches() {
                return true;
            }
        });
    }


}
