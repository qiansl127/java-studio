package com.frank.java.studio.pattern.entity.composite;

import com.frank.java.studio.pattern.entity.visitor.Visitor;

public class File extends Entry {

    public File(String name) {
        super(name);
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
