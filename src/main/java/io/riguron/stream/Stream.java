package io.riguron.stream;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * Simplified version of Java Stream.
 *
 * @param <T> type of elements in stream
 * @see java.util.stream.Stream for the documentation
 */

public interface Stream<T> {

    Iterator<T> iterator();

    Stream<T> filter(Predicate<? super T> predicate);

    Stream<T> filterNot(Predicate<? super T> predicate);

    Stream<T> takeWhile(Predicate<T> predicate);

    Stream<T> dropWhile(Predicate<T> predicate);

    <R> Stream<R> map(Function<? super T, ? extends R> mapper);

    <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper);

    Stream<T> distinct();

    Stream<T> apply(UnaryOperator<Stream<T>> op);

    default Stream<T> filterNulls() {
        return filter(Objects::nonNull);
    }

    Stream<T> sorted();

    Stream<T> sorted(Comparator<? super T> comparator);

    Stream<T> peek(Consumer<? super T> action);

    Stream<T> limit(long maxSize);

    Stream<T> skip(long n);

    void forEach(Consumer<? super T> action);

    Object[] toArray();

    T[] toArray(IntFunction<T[]> generator);

    T reduce(T identity, BinaryOperator<T> accumulator);

    Optional<T> reduce(BinaryOperator<T> accumulator);

    <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner);

    <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner);

    <R, A> R collect(Collector<? super T, A, R> collector);

    Optional<T> min(Comparator<? super T> comparator);

    Optional<T> max(Comparator<? super T> comparator);

    long count();

    boolean anyMatch(Predicate<? super T> predicate);

    boolean allMatch(Predicate<? super T> predicate);

    boolean noneMatch(Predicate<? super T> predicate);

    Optional<T> findAny();


}
