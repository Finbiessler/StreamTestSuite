package io.riguron.stream;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;
import java.util.function.*;

import static java.util.stream.Collectors.toList;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.testng.Assert.*;

public class ElementStreamTest {
    @Test
    public void testFilterNot() {
        List<Integer> expectedOutput = Arrays.asList(1,2,3);
        List<Integer> input = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        assertEquals(expectedOutput, StreamFactory.of(input).filterNot(x -> x > 3).collect(toList()));
    }

    @Test
    public void testTakeWhile() {
        assertEquals(Arrays.asList(1,2,3,4,5), StreamFactory.of(1,2,3,4,5,6,7,8,9,10).takeWhile(x -> x < 6).collect(toList()));
    }

    @Test
    public void testDropWhile() {
        assertEquals(Arrays.asList(6,7,8,9,10), StreamFactory.of(1,2,3,4,5,6,7,8,9,10).dropWhile(x -> x < 6).collect(toList()));
    }

    @Test
    public void testDistinct() {
        assertEquals(Arrays.asList(1,2,3,4), StreamFactory.of(1,1,1,2,2,3,3,3,4).distinct().collect(toList()));
    }

    @Test
    public void testApplyAndMap() {
        UnaryOperator<Stream<Integer>> add1 = (Stream<Integer> x) -> {return x.map(y -> y+1);};
        assertEquals(StreamFactory.of(0,1,2,3).apply(add1).collect(toList()), Arrays.asList(1,2,3,4));
    }

    @Test
    public void testForEach() { //Here we got inspired by the available test suite on github
        target tar = mock(target.class);
        StreamFactory.of(9,8,7,6,5).forEach(tar::peekIt);
        for(int i = 9; i > 4; i--){
            verify(tar).peekIt(eq(i));
        }
    }

    @Test
    public void testSorted() {
        Assert.assertEquals(StreamFactory.of(5,6,2,1,3,4).sorted().collect(toList()), Arrays.asList(1,2,3,4,5,6));
    }

    @Test
    public void testPeek() { //Here we got inspired by the available test suite on github
        target tar = mock(target.class);

        StreamFactory.of(9,8,7,6,5).peek(tar::peekIt).count();
        for(int i = 9; i > 4; i--) verify(tar).peekIt(eq(i));
    }

    @Test
    public void testLimit() {
        assertEquals(StreamFactory.of(1,2,3,4,5).limit(3).collect(toList()), Arrays.asList(1,2,3));
    }

    @Test
    public void testTestSorted(){
        assertEquals(StreamFactory.of(3,4,1,5,2,6).sorted(Comparator.naturalOrder()).collect(toList()), Arrays.asList(1,2,3,4,5,6));

    }

    @Test
    public void testSkip() {
        Assert.assertEquals(StreamFactory.of(1,2,3,4,5).skip(3).collect(toList()), Arrays.asList(4,5));
    }

    @Test
    public void testMin() {
        Assert.assertEquals(StreamFactory.of(3,2,4,1,4,4).min(Comparator.naturalOrder()), Optional.of(1));
        Assert.assertEquals(emptyStream().min(Integer::compareTo), Optional.empty());
    }

    @Test
    public void testMax() {
        Assert.assertEquals(StreamFactory.of(3,2,4,1,4,4,5).max(Comparator.naturalOrder()), Optional.of(5));
    }

    private Stream<Integer> emptyStream(){
        return StreamFactory.of(4).filter(x->x==5);
    }

    @Test
    public void testAnyMatch() {
        Assert.assertFalse(StreamFactory.of(1,2,3,4).anyMatch(x -> x == 9));
        Assert.assertTrue(StreamFactory.of(1,2,3,4).anyMatch(x -> x == 4));
    }

    @Test
    public void testAllMatch() {
        Assert.assertFalse(StreamFactory.of(1,2,3,4).allMatch(x -> x == 9));
        Assert.assertTrue(StreamFactory.of(9,9,9,9).allMatch(x -> x == 9));

    }

    @Test
    public void testNoneMatch() {
        Assert.assertTrue(StreamFactory.of(1,2,3,4).noneMatch(x -> x == 9));
        Assert.assertFalse(StreamFactory.of(1,2,3,9).noneMatch(x -> x == 9));
    }

    @Test
    public void testFindAny() {
        Assert.assertEquals(StreamFactory.of(4,2,3,4).findAny(), Optional.of(4));
        Assert.assertEquals(StreamFactory.empty().findAny(), Optional.empty());
    }

    @Test
    public void testToArray() {
        int results[] = {1,2,3,4};
        Assert.assertEquals(StreamFactory.of(1,2,3,4).toArray(), results);
    }

    @Test
    public void testTestToArray() {
        int results[] = {1,2,3,4};
        Assert.assertEquals(StreamFactory.of(1,2,3,4).toArray(Integer[]::new), results);
    }

    @Test
    public void testFlatMap() {
        List<Integer> arr = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Assert.assertEquals(StreamFactory.of(Arrays.asList(1,2,3),Arrays.asList(4,5,6), Arrays.asList(7,8,9,10)).flatMap(StreamFactory::of).collect(toList()), arr);
    }

    @Test
    public void testReduceEmptyStream() {
        Integer identity = 0;
        assertEquals(Optional.ofNullable(StreamFactory.of(1,2,3).reduce(identity, Integer::sum)), Optional.of(6));
        assertEquals(Optional.ofNullable(StreamFactory.of(1,2,3).dropWhile(x -> x < 4).reduce(identity, Integer::sum)), Optional.of(identity));
    }

    @Test
    public void testTestReduce() {
        assertEquals(StreamFactory.of(1,2,3).reduce(Integer::sum), Optional.of(6));
        assertEquals(StreamFactory.of(1,2,3).dropWhile(x -> x < 4).reduce(Integer::sum), Optional.empty());
    }

    @Test
    public void testTestReduce1() {
        assertEquals(Optional.ofNullable(StreamFactory.of(1, 2, 3).dropWhile(x -> x < 4).reduce(0, (Integer a, Integer b) -> a + b, Integer::sum)), Optional.of(0));
        assertEquals(Optional.ofNullable(StreamFactory.of(1, 2, 3).reduce(0, (Integer a, Integer b) -> a + b, Integer::sum)), Optional.of(6));
        assertEquals(Optional.ofNullable(StreamFactory.of(1, 2, 3).reduce(3, (Integer a, Integer b) -> a + b, Integer::sum)), Optional.of(15));

    }

    @Test
    public void testCollect() {
        Supplier<Integer> integerSupplier = () -> 1;
        BiConsumer<Integer, Integer> integerBiConsumer = (x,y) -> System.out.println(x+y);
        assertEquals(Optional.ofNullable(StreamFactory.of(1, 2, 3).collect(integerSupplier, integerBiConsumer, integerBiConsumer)), Optional.of(1));
    }

    @Test
    public void testFilterNulls(){
        assertEquals(StreamFactory.of(1,2,null,3,null,null,4,5,null).filterNulls().collect(toList()), Arrays.asList(1,2,3,4,5));
    }


    interface target{
        void peekIt(Integer a);
    }
}