package com.frank.java.studio.thread.threadclass.printer;

public class AbstractPrinter extends Thread {

    private final char[] chars;

    private final char c; // char to append
    private final int hit; // hit index

    AbstractPrinter(char[] chars, char c, int hit) {
        this.chars = chars;
        this.c = c;
        this.hit = hit;
    }

    private int getIndexToSet(char[] chars) {
        for (int i = 0; i < chars.length; ++i) {
            if (chars[i] == 0) {
                return i;
            }
        }
        return chars.length;
    }

    @Override
    public void run() {
        synchronized (chars) {
            while (getIndexToSet(chars) <= chars.length - (3 - hit)) {

                while (getIndexToSet(chars) % 3 != hit) {
                    try {
                        chars.wait();

                    } catch (InterruptedException e) {
                        e.printStackTrace(System.err);
                    }
                }

                chars[getIndexToSet(chars)] = c;
                chars.notifyAll();
            }
        }
    }

}
