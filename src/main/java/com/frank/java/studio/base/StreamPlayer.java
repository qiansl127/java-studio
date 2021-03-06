package com.frank.java.studio.base;

import com.frank.java.studio.base.entity.Student;
import com.frank.java.studio.Player;
import com.frank.java.studio.util.Printer;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamPlayer implements Player {

    private List<Student> studentList =
            Arrays.asList(new Student("frank", 17),
                          new Student("jason", 18),
                          new Student("donut", 19),
                          new Student("frank", 15),
                          new Student("frank", 16));

    @Override
    public void play() {
        testMap();

        Printer.printDividingLine();
        testSorted();

        Printer.printDividingLine();
        testToMap();

        Printer.printLine();
        testGroupingBy();

        Printer.printLine();
        testMax();
    }
    
    private void testMap() {
        Printer.print("Test Map");

        String[] names = new String[] {"apple", "banana", "orange"};
        Integer[] ages = new Integer[] {3, 5};

        List<List<Student>> result1 = Stream.of(names)
                .map(name -> Stream.of(ages)
                        .map(age -> new Student(name, age))
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());

        List<Student> result2 = Stream.of(names)
                .flatMap(name -> Stream.of(ages).map(age -> new Student(name, age)))
                .collect(Collectors.toList());

        Printer.print(result1);
        Printer.print(result2);
    }

    private void testSorted() {
        Printer.print("Test Sorted");

        Map<String, Integer> myMap = new TreeMap<>();

        myMap.put("tree"  , 2);
        myMap.put("flower", 3);
        myMap.put("river" , 5);

        myMap.merge("river", 1, Integer::sum);
        myMap.merge("water", 1, Integer::sum);

        Printer.print(myMap);

        List<String> myList =
                myMap.keySet().stream()
                        .sorted(Comparator.<String, Integer>comparing(myMap::get).reversed())
                        .filter(key -> key.length() > 4)
                        .limit(3)
                        .collect(Collectors.toList());

        Printer.print(myList);
    }

    private void testToMap() {
        Printer.print("Test Collector - toMap");

        Map<String, Student> result1 =
                studentList.stream().collect(
                        Collectors.toMap(
                                Student::getName,
                                student -> student,
                                (v1, v2) -> v1));

        Map<String, Student> result2 =
                studentList.stream().collect(
                        Collectors.toMap(
                                Student::getName,
                                student -> student,
                                BinaryOperator.minBy(Comparator.comparing(Student::getAge))));

        Printer.print(result1);
        Printer.print(result2);
    }

    private void testGroupingBy() {
        Printer.print("Test Collector - groupingBy");

        List<String> myList = Arrays.asList("flower", "water", "river", "water");

        // HashMap and ArrayList by default
        Map<String, List<String>> result1 =
                myList.stream().collect(Collectors.groupingBy(key -> key));

        // add downstream collector
        Map<String, Long> result2 =
                myList.stream().collect(Collectors.groupingBy(key -> key, Collectors.counting()));

        // choose collection type
        Map<String, Set<String>> result3 =
                myList.stream().collect(
                        Collectors.groupingBy(
                                key -> key,
                                TreeMap::new,
                                Collectors.toCollection(TreeSet::new)));

        Printer.print(result1, result2, result3);

        // more advanced operations
        Map<String, List<Integer>> result4 =
                studentList.stream().collect(
                        Collectors.groupingBy(
                                Student::getName,
                                Collectors.mapping(Student::getAge, Collectors.toList())));

        Map<String, Optional<Student>> result5 =
                studentList.stream().collect(
                        Collectors.groupingBy(
                                Student::getName,
                                Collectors.maxBy(Comparator.comparing(Student::getAge))));

        Map<String, Optional<Integer>> result6 =
                studentList.stream().collect(
                        Collectors.groupingBy(
                                Student::getName,
                                Collectors.mapping(
                                        Student::getAge,
                                        Collectors.maxBy(Comparator.comparing(age -> age)))));
    }

    private void testMax() {
        Printer.print("Test Collector - maxBy");

        // seems same for these two ways
        // throws exception when more than one student and null name in one student
        // returns Optional.empty when the stream is empty
        Optional<Student> student1 =
                Stream.of(new Student(null, 12))
                        .collect(Collectors.maxBy(Comparator.comparing(Student::getName)));
        Optional<Student> student2 =
                Stream.of(new Student(null, 12))
                        .max(Comparator.comparing(Student::getName));
        Printer.print(student1, student2);
    }
}