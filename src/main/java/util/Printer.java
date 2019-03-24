package util;

public class Printer {
    @SafeVarargs
    public static <T> void print(T... array) {
        if (array == null || array.length == 0) {
            System.out.println();
            return;
        }
        for (T t : array) {
            System.out.println(t);
        }
    }

    public static void printDividingLine() {
        System.out.println("\n======I'm dividing line======\n");
    }

    public static void printLine() {
        System.out.println();
    }
}
