package com.frank.java.studio.base;

import com.frank.java.studio.base.annotation.Description;
import com.frank.java.studio.Player;
import com.frank.java.studio.util.Printer;

@Description("Hello Annotation")
public class AnnotationPlayer implements Player {

    @Override
    public void play() {
        Class<?> clazz = AnnotationPlayer.class;

        Description desc = clazz.getAnnotation(Description.class);
        if (desc != null) {
            Printer.print(desc.value());
        }
    }
}
