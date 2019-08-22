package player.pattern;

import entity.composite.Directory;

public class Visitor extends Composite {

    @Override
    public void play() {
        Directory root = this.setUpDirectory();

        entity.visitor.Visitor v  = new entity.visitor.Visitor();
        root.accept(v);
    }
}
