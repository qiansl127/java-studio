package com.frank.java.studio.thread.threadclass;

import java.util.Queue;

public class Producer extends Thread {
    final private Queue<Integer> queue;
    final private int capacity;
    final private String name;

    public Producer(Queue<Integer> queue, int capacity, String name) {
        this.queue = queue;
        this.capacity = capacity;
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; ++i) {
            produce(i);
        }
    }

    private void produce(int i) {
        synchronized (queue) {
            while (queue.size() == capacity) {
                try {
                    System.out.println("Producer_" + this.name + " is waiting");
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace(System.err);
                }
            }

            queue.offer(i);
            System.out.println("Producer_" + this.name + " produces " + i);

            queue.notifyAll();
        }
    }
}
