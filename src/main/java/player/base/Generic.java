package player.base;

import entity.Gender;
import entity.Student;
import player.Player;
import util.Printer;

import java.util.ArrayList;
import java.util.List;

public class Generic extends Player {
    @Override
    public void play() {
        testParameterizedType();

        Printer.printLine();
        testWildcardType();

        Printer.printLine();
        testBoundedParameterizedType();

        Printer.printLine();
        testBoundedWildcardType();
    }

    private void testParameterizedType() {
        // generic is invariant and erased
        // List<Integer> has no type relation with List<Number>
        List<Number> list = new ArrayList<>(2);
        Integer i = 17;
        list.add(i);

        // list.add("Hello");
        // won't compile, check and erase at compile time
        Printer.print(list);

        // array is covariant and reified
        // Integer[] is a subtype of Number[]
        Object[] array = new Integer[2];
        array[0] = i;

        // array[1] = "hello";
        // runtime ArrayStoreException
        Printer.print(array[0]);
    }

    private void testWildcardType() {
        List<String> list = new ArrayList<>(2);

        List<?> listInWildcardType = list;

        // listInWildcardType.add("hello");
        // won't compile, can add null only

        listInWildcardType.add(null);
        list.add("Hello");

        Printer.print(list);
    }

    private void testBoundedParameterizedType() {
        MyList<SuperStudent> list = new MyList<>();
        list.add(new SuperStudent());
        Printer.print(list.size());
    }

    private void testBoundedWildcardType() {
        List<Integer> list1 = new ArrayList<>();
        list1.add(42);

        List<Object> list2 = new ArrayList<>();

        // Concrete < Integer < Number < Object < Abstract
        // this.<Number>move(list1, list2);
        // assigning Number to E is not necessary, just compile time check
        this.move(list1, list2);

        Printer.print(list2);
    }

    private <E> void move(List<? extends E> list1, List<? super E> list2) {
        // PECS, producer extends & consumer super
        list2.addAll(list1);

        // comparables and comparators are always consumers, user Comparable/Comparator<? super T>
    }

    public static class MyList<E extends Student> extends ArrayList<E> {
        @Override
        public int size() {
            return -1;
        }
    }

    public static class SuperStudent extends Student {
        SuperStudent() {
            super("super", 100, Gender.UNKNOWN);
        }
    }

}
