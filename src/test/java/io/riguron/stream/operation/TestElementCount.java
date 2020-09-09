package io.riguron.stream.operation;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Arrays;
import static org.testng.Assert.*;

public class TestElementCount {

    @Test
    public void testWhenNotEmpty() {
        Assert.assertEquals(returnNoneEmptyElementCount().evaluate(), 4);
    }

    @Test
    public void testWhenEmpty(){
        assertEquals(returnEmptyElementCount().evaluate(), 0);
    }

    private ElementCount returnNoneEmptyElementCount(){
        return new ElementCount(Arrays.asList(1,2,3,4).iterator());
    }
    private ElementCount returnEmptyElementCount(){
        return new ElementCount(Arrays.asList().iterator());
    }
}