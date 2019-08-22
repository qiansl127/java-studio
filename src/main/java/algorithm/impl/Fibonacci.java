package algorithm.impl;

import algorithm.Calculator;

public class Fibonacci implements Calculator {

    @Override
    public int calculate(int i) {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        return doTailRecursion(0, 1, i);
    }

    private int doTailRecursion(int now, int next, int count) {
        if (count == 0) {
            return now;
        }
        return doTailRecursion(next, now + next, count - 1);
    }
}
