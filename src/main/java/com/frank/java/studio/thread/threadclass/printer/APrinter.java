package com.frank.java.studio.thread.threadclass.printer;

public class APrinter extends AbstractPrinter {

    // 0, 1, 2, 3, 4, 5, 6, 7, 8
    // A, x, x, A, x, x, A, x, x

    public APrinter(char[] chars) {
        super(chars, 'A', 0);
    }

}
