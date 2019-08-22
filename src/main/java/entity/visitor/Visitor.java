package entity.visitor;

import entity.composite.Directory;
import entity.composite.File;

public class Visitor {

    private String currentDirectory = "";

    public void visit(File file) {
        System.out.println(currentDirectory + "/" + file);
    }

    public void visit(Directory directory) {
        System.out.println(currentDirectory + "/" + directory);
        String savedDirectory = currentDirectory;
        currentDirectory = currentDirectory + "/" + directory;

        directory.getEntryList().forEach(entry -> entry.accept(this));

        currentDirectory = savedDirectory;
    }
}
