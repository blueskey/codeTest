package rule.generic;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/23 0023.
 * Class类无构造函数。不能实例化，Class对象是在加载类时由JVM通过调用类加载器中的defineClass方法自动构造的
 * 可以描述基本类型。虽然8个基本类型在JVM中并不是一个对象，它们一般存在于栈内在中，Class类仍可描述它们，如int.class。
 *
 * Class类是Java的反射入口，只有在获得一个类的描述对象后才能动态地加载、调用，
 * 一般获得一个Class对象有三种途径：
 *      类属性方式，如String.class;
 *      对象的getClass方法，如new String().getClass();
 *      forName方法加载，如Class.forName("java.lang.String");
 */
public class ClassTest {
    public static void main(String[] args) throws Exception{
//        getDeclaredTest();
        invokeTest();
        dynamicLoadingTest();

        arrayForNameTest();
    }

    /**
     * Class类对象都是单例模式。一个Class的实例对象描述且只描述一个类，一个类只有一个Class实例对象
     * Class实例对象不区分泛型：
     */
    public static void classTest() {
        System.out.println(String.class.equals(new String().getClass()));//true
        System.out.println("Abc".getClass().equals(String.class));//true
        // Class实例对象不区分泛型：
        System.out.println(ArrayList.class.equals(new ArrayList<String>().getClass()));//true
    }

    /**
     * getMethod:获得所有public访问级别的方法，包换从父类继承的方法；
     * getDeclaredMethod：获得自身类的所有方法，包括public、private等，不受限于访问权限。
     * 其他成对的getXXX类似。
     * @throws Exception
     */
    public static void getDeclaredTest() throws Exception{
        String methodName = "doStuff";
        Method m1 = Foo.class.getDeclaredMethod(methodName);
        Method m2 = Foo.class.getMethod(methodName);//Exception in thread "main" java.lang.NoSuchMethodException: rule.generic.ClassTest$Foo.doStuff()

    }
    static class Foo{
        void doStuff() {
            System.out.println("--doStuff--------");
        }
    }

    /**
     * Java中通过反射执行一个方法的过程如下：
        获取一个方法对象；根据isAccessible返回值确定是否能够执行，如返回false则需要调用setAcessible(true)，最后再调用 invoke执行方法。

     Accessible不是语法层级理解的访问权限，而是用来判断是否需要进行安全检查的。不需要则直接执行，在幅度地提升系统性能（由于取消了安全检查，也可以运行private方法和属性了）
     * @throws Exception
     */
    public static void invokeTest() throws Exception{
        Method m1 = Foo.class.getDeclaredMethod("doStuff");
        if (!m1.isAccessible()) {
            m1.setAccessible(true);
        }
        m1.invoke(new Foo(), null);

    }

    /**
     * 使用forName动态加载类文件
     动态加载是指在程序运行时加载需要的类库文件，对Java，一般情况下，一个类文件在启动时或首次初始化时会被加载到内存中，而反射则可以在运行时再度决定是否要加载一个类。
     一个对象的生成必然会经过以下两个步骤：
     1)加载到内存中生成Class的实例对象
     2)通过new关键字生成实例对象
     动态加载的意义：加载一个类即表示要初始化该类的static变量，特别是static代码块，在这里可以做大量的工作，如初始化环境等。是经典的是就数据库驱动程序的加载片段：
     ```java
     Class.forName("com.mysql.jdbc.Driver").newInstance();
     ```
     forName只是把一个类加载到内存中，并不保证由此产生一个实例对象，也不会执行任何方法，之所以会初始化static代码块，那是由类加载机制所决定的，而不是forName方法决定的。即如果没有static属性或static代码块，forName就只是加载类，没有任何的执行行为。
     */
    public static void dynamicLoadingTest() throws Exception{
        Class.forName("rule.generic.GenericTest");//执行了GenericTest类static代码块： Do GenericTest static code ....
    }

    /**
     * 动态加载不适合数组
     *
     * 不同类型的数组经编译后生成不同的类
            byte[]  -->  [B
            char[]  -->  [C
            Double[]  -->  [D
            Float[]  -->  [F
            Int[]  -->  [I
            Long[]  -->  [J
            Short[]  -->  [S
            Boolean[]  -->  [Z
            引用类型，如String[]  -->  [L引用类型；，（如[Ljava.lang.String;）

     虽然代码上可以动态加载一个数组类，但没有任何意义，因为它不能生成一个数组对象，
     它只是把一个数组类加载到内存中，并不能通过newInstance方法生成一个实例对象，因为没有定义数组长度（没有长度的数组是不允许存在的）

     可以用Array数组反射类来动态加载数组。

     */
    public static void arrayForNameTest() throws Exception{
        String[] strs = new String[10];
//        Class.forName("java.lang.String[]");//Exception in thread "main" java.lang.ClassNotFoundException: java/lang/String[]
        Class.forName("[Ljava.lang.String;");//注意后面有个";"
        Class.forName("[J");//加载long[]

        //动态创建数组
        String[] st = (String[]) Array.newInstance(String.class, 10);
        //创建一个多维数组
        int[][] ints = (int[][]) Array.newInstance(int.class, 3, 5);
    }
}
