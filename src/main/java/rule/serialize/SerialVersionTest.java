package rule.serialize;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/14 0014.
 * 良好习惯：显示声明SerialVersionUID
 *      类实现Serializable接口目的是为了可持久化，比如网络传输或本地存储，为系统的分布和异构部署提供先决支持条件。若没有序列化，就不能远程调用。
        JVM通过什么来判断一个类的版本呢？通过SerialVersionUID（流标识符），可显示声明，也可隐式声明。显示声明如：
        public static final long serialVersionUID=2343L;
        隐式则是我不声明，让编译器在编译的时候帮我生成。生成的依据是通过包名、类名、继承关系、非私有的方法和属性，以及参数、返回值等诸多因子计算得出，极度复杂，基本上计算出来的这个值是唯一的。
        JVM在反序列化时，会比较数据流中的serialVersionUID与类的serialVersionUID是否相同，如相同，认为类没有发生改变，可以把数据load为实例对象；如果不相同，JVM就抛异常了。
        但是，有时候特殊一点，我的类改变不大，JVM是不是可以把我以前的对象反序列化过来？这就要依靠显式声明了，向JVM撒谎说“我的类版本没有变更”，这样写的类就是实现了向上兼容。

 * 避免用序列化类在构造函数中为不变量赋值。
        反序列化时构造函数不会被执行

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
