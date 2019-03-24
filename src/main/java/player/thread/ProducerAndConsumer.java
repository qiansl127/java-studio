package player.thread;

import player.Player;
import thread.Consumer;
import thread.Producer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProducerAndConsumer extends Player {
    private static final int CAPACITY = 5;

    public void play() {

        final Queue<Integer> queue = new LinkedList<>();
        Producer p1 = new Producer(queue, CAPACITY, "1");
        Producer p2 = new Producer(queue, CAPACITY, "2");
        Consumer c1 = new Consumer(queue, "1");
        Consumer c2 = new Consumer(queue, "2");

        ExecutorService executor = Executors.newFixedThreadPool(4);
        executor.execute(p1);
        executor.execute(c1);
        executor.execute(c2);
        executor.execute(p2);

        executor.shutdown();
    }
}
