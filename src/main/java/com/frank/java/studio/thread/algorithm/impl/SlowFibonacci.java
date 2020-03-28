package com.frank.java.studio.thread.algorithm.impl;

import com.frank.java.studio.thread.algorithm.Calculator;

public class SlowFibonacci implements Calculator {
    @Override
    public int calculate(int i) {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        if (i <= 1) {
            return i;
        }
        return calculate(i-1) + calculate(i-2);
    }
}
