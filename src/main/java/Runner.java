import player.Player;

import java.lang.reflect.Constructor;

public class Runner {
    private static final String[] classNames = new String[] {
            "player.base.Reflection",            // 0
            "player.base.Serialization",         // 1
            "player.base.HashCode",              // 2
            "player.base.StreamPlayer",          // 3
            "player.base.ValidityCheck",         // 4
            "player.base.Generic",               // 5
            "player.base.AnnotationPlayer",      // 6
            "player.thread.ProducerAndConsumer", // 7
            "player.thread.ParallelStream",      // 8
            "player.pattern.Composite",          // 9
            "player.pattern.Visitor"             // 10
    };

    public static void main(String[] args) {
        try {
            Integer i = (args.length > 0) ? Integer.valueOf(args[0]) : 10;

            // check the type manually
            @SuppressWarnings("unchecked")
            Class<Player> klass = (Class<Player>) Class.forName(classNames[i]);

            Constructor<Player> constructor = klass.getConstructor();
            Player player = constructor.newInstance();
            player.play();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}
