package player.pattern;

import entity.composite.Directory;
import entity.composite.File;
import player.Player;

public class Composite implements Player {

    @Override
    public void play() {
        this.setUpDirectory().print();
    }

    Directory setUpDirectory() {
        Directory root = new Directory("root");

        Directory dirA = new Directory("A");
        File file1 = new File("1");
        File file2 = new File("2");
        dirA.add(file1);
        dirA.add(file2);

        Directory dirB = new Directory("B");
        File fileC = new File("C");

        root.add(dirA);
        root.add(dirB);
        root.add(fileC);

        return root;
    }

}
