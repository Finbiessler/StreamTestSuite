package io.riguron.stream;

import io.riguron.stream.iterator.EmptyIterator;
import lombok.EqualsAndHashCode;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;
import java.util.function.*;
import java.util.stream.Collector;

@EqualsAndHashCode
public class EmptyStream<T> implements Stream<T> {
    EmptyStream() {
    }

    @Override
    public Iterator<T> iterator() {
        return new EmptyIterator<>();
    }

    @Override
    public Stream<T> filter(Predicate<? super T> predicate) {
        return this;
    }

    @Override
    public Stream<T> filterNot(Predicate<? super T> predicate) {
        return this;
    }

    @Override
    public Stream<T> takeWhile(Predicate<T> predicate) {
        return this;
    }

    @Override
    public Stream<T> dropWhile(Predicate<T> predicate) {
        return this;
    }

    @Override
    public <R> Stream<R> map(Function<? super T, ? extends R> mapper) {
        return new EmptyStream<>();
    }

    @Override
    public <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper) {
        return new EmptyStream<>();
    }

    @Override
    public Stream<T> distinct() {
        return this;
    }

    @Override
    public Stream<T> apply(UnaryOperator<Stream<T>> op) {
        return op.apply(this);
    }

    @Override
    public Stream<T> sorted() {
        return this;
    }

    @Override
    public Stream<T> sorted(Comparator<? super T> comparator) {
        return this;
    }

    @Override
    public Stream<T> peek(Consumer<? super T> action) {
        return this;
    }

    @Override
    public Stream<T> limit(long maxSize) {
        return this;
    }

    @Override
    public Stream<T> skip(long n) {
        return this;
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        iterator().forEachRemaining(action);
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public T[] toArray(IntFunction<T[]> generator) {
        return generator.apply(0);
    }

    @Override
    public T reduce(T identity, BinaryOperator<T> accumulator) {
        return identity;
    }

    @Override
    public Optional<T> reduce(BinaryOperator<T> accumulator) {
        return Optional.empty();
    }

    @Override
    public <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner) {
        return identity;
    }

    @Override
    public <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner) {
        return supplier.get();
    }

    @Override
    public <R, A> R collect(Collector<? super T, A, R> collector) {
        return collector.finisher().apply(collector.supplier().get());
    }

    @Override
    public Optional<T> min(Comparator<? super T> comparator) {
        return Optional.empty();
    }

    @Override
    public Optional<T> max(Comparator<? super T> comparator) {
        return Optional.empty();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public boolean anyMatch(Predicate<? super T> predicate) {
        return false;
    }

    @Override
    public boolean allMatch(Predicate<? super T> predicate) {
        return true;
    }

    @Override
    public boolean noneMatch(Predicate<? super T> predicate) {
        return true;
    }

    @Override
    public Optional<T> findAny() {
        return Optional.empty();
    }

}
