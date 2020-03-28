package com.frank.java.studio.pattern.entity.composite;

import com.frank.java.studio.pattern.entity.visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class Directory extends Entry {

    private List<Entry> entryList = new ArrayList<>();

    public Directory(String name) {
        super(name);
    }

    @Override
    public void print() {
        super.print();
        entryList.forEach(Entry::print);
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    public void add(Entry e) {
        entryList.add(e);
    }

    public List<Entry> getEntryList() {
        return entryList;
    }
}
