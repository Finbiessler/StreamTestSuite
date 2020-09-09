package io.riguron.stream.comparator;

import io.riguron.stream.StreamFactory;
import org.testng.annotations.Test;

import java.util.NoSuchElementException;
import java.util.function.Function;

import static org.testng.Assert.*;

public class ArbitraryTypeComparatorTest {

    @Test (expectedExceptions = IllegalArgumentException.class)
    public void testCompare() {
        person a = new person(12, "fin");

        ArbitraryTypeComparator<person> comp = new ArbitraryTypeComparator<>();
        comp.compare(a,a);
    }

    public class person{
        int age;
        String name;

        public person(int age, String name){
            this.age = age;
            this.name = name;
        }
    }
}