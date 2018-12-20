import java.util.Queue;

public class Consumer extends Thread {

    private final Queue<Integer> queue;
    private final String name;

    Consumer(Queue<Integer> queue, String name) {
        this.queue = queue;
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; ++i) {
            consume();
        }
    }

    private void consume() {
        synchronized (queue) {
            while (queue.size() == 0) {
                try {
                    System.out.println("Consumer " + this.name + " is waiting");
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace(System.err);
                }
            }

            int result = queue.poll();
            System.out.println("Consumer " + this.name + " consumes " + result);
            queue.notifyAll();
        }
    }
}
