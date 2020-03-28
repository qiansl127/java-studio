package com.frank.java.studio.thread;

import com.frank.java.studio.Player;
import com.frank.java.studio.thread.threadclass.printer.APrinter;
import com.frank.java.studio.thread.threadclass.printer.AbstractPrinter;
import com.frank.java.studio.thread.threadclass.printer.BPrinter;
import com.frank.java.studio.thread.threadclass.printer.CPrinter;
import com.frank.java.studio.util.Printer;

public class AbcPlayer implements Player {

    @Override
    public void play() {
        char[] chars = new char[9];
        for (int i = 0; i < chars.length; ++i) {
            chars[i] = 0;
        }

        AbstractPrinter a = new APrinter(chars);
        AbstractPrinter b = new BPrinter(chars);
        AbstractPrinter c = new CPrinter(chars);

        a.start();
        b.start();
        c.start();

        try {
            a.join();
            b.join();
            c.join();
        } catch (InterruptedException e) {
            e.printStackTrace(System.err);
        }

        for (char ch : chars) {
            Printer.print(ch);
        }
    }

}
