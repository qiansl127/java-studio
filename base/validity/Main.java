import java.util.Objects;

public class Main {

    public static void main(String[] args) {
        test(new Student("frank", -8));
    }

    private static void test(Student student) {
        Objects.requireNonNull(student);
        assert student.getAge() > 0;    // -ea(-enableassertions) in VM options

        print(student);
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