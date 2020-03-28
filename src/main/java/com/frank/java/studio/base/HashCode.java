package com.frank.java.studio.base;

import com.frank.java.studio.base.entity.Student;
import com.frank.java.studio.base.entity.Gender;
import com.frank.java.studio.Player;
import com.frank.java.studio.util.Printer;

public class HashCode implements Player {
    @Override
    public void play() {
        testHashCode();
        Printer.printDividingLine();
        testToBinary();
    }

    private void testHashCode() {
        Student frank = new Student("frank", 17, null);
        Student raven = new Student(null, 17, Gender.FEMALE);
        Student frank_copy = new Student("frank", 17, null);
        Student raven_copy = new Student(null, 17, Gender.FEMALE);

        Printer.print(frank);
        Printer.print(toBinary(frank.hashCode()));

        Printer.print(raven);
        Printer.print(toBinary(raven.hashCode()));

        Printer.printLine();
        Printer.print(frank.equals(null));        // false
        Printer.print(frank.equals(frank));       // true
        Printer.print(frank.equals(raven));       // false
        Printer.print(raven.equals(frank));       // false
        Printer.print(frank.equals(frank_copy));  // true
        Printer.print(raven.equals(raven_copy));  // true
    }

    private void testToBinary() {
        Printer.print(toBinary(-2));
        Printer.print(toBinary(2));
        Printer.print(toBinary(Integer.MAX_VALUE));
        Printer.print(toBinary(Integer.MIN_VALUE));
    }

    private String toBinary(int num) {
        if (num == Integer.MIN_VALUE) return "10000000000000000000000000000000";
        if (num < 0) {
            String temp = toBinary(0 - num);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < temp.length(); ++i) {
                sb.append(temp.charAt(i) == '0' ? '1' : '0');
            }
            for (int i = sb.length() - 1; i >= 0; --i) {
                if (sb.charAt(i) == '0') {
                    sb.setCharAt(i, '1');
                    break;
                }
                sb.setCharAt(i, '0');
            }
            return sb.toString();
        }
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.insert(0, (num % 2 > 0) ? '1' : '0');
            num = num / 2;
        }
        int length = sb.length();
        for (int i = 0; i < 32 - length; ++i) {
            sb.insert(0, '0');
        }
        return sb.toString();
    }
}
