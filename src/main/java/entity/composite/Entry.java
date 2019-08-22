package entity.composite;

import entity.visitor.Visitor;

public abstract class Entry {
    private String name;

    Entry(String name) {
        this.name = name;
    }

    public void print() {
        System.out.println(name);
    }

    public abstract void accept(Visitor v);

    @Override
    public String toString() {
        return name;
    }

}
