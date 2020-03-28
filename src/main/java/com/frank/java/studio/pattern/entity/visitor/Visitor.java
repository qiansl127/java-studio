package com.frank.java.studio.pattern.entity.visitor;

import com.frank.java.studio.pattern.entity.composite.Directory;
import com.frank.java.studio.pattern.entity.composite.File;
import com.frank.java.studio.util.Printer;

public class Visitor {

    private String currentDirectory = "";

    public void visit(File file) {
        Printer.print(currentDirectory + "/" + file);
    }

    public void visit(Directory directory) {
        Printer.print(currentDirectory + "/" + directory);
        String savedDirectory = currentDirectory;
        currentDirectory = currentDirectory + "/" + directory;

        directory.getEntryList().forEach(entry -> entry.accept(this));

        currentDirectory = savedDirectory;
    }
}
