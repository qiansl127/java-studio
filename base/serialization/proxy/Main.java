import java.io.*;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        final byte[] serializedForm = serialize(new Period(new Date(), new Date()));
        Period p = (Period) deserialize(serializedForm);

        System.out.println(p);
    }

    private static byte[] serialize(Period period) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);

            out.writeObject(period);

            return bos.toByteArray();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static Object deserialize(byte[] sf) {
        try {
            return new ObjectInputStream(
                    new ByteArrayInputStream(sf)
            ).readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }
}