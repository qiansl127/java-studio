package com.frank.java.studio;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.stream.IntStream;

public class PlayGround {

    private static final String SEPARATOR = ",";

    private static final String[] classNames = new String[] {
            "com.frank.java.studio.base.Reflection",
            "com.frank.java.studio.base.Serialization",
            "com.frank.java.studio.base.HashCode",
            "com.frank.java.studio.base.StreamPlayer",
            "com.frank.java.studio.base.ValidityCheck",
            "com.frank.java.studio.base.Generic",
            "com.frank.java.studio.base.AnnotationPlayer",
            "com.frank.java.studio.thread.ProducerAndConsumer",
            "com.frank.java.studio.thread.ParallelStream",
            "com.frank.java.studio.pattern.Composite",
            "com.frank.java.studio.pattern.VisitorPlayer"
    };

    public static void main(String[] args) {

        IntStream idStream;
        if (args.length > 0) {
            idStream = Arrays.stream(args[0].split(SEPARATOR)).mapToInt(Integer::valueOf);
        } else {
            idStream = IntStream.range(0, classNames.length);
        }

        idStream.forEach(PlayGround::play);
    }

    private static void play(Integer id) {
        try {
            System.out.println("===== " + id + ". " + classNames[id] + " =====");

            // check the type manually
            @SuppressWarnings("unchecked")
            Class<Player> klass = (Class<Player>) Class.forName(classNames[id]);

            Constructor<Player> constructor = klass.getConstructor();
            Player player = constructor.newInstance();
            player.play();

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

        System.out.println();
        System.out.println();
    }
}
