package ua.edu.ucu;

import org.junit.Before;
import org.junit.Test;
import ua.edu.ucu.stream.AsIntStream;
import ua.edu.ucu.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class AsIntStreamTest {
    private IntStream emptyStream;
    private IntStream exampleStream;

    @Before
    public void init() {
        int[] intArr = {};
        int[] streamArr = {1, 2, 3, 4, 5};
        emptyStream = AsIntStream.of(intArr);
        exampleStream = AsIntStream.of(streamArr);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyMaxStream() {
        int result = emptyStream.max();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyMinStream() {
        int result = emptyStream.min();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyReduceStream() {
        int result = emptyStream.reduce(0, Integer::sum);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptySumStream() {
        int result = emptyStream.sum();
    }

    @Test(expected = IllegalStateException.class)
    public void testCallAfterTerminating() {
        exampleStream.min();
        exampleStream.max();
    }

    @Test
    public void testStreamCount() {
        long expectedResult = 5;
        long result = exampleStream.count();
        assertEquals(expectedResult, result);
    }

}
