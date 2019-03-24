package player.base;

import player.Player;
import util.Printer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Reflection extends Player {
    @Override
    public void play() {
        try {
            Class<?> klass = Class.forName("entity.Student");

            // get constructor
            Constructor constructor = klass.getConstructor(String.class, int.class);

            // get method
            Method method1 = klass.getDeclaredMethod("getName");
            Method method2 = klass.getDeclaredMethod("getAge");
            // set the method accessible if it is private
            method1.setAccessible(true);
            method2.setAccessible(true);

            // create the object at run time
            Object object = constructor.newInstance("frank", 17);

            // call
            Object result1 = method1.invoke(object);
            Object result2 = method2.invoke(object);
            Printer.print(result1, result2);

            // edit private field
            Field field = klass.getDeclaredField("age");
            field.setAccessible(true);
            field.setInt(object, 16);

            // call
            Printer.print(method2.invoke(object));
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}
