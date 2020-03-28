package com.frank.java.studio.thread;

import com.frank.java.studio.thread.algorithm.Calculator;
import com.frank.java.studio.thread.algorithm.impl.SlowFibonacci;
import com.frank.java.studio.Player;
import com.frank.java.studio.util.Printer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

public class ParallelStream implements Player {

    private Calculator calculator = new SlowFibonacci();

    @Override
    public void play() {
        test(IntStream.range(0, 44));
        test(IntStream.range(0, 44).parallel());  // no guarantee for the order
    }

    private void test(IntStream stream) {
        Date start = new Date();
        List<Integer> list = new ArrayList<>();

        // if using list collector, cannot do it in parallel
        stream
            .map(calculator::calculate)
            .forEach(list::add);

        Date end  = new Date();

        Printer.print(end.getTime() - start.getTime());
        Printer.print(list);
    }
}
