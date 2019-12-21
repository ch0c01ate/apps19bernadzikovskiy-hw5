package ua.edu.ucu.iterators;

import java.util.Iterator;

public class BaseIterator implements Iterator<Integer> {
    int[] values;
    int i;

    public BaseIterator(int[] values) {
        this.values = values.clone();
        i = 0;
    }

    @Override
    public boolean hasNext() {
        return i < values.length;
    }

    @Override
    public Integer next() {
        Integer nextValue = values[i];
        i++;
        return nextValue;
    }
}
