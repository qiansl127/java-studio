package entity.composite;

import entity.visitor.Visitor;

public class File extends Entry {

    public File(String name) {
        super(name);
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
