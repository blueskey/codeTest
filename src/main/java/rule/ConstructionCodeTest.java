package rule;

/**
 * Created by Administrator on 2016/6/15 0015.
 * 编译器会把构造代码块插入到每个构造函数的最前端。（不过，当遇到this关键字（即构造函数里调用另一个构造函数）时，则不会再插入构造代码块，即不会重复调用构造代码块）
 * 注意：构造代码块不是在构造函数之前运行的，它是依托于构造函数的执行的。
 * 代码块之间则按照出现顺序依次执行
 *
 * 可应用场景：1、初始化实例变量：如果每个构造函数都要初始化这个变量，可以通过构造代码块来实现，当然也可以定义一个方法，然后在每个构造函数中调用，但不是最佳方案；
 *             2、初始化实例环境：如一个对象必须在另一个对象存在时才可以存在，则可以在构造代码块检查另一个是否已存在，不存在则创建它。
 *
 */
public class ConstructionCodeTest extends MyFather{
    private static int count;
    //编译器会把构造代码块插入到每个构造函数的最前端
    {
        count++;
        System.out.println("执行构造代码块  1…………");

    }

    public ConstructionCodeTest() {
        super();
        System.out.println("执行无参构造……………");
    }

    public ConstructionCodeTest(String string) {
        this();
        System.out.println("执行有参构造 s ………………");
    }

    {
        System.out.println("执行构造代码块  2…………");

    }

    /**
     * new ConstructionCodeTest("s");输出如下：
     *  父类构造代码块……
        父类构造方法……
        执行构造代码块  1…………
        执行构造代码块  2…………
        执行无参构造……………
        执行有参构造 s ………………
     * @param args
     */
    public static void main(String[] args) {
        new ConstructionCodeTest("s");
//        new ConstructionCodeTest();
//        new ConstructionCodeTest("s");
        System.out.println(ConstructionCodeTest.count);
    }


    /**
     * 等同于
     *  public ConstructionCodeTest() {
            System.out.println("执行构造代码块…………");
            System.out.println("执行无参构造……………");
        }
     */
}

class MyFather{
    {
        System.out.println("父类构造代码块……");
    }
    MyFather(){
        System.out.println("父类构造方法……");
    }
    MyFather(String s){
        this();
        System.out.println("父类构造方法……");
    }
}
