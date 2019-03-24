package player.base;

import player.Player;
import entity.Period;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

public class Serialization extends Player {
    @Override
    public void play() {
        final byte[] serializedForm = this.serialize(new Period(new Date(), new Date()));
        Period p = (Period) this.deserialize(serializedForm);

        System.out.println(p);
    }

    private byte[] serialize(Period period) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);

            out.writeObject(period);

            return bos.toByteArray();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private Object deserialize(byte[] sf) {
        try {
            return new ObjectInputStream(
                    new ByteArrayInputStream(sf)
            ).readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }
}