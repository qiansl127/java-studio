package com.frank.java.studio.pattern;

import com.frank.java.studio.pattern.entity.composite.Directory;
import com.frank.java.studio.pattern.entity.visitor.Visitor;

public class VisitorPlayer extends Composite {

    @Override
    public void play() {
        Directory root = this.setUpDirectory();

        root.accept(new Visitor());
    }
}
