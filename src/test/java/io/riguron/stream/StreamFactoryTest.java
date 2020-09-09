package io.riguron.stream;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Arrays;

import static org.testng.Assert.*;

public class StreamFactoryTest {

    @Test
    public void testOfIterable() {
        Assert.assertEquals(StreamFactory.of(Arrays.asList()), new EmptyStream<>());
    }

    @Test
    public void testIterate() {
       Stream<Integer> stream = StreamFactory.iterate(5, x -> x+1);
       Assert.assertEquals(stream.iterator().hasNext(), true);
       Assert.assertEquals(java.util.Optional.of(stream.iterator().next()), java.util.Optional.of(5));
    }
}