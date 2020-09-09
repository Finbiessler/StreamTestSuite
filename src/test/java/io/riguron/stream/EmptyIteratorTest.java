package io.riguron.stream;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EmptyIteratorTest {
    @Test (expectedExceptions = NoSuchElementException.class)
    public void TestEmptyIteratorNext(){
        returnEmptyIterator().next();
    }
    private Iterator<Integer> returnEmptyIterator(){
        return new EmptyStream<Integer>().iterator();
    }
}
