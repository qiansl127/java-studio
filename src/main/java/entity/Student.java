package entity;

public class Student {
    private String name;
    private int age;
    private Gender gender;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student(String name, int age, Gender gender) {
        this(name, age);
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Student)) return false;    // including null check here

        Student student = (Student) o;

        return (this.name == null) ? student.name == null : this.name.equals(student.name)
                && this.age == student.age
                && this.gender == student.gender;
    }

    @Override
    public int hashCode() {
        int result = ((name != null) ? name.hashCode() : 0);
        result = 31 * result + Integer.hashCode(age);
        result = 31 * result + ((gender != null) ? gender.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("{name: ");
        sb.append((name != null) ? name : "null");
        sb.append(", ");
        sb.append("age: ");
        sb.append(age);
        sb.append(", gender: ");
        sb.append((gender != null) ? gender.name() : "null");
        sb.append("}");

        return sb.toString();
    }
}
