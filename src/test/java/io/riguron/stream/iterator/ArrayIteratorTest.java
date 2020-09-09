package io.riguron.stream.iterator;

import io.riguron.stream.StreamFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static org.testng.Assert.*;

public class ArrayIteratorTest {

    @Test (expectedExceptions = NoSuchElementException.class)
    public void testNext() {
        ArrayIterator<Integer> iterator = (ArrayIterator<Integer>) StreamFactory.of(1).iterator();
        iterator.next();
        iterator.next();
    }
}