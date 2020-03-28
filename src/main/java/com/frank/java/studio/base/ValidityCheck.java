package com.frank.java.studio.base;

import com.frank.java.studio.base.entity.Student;
import com.frank.java.studio.Player;
import com.frank.java.studio.util.Printer;

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
