# Stream
Implementation of simplified and modified version of Java Stream API with lazy evaluation.

[![Build Status](https://travis-ci.org/riguron/Stream.svg?branch=master)](https://travis-ci.org/riguron/Stream)
[![](https://jitpack.io/v/riguron/Stream.svg)](https://jitpack.io/#riguron/Stream)
[![codecov](https://codecov.io/gh/riguron/Stream/branch/master/graph/badge.svg)](https://codecov.io/gh/riguron/Stream)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=riguron_Stream&metric=alert_status)](https://sonarcloud.io/dashboard?id=riguron_Stream)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=riguron_Stream&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=riguron_Stream)

## Dependency

This project is distributed via JitPack. Register a JitPack repository at your pom.xml:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

And add the following dependency:

```xml
<dependency>
    <groupId>com.github.riguron</groupId>
    <artifactId>Stream</artifactId>
    <version>v1.0</version>
</dependency>
```

## Creating a stream

StreamFactory class is responsible for creating streams. 

A stream may be constructed from fixed list of elements:

```java
Stream<String> stream = StreamFactory.of("alpha", "bravo", "charlie", "delta", "echo");
```

Or from any Iterable implementation:

```java
List<String> list = Arrays.asList("alpha", "bravo", "charlie", "delta", "echo");
Stream<String> stream = StreamFactory.of(list);
```

Empty stream may be created as follows:

```java
Stream<String> stream = StreamFactory.empty();
```

Infinite streams are also supported:

```java
Stream<Integer> stream = StreamFactory.iterate(1, i -> i + 1).limit(5);
```

## Operations

### Operations inherited from Java 8 Stream interface

- Iterator
- Filter
- Map
- FlatMap
- Distinct
- Sorted
- Peek
- Limit
- Skip
- ForEach
- ToArray
- Reduce
- Collect
- Min
- Max
- Count
- AnyMatch
- AllMatch
- NoneMatch
- FindAny 

### Custom operations

```TakeWhile``` skips all elements after the first one that doesn't match a predicate:

```java
StreamFactory.of(1,2,3,4,5,6).takeWhile(x -> x <= 3)
       .collect(Collectors.toList()); // [1,2,3]
```

```DropWhile``` skips all elements before the first one that matches a predicate:

```java
 StreamFactory.of(6,5,4,3,2,1).dropWhile(x -> x > 3)
       .collect(Collectors.toList()); // [3,2,1]
```       

```FiterNot``` filters out the elements that match the predicate, inverse for ```Filter```:

```java
StreamFactory.of(1,2,3,4,5,6).filterNot(x -> x % 2 == 0)
        .collect(Collectors.toList()); // [1,3,5]
```

```FilterNulls``` filters out null items:

```java
StreamFactory.of(1, 2, null, 4, null, 6).filterNulls()
        .collect(Collectors.toList()); // [1, 2, 4, 6]
```

## Not supported operations

- Parallel
- MapToInt / Double / Long
- FlatMapToInt / Double / Long
- ForEachOrdered
