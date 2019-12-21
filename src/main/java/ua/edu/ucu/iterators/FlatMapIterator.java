package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntToIntStreamFunction;
import ua.edu.ucu.stream.AsIntStream;
import ua.edu.ucu.stream.IntStream;

import java.util.Iterator;

public class FlatMapIterator implements Iterator<Integer> {

    Iterator<Integer> currentIterator;
    Iterator<Integer> iterator;
    IntToIntStreamFunction function;
    int nextV;

    public FlatMapIterator(Iterator<Integer> iterator, IntToIntStreamFunction function) {
        this.iterator = iterator;
        this.function = function;
        currentIterator = new BaseIterator(new int[0]);
    }

    @Override
    public boolean hasNext() {
        if (currentIterator.hasNext()) {
            nextV = currentIterator.next();
            return true;
        }
        if (iterator.hasNext()) {
            AsIntStream stream = (AsIntStream) function.applyAsIntStream(iterator.next());
            currentIterator = new BaseIterator(stream.toArray());
            nextV = currentIterator.next();
            return true;
        }

        return false;
    }

    @Override
    public Integer next() {
        return nextV;
    }
}
