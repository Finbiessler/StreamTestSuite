package io.riguron.stream;

import io.riguron.stream.comparator.ArbitraryTypeComparator;
import io.riguron.stream.comparator.Extreme;
import io.riguron.stream.comparator.InverseComparator;
import io.riguron.stream.iterator.*;
import io.riguron.stream.matcher.AllMatch;
import io.riguron.stream.matcher.AnyMatch;
import io.riguron.stream.matcher.Match;
import io.riguron.stream.matcher.NoneMatch;
import io.riguron.stream.operation.ElementCount;
import lombok.EqualsAndHashCode;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.*;
import java.util.stream.Collector;

import static java.util.stream.Collectors.toList;

public class ElementStream<T> implements Stream<T> {

    private Iterator<T> elementProvider;

    ElementStream(Iterator<T> elementProvider) {
        this.elementProvider = elementProvider;
    }

    @Override
    public Stream<T> filter(Predicate<? super T> predicate) {
        return new ElementStream<>(new FilteringIterator<>(elementProvider, predicate));
    }

    @Override
    public Stream<T> filterNot(Predicate<? super T> predicate) {
        return this.filter(predicate.negate());
    }

    @Override
    public Stream<T> takeWhile(Predicate<T> predicate) {
        return new ElementStream<>(new TakeWhileIterator<>(this.elementProvider, predicate));
    }

    @Override
    public Stream<T> dropWhile(Predicate<T> predicate) {
        return new ElementStream<>(new DropWhileIterator<>(this.elementProvider, predicate));
    }

    @Override
    public Stream<T> distinct() {
        return new ElementStream<>(new DistinctIterator<>(elementProvider));
    }

    @Override
    public Stream<T> apply(UnaryOperator<Stream<T>> op) {
        return op.apply(this);
    }

    @Override
    public <R> Stream<R> map(Function<? super T, ? extends R> mapper) {
        return new ElementStream<>(new MappingIterator<>(elementProvider, mapper));
    }


    @Override
    public void forEach(Consumer<? super T> action) {
        while (elementProvider.hasNext()) {
            action.accept(elementProvider.next());
        }
    }

    @Override
    public Stream<T> sorted() {
        return sorted(new ArbitraryTypeComparator<>());
    }

    @Override
    public Stream<T> peek(Consumer<? super T> action) {
        return new ElementStream<>(new PeekingIterator<>(this.elementProvider, action));
    }

    @Override
    public Stream<T> limit(long maxSize) {
        return new ElementStream<>(new LimitedIterator<>(this.elementProvider, maxSize));
    }

    @Override
    public Stream<T> sorted(Comparator<? super T> comparator) {
        return new ElementStream<>(new SortedIterator<>(this.elementProvider, comparator));
    }

    @Override
    public Stream<T> skip(long n) {
        return new ElementStream<>(new SkippingIterator<>(this.elementProvider, n));
    }

    @Override
    public Optional<T> min(Comparator<? super T> comparator) {
        return new Extreme<>(elementProvider, comparator).get();
    }

    @Override
    public Optional<T> max(Comparator<? super T> comparator) {
        return new Extreme<>(this.elementProvider, new InverseComparator<>(comparator)).get();
    }

    @Override
    public Iterator<T> iterator() {
        return this.elementProvider;
    }

    @Override
    public long count() {
        return new ElementCount(iterator()).evaluate();
    }

    @Override
    public boolean anyMatch(Predicate<? super T> predicate) {
        return evaluateMatch(AnyMatch::new, predicate);
    }

    @Override
    public boolean allMatch(Predicate<? super T> predicate) {
        return evaluateMatch(AllMatch::new, predicate);
    }

    @Override
    public boolean noneMatch(Predicate<? super T> predicate) {
        return evaluateMatch(NoneMatch::new, predicate);
    }

    private <R extends Match<T>> boolean evaluateMatch(BiFunction<Iterator<T>, Predicate<? super T>, R> producer, Predicate<? super T> predicate) {
        return producer.apply(this.elementProvider, predicate).evaluate();
    }

    @Override
    public Optional<T> findAny() {
        return elementProvider.hasNext() ? Optional.ofNullable(elementProvider.next()) : Optional.empty();
    }

    @Override
    public Object[] toArray() {
        return collect(toList()).toArray();
    }

    @Override
    public T[] toArray(IntFunction<T[]> generator) {
        List<T> list = collect(toList());
        T[] result = generator.apply(list.size());
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    @Override
    public <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper) {
        return new ElementStream<>(new FlatMappingIterator<>(mapper, elementProvider));
    }

    @Override
    public T reduce(T identity, BinaryOperator<T> accumulator) {
        if (!elementProvider.hasNext()) {
            return identity;
        } else {
            T initial = accumulator.apply(identity, elementProvider.next());
            while (elementProvider.hasNext()) {
                initial = accumulator.apply(initial, elementProvider.next());
            }
            return initial;
        }
    }

    @Override
    public Optional<T> reduce(BinaryOperator<T> accumulator) {
        if (!elementProvider.hasNext()) {
            return Optional.empty();
        } else {
            return Optional.of(this.reduce(elementProvider.next(), accumulator));
        }
    }

    @Override
    public <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner) {
        if (!elementProvider.hasNext()) {
            return identity;
        } else {
            U initial = accumulator.apply(identity, elementProvider.next());
            while (elementProvider.hasNext()) {
                U item = accumulator.apply(identity, elementProvider.next());
                initial = combiner.apply(item, initial);
            }
            return initial;
        }
    }


    @Override
    public <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner) {
        R collection = supplier.get();
        while (elementProvider.hasNext()) {
            T next = elementProvider.next();
            accumulator.accept(collection, next);
        }
        return collection;
    }

    @Override
    public <R, A> R collect(Collector<? super T, A, R> collector) {
        A container = collector.supplier().get();
        while (elementProvider.hasNext()) {
            T next = elementProvider.next();
            BiConsumer<A, ? super T> accumulator = collector.accumulator();
            accumulator.accept(container, next);
        }
        return collector.finisher().apply(container);
    }


}
