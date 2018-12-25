import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

public class Main {

    public static void main(String[] args) {
        testMap();
        testSorted();
        testCollect();
    }

    private static void testMap() {
        print("Test Map");

        String[] names = new String[] {"apple", "banana", "orange"};
        Integer[] ages = new Integer[] {3, 5};

        List<List<Student>> result1 = Stream.of(names)
                .map(name -> Stream.of(ages).map(age -> new Student(name, age)).collect(toList()))
                .collect(toList());

        List<Student> result2 = Stream.of(names)
                .flatMap(name -> Stream.of(ages).map(age -> new Student(name, age)))
                .collect(toList());

        print(result1);
        print(result2);
    }

    private static void testSorted() {
        print("\nTest Sorted");

        Map<String, Integer> myMap = new TreeMap<>();

        myMap.put("tree"  , 2);
        myMap.put("flower", 3);
        myMap.put("river" , 5);

        myMap.merge("river", 1, Integer::sum);
        myMap.merge("water", 1, Integer::sum);

        print(myMap);

        List<String> myList =
                myMap.keySet().stream()
                        .sorted(comparing(myMap::get).reversed())
                        .filter(key -> key.length() > 4)
                        .limit(2)
                        .collect(toList());

        print(myList);
    }

    private static void testCollect() {
        testToMap();
        testGroupingBy();
    }

    private static void testToMap() {
        print("\nTest Collector - toMap");

        List<Student> myList = Arrays.asList(
                new Student("frank", 17),
                new Student("jason", 18),
                new Student("donut", 19),
                new Student("frank", 15),
                new Student("frank", 16));

        Map<String, Student> result1 =
                myList.stream().collect(
                        toMap(Student::getName, student -> student, (v1, v2) -> v1));

        Map<String, Student> result2 =
                myList.stream().collect(
                        toMap(Student::getName, student -> student, BinaryOperator.minBy(comparing(Student::getAge))));

        print(result1);
        print(result2);
    }

    private static void testGroupingBy() {
        print("\nTest Collector - groupingBy");

        List<String> myList = Arrays.asList("flower", "water", "river", "water");

        // HashMap and ArrayList by default
        Map<String, List<String>> result1 =
                myList.stream().collect(groupingBy(key -> key));

        // add downstream collector
        Map<String, Long> result2 =
                myList.stream().collect(groupingBy(key -> key, counting()));

        // choose collection type
        Map<String, Set<String>> result3 =
                myList.stream().collect(groupingBy(key -> key, TreeMap::new, toCollection(TreeSet::new)));

        print(result1, result2, result3);
    }

    @SafeVarargs
    private static <T> void print(T... array) {
        if (array == null || array.length == 0) {
            System.out.println();
            return;
        }
        for (T t : array) {
            System.out.println(t);
        }
    }

}