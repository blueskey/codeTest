package rule.serialize;

import java.io.*;

/**
 * Created by Administrator on 2016/6/14 0014.
 */
public class SerializationUtils {
    private static String FILE_NAME = "c:/obj.bin";

    public static void writeObject(Serializable serializable) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
            oos.writeObject(serializable);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Object readObject() {
        Object obj = null;
        try {
            ObjectInput input = new ObjectInputStream(new FileInputStream(FILE_NAME));
            obj = input.readObject();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
