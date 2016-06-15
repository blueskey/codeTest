package rule;

/**
 * Created by Administrator on 2016/6/15 0015.
 * 静态变量是类加载时被分配到数据区的，它在内存中只有一个拷贝，不会被分配多次，其后的所有赋值操作都是值改变，地址则保持不变。
 * JVM初始化变量是先声明空间，再赋值。int i=100;int i;i=100;
 * 静态变量是在类初始化时首先被加载的，JVM会查找类中所有的静态声明，然后分配空间（这时只是完成了地址空间的分配，还没有赋值），之后JVM会根据类中静态赋值（包括静态类赋值和静态块赋值）的先后顺序来执行。
 * 变量先声明后使用，是一个良好的编码风格
 */
public class StaticTest {
    static {
        i = 200;//可以先使用，后声明
    }

    public static int i;

    public static void main(String[] args) {

        System.out.println(i);
    }


    static {
        i = 30;
    }

    public static void test() {
        i = 50;
    }
}
