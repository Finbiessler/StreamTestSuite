package io.riguron.stream.matcher;

import lombok.RequiredArgsConstructor;

import java.util.Iterator;
import java.util.function.Predicate;

@RequiredArgsConstructor
public class ListMatch<T> implements Match<T> {

    private final Iterator<T> iterator;
    private final Predicate<? super T> match;
    private final Matching matching;

    @Override
    public boolean evaluate() {
        final boolean whenAccepted = matching.whenMatches();
        while (iterator.hasNext()) {
            if (match.test(iterator.next()) ^ matching.inverseMatchingResult()) {
                return whenAccepted;
            }
        }
        return !whenAccepted;
    }
}
