package io.riguron.stream.matcher;

import java.util.Iterator;
import java.util.function.Predicate;

public class AllMatch<T> extends ListMatch<T> {

    public AllMatch(Iterator<T> iterator, Predicate<? super T> match) {
        super(iterator, match, new Matching() {
            @Override
            public boolean inverseMatchingResult() {
                return true;
            }

            @Override
            public boolean whenMatches() {
                return false;
            }
        });
    }

}
