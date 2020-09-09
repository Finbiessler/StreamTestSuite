package io.riguron.stream.iterator;

import io.riguron.stream.Stream;
import io.riguron.stream.StreamFactory;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.testng.Assert.*;

public class DropWhileIteratorTest {

    @Test (expectedExceptions = NoSuchElementException.class)
    public void testNext() {
        emptyStream().dropWhile(x -> x ==1).iterator().next();
    }

    private Stream<Integer> emptyStream(){
        return StreamFactory.of(1).filter(x->x==1);
    }
}