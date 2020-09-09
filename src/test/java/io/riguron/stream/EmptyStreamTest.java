package io.riguron.stream;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.*;
import java.util.stream.Collector;

import static java.util.stream.Collectors.toList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;

public class EmptyStreamTest {

    //Init mocks of parameters as private member variables
    private Predicate<Integer>  predicate  = mock(Predicate.class);
    private Function<Integer, Integer> function = mock(Function.class);
    private UnaryOperator<Stream<Integer>> uOperator = mock(UnaryOperator.class);
    private Comparator<Integer> comparator = mock(Comparator.class);
    private Consumer<Integer> consumer = mock(Consumer.class);
    private IntFunction<Integer> intFunction = mock(IntFunction.class);
    private BinaryOperator<Integer> bOperator = mock(BinaryOperator.class);
    private BiFunction<Integer, Integer, Integer> biFunction = mock(BiFunction.class);
    private BiConsumer<Integer, Integer> biConsumer = mock(BiConsumer.class);
    private Supplier<Integer> supplier = mock(Supplier.class);
    private Collector<Integer, Integer, Integer> collector = mock(Collector.class);
    private Function<Integer, Stream<Integer>> functionStream = mock(Function.class);

    @Test
    public void testIterator(){
        Assert.assertEquals(returnEmptyStream().iterator().hasNext(), false);
    }

    @Test
    public void testFilter() {
        Assert.assertEquals(returnEmptyStream().filter(predicate), returnEmptyStream());
    }

    @Test
    public void testFilterNot() {
        Assert.assertEquals(returnEmptyStream().filterNot(predicate), returnEmptyStream());
    }

    @Test
    public void testTakeWhile() {
        Assert.assertEquals(returnEmptyStream().takeWhile(predicate), returnEmptyStream());
    }

    @Test
    public void testDropWhile() {
        Assert.assertEquals(returnEmptyStream().dropWhile(predicate), returnEmptyStream());
    }

    @Test
    public void testMap() {
        Assert.assertEquals(returnEmptyStream().map(function), returnEmptyStream());
    }

    @Test
    public void testFlatMap() {
        Assert.assertEquals(returnEmptyStream().flatMap(functionStream), returnEmptyStream());
    }

    @Test
    public void testDistinct() {
        Assert.assertEquals(returnEmptyStream().distinct(), returnEmptyStream());
    }

    @Test
    public void testApply() {
        Assert.assertEquals(returnEmptyStream().apply(stream -> stream.apply(elem->elem)), returnEmptyStream());
    }

    @Test
    public void testSorted() {
        Assert.assertEquals(returnEmptyStream().sorted(), returnEmptyStream());
    }

    @Test
    public void testTestSorted() {
        Assert.assertEquals(returnEmptyStream().sorted(comparator), returnEmptyStream());
    }

    @Test
    public void testPeek() {

        Assert.assertEquals(returnEmptyStream().peek(consumer), returnEmptyStream());
    }

    @Test
    public void testLimit() {
        Assert.assertEquals((returnEmptyStream().limit(21)), returnEmptyStream());
    }

    @Test
    public void testSkip() {
        Assert.assertEquals(returnEmptyStream().skip(21), returnEmptyStream());
    }

    @Test
    public void testForEach() {
       returnEmptyStream().forEach(consumer);
       verifyZeroInteractions(consumer);
    }

    @Test
    public void testToArray() {
        Assert.assertEquals(returnEmptyStream().toArray().length, 0);
    }

    @Test
    public void testTestToArray() {
        Assert.assertEquals(returnEmptyStream().toArray(Integer[]::new).length, 0);
    }

    @Test
    public void testReduce() {
        Assert.assertEquals(returnEmptyStream().reduce(5, bOperator).intValue(), 5);
    }

    @Test
    public void testTestReduce() {
        Assert.assertEquals(returnEmptyStream().reduce(bOperator), Optional.empty());
    }

    @Test
    public void testTestReduce1() {
       Assert.assertEquals(returnEmptyStream().reduce(5, bOperator, bOperator).intValue(), 5);
    }

    @Test
    public void testCollect() {
        Assert.assertEquals(returnEmptyStream().collect(supplier, biConsumer, biConsumer), supplier.get());
    }

    @Test
    public void testTestCollect() {
        Assert.assertEquals(returnEmptyStream().collect(toList()).isEmpty(), true);
    }

    @Test
    public void testMin() {
        Assert.assertEquals(returnEmptyStream().min(comparator), Optional.empty());
    }

    @Test
    public void testMax() {
        Assert.assertEquals(returnEmptyStream().max(comparator), Optional.empty());
    }

    @Test
    public void testCount() {
        Assert.assertEquals(returnEmptyStream().count(), 0);
    }

    @Test
    public void testAnyMatch() {
        Assert.assertEquals(returnEmptyStream().anyMatch(predicate), false);
    }

    @Test
    public void testAllMatch() {
        Assert.assertEquals(returnEmptyStream().allMatch(predicate), true);
    }

    @Test
    public void testNoneMatch() {
        Assert.assertEquals(returnEmptyStream().noneMatch(predicate), true);
    }

    @Test
    public void testFindAny() {
        Assert.assertEquals(returnEmptyStream().findAny(), Optional.empty());
    }

    private EmptyStream<Integer> returnEmptyStream(){
        return new EmptyStream<Integer>();
    }



}