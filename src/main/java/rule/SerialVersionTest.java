package rule;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/14 0014.
 */
public class SerialVersionTest implements Serializable {
    /**
     * 位置1
     * 良好习惯：显示声明SerialVersionUID
     */
    private static final long serialVersionUID = -5620204858357582874L;

    public final String nameFinal ="还是浊世魔王";

    public final int age = 10;

    private  String name;

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameFinal() {
        return nameFinal;
    }

    /**
     * 反序列化时构造函数不会被执行
     */
    public SerialVersionTest() {
        this.name = "不是混世魔王";
//        this.nameFinal = "还是混世魔王－Final";，如果在位置1赋好值，则反序化后final类型的nameFinal则为新的值
    }

    public static void main(String[] args) {
//        Producer.writeObject();
        Consumer.readObject();
    }
}

class Producer{
    public static void writeObject() {
        SerialVersionTest test = new SerialVersionTest();
        test.setName("混世魔王");
        SerializationUtils.writeObject(test);
    }
}

class Consumer{
    public static void readObject() {
        SerialVersionTest serialVersionTest = (SerialVersionTest) SerializationUtils.readObject();
        System.out.println("name="+serialVersionTest.getName());
        System.out.println("nameFial="+serialVersionTest.getNameFinal());
        System.out.println("age="+serialVersionTest.getAge());


    }
}
