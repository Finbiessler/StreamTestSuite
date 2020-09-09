package io.riguron.stream.iterator;

import io.riguron.stream.Stream;
import io.riguron.stream.StreamFactory;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Function;

import static org.mockito.Mockito.mock;
import static org.testng.Assert.*;

public class FlatMappingIteratorTest {


    @Test (expectedExceptions = NoSuchElementException.class)
    public void testNext() {
        FlatMappingIterator<Integer, Integer> itr = (FlatMappingIterator<Integer, Integer>) emptyStream().flatMap(StreamFactory::of).iterator();
        itr.next();
        itr.next();
        itr.next();
    }

    @Test
    public void testHasNext(){
        Iterator<Integer> iterator = mock(Iterator.class);
        Function<Integer, Stream<Integer>> functionStream = mock(Function.class);

        FlatMappingIterator<Integer, Integer> itr = new FlatMappingIterator<>(functionStream,iterator);
        assertEquals(itr.hasNext(), false);
    }

    private Stream<Integer> emptyStream(){
        return StreamFactory.of(1,1).filter(x->x==1);
    }
}