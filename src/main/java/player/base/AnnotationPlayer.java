package player.base;

import annotation.Description;
import player.Player;
import util.Printer;

@Description("Hello Annotation")
public class AnnotationPlayer extends Player {

    @Override
    public void play() {
        Class<?> clazz = AnnotationPlayer.class;

        Description desc = clazz.getAnnotation(Description.class);
        if (desc != null) {
            Printer.print(desc.value());
        }
    }
}
