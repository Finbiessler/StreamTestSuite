package io.riguron.stream.iterator;

import io.riguron.stream.Stream;
import io.riguron.stream.StreamFactory;
import org.testng.annotations.Test;

import java.util.NoSuchElementException;

import static org.testng.Assert.*;

public class FilteringIteratorTest {


    @Test (expectedExceptions = NoSuchElementException.class)
    public void testNext() {
        FilteringIterator<Integer> itr = (FilteringIterator<Integer>) emptyStream().filter(x -> x == 1).iterator();
        itr.next();
        itr.next();
    }

    private Stream<Integer> emptyStream(){
        return StreamFactory.of(1).filter(x->x==1);
    }

}