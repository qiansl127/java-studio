import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) {
        try {
            Class<?> klass = Class.forName("Student");

            // get constructor
            Constructor constructor = klass.getConstructor(String.class, int.class);

            // get private method
            Method method1 = klass.getDeclaredMethod("getName");
            Method method2 = klass.getDeclaredMethod("getAge");
            method1.setAccessible(true);
            method2.setAccessible(true);

            // create the object at run time
            Object object = constructor.newInstance("frank", 17);

            // call
            Object result1 = method1.invoke(object);
            Object result2 = method2.invoke(object);

            print(result1, result2);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
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