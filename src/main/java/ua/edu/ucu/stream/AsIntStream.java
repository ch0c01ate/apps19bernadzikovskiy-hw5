package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;
import ua.edu.ucu.iterators.BaseIterator;
import ua.edu.ucu.iterators.FilterIterator;
import ua.edu.ucu.iterators.FlatMapIterator;
import ua.edu.ucu.iterators.MapIterator;

import java.util.Iterator;

public class AsIntStream implements IntStream {

    Iterator<Integer> iterator;
    int size;
    boolean isTerminated;

    private AsIntStream() {
        this.isTerminated = false;
    }

    public AsIntStream(int... values) {
        this();
        size = values.length;
        iterator = new BaseIterator(values);
    }

    public static IntStream of(int... values) {
        return new AsIntStream(values);
    }

    @Override
    public Double average() {
        checkIfTerminated();
        isTerminated = true;
        int count;
        double sum = 0;
        if (iterator.hasNext()) {
            sum += iterator.next();
            count = 1;
        } else {
            throw new IllegalArgumentException("Size is 0");
        }
        while (iterator.hasNext()) {
            sum += iterator.next();
            count++;
        }
        return sum / count;
    }

    @Override
    public Integer max() {
        return reduce(Integer.MAX_VALUE, Math::min);
    }

    @Override
    public Integer min() {
        return reduce(Integer.MAX_VALUE, Math::min);
    }

    @Override
    public long count() {
        checkIfTerminated();
        isTerminated = true;
        int count = 0;
        while (iterator.hasNext()) {
            iterator.next();
            count++;
        }

        return count;
    }

    @Override
    public Integer sum() {
        return reduce(0, Integer::sum);
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        checkIfTerminated();
        AsIntStream newStream = new AsIntStream(0);
        newStream.size = this.size;
        newStream.iterator = new FilterIterator(iterator, predicate);
        return newStream;
    }

    @Override
    public void forEach(IntConsumer action) {
        checkIfTerminated();
        isTerminated = true;
        while (iterator.hasNext()) {
            action.accept(iterator.next());
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        checkIfTerminated();
        AsIntStream newStream = new AsIntStream(0);
        newStream.size = this.size;
        newStream.iterator = new MapIterator(iterator, mapper);
        return newStream;
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        checkIfTerminated();
        AsIntStream newStream = new AsIntStream(0);
        newStream.size = this.size;
        newStream.iterator = new FlatMapIterator(iterator, func);
        return newStream;
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        checkIfTerminated();
        isTerminated = true;
        int result;
        if (iterator.hasNext()) {
            result = op.apply(identity, iterator.next());
        } else {
            throw new IllegalArgumentException("Size is 0");
        }

        while (iterator.hasNext()) {
            result = op.apply(result, iterator.next());
        }

        return result;
    }

    @Override
    public int[] toArray() {
        checkIfTerminated();
        isTerminated = true;
        int[] streamNumbers = new int[size];
        int i = 0;
        while (iterator.hasNext()) {
            streamNumbers[i] = iterator.next();
            i++;
        }

        return streamNumbers;
    }

    private void checkIfTerminated() {
        if (isTerminated) {
            throw new IllegalStateException("Stream has already been operated upon or closed");
        }
    }
}
