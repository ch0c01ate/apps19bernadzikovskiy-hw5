package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntPredicate;

import java.util.Iterator;

public class FilterIterator implements Iterator<Integer> {
    Iterator<Integer> iterator;
    IntPredicate pr;
    Integer nextV;

    public FilterIterator(Iterator<Integer> iterator, IntPredicate pr) {
        this.iterator = iterator;
        this.pr = pr;
    }

    @Override
    public boolean hasNext() {
        while(iterator.hasNext()){
            Integer nextValue = iterator.next();
            if(pr.test(nextValue)){
                nextV = nextValue;
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        return nextV;
    }
}