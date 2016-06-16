package rule.clone;

import java.io.*;

/**
 * Created by Administrator on 2016/6/16 0016.
 *
 * 此工具类拷贝注意：
 * （1）对象的内部属性都是可序列化的：如果内部属性不可序列化，则会抛出序列化异常。
 * （2）注意方法和属性的特殊修饰符：比如final、static变量的序列化问题会被引入到对象拷贝中来，同时transient变量也会影响到拷贝的效果。
 * 使用Apache下的 commons工具包中的SerializationUtils类拷贝，直接使用更简洁方便。
 */
public class CloneUtils {
    public static <T extends Serializable> T clone (T obj) {
        T cloneObj = null;
        //读取对象字节数据
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            oos.close();

            //分配内存空间，写入原始对象，生成新对象
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            cloneObj = (T) ois.readObject();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
