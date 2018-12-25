public class Student {

    private String name;
    private int age;

    Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    String getName() {
        return name;
    }

    int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "{name: " + name + ", age: " + age + "}";
    }
}
