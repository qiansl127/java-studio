package player.base;

import entity.Student;
import player.Player;
import util.Printer;

import java.util.Objects;

public class ValidityCheck implements Player {
    @Override
    public void play() {
        this.test(new Student("frank", -8));
    }

    private void test(Student student) {
        Objects.requireNonNull(student);
        assert student.getAge() > 0;    // -ea(-enableassertions) in VM options

        Printer.print(student);
    }
}
