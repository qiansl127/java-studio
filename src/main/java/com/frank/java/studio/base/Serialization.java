package com.frank.java.studio.base;

import com.frank.java.studio.Player;
import com.frank.java.studio.base.entity.Period;
import com.frank.java.studio.util.Printer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

public class Serialization implements Player {
    @Override
    public void play() {
        final byte[] serializedForm = this.serialize(new Period(new Date(), new Date()));
        Period p = (Period) this.deserialize(serializedForm);

        Printer.print(p);
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