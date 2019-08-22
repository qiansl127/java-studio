package player.thread;

import algorithm.Calculator;
import algorithm.impl.SlowFibonacci;
import player.Player;
import util.Printer;

import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParallelStream implements Player {

    private Calculator calculator = new SlowFibonacci();

    @Override
    public void play() {
        test(IntStream.range(0, 47));
        test(IntStream.range(0, 47).parallel());  // no guarantee for the order
    }

    private void test(IntStream stream) {
        Date start = new Date();
        stream.map(calculator::calculate).boxed().collect(Collectors.toSet()).size();
        Date end  = new Date();

        Printer.print(end.getTime() - start.getTime());
    }
}
