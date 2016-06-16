package rule.construction;

/**
 * Created by Administrator on 2016/6/15 0015.
 * 构造函数之间出现死循环，StackOverflowError
 */
public class ClassInitTest {

    public static void main(String[] args) {
        Son son = new Son();
        son.doSomething();

    }
}

class Father {
    Father() {
        new Other();
    }
}
class Other{
    public Other() {
        new Son();
    }
}

class Son extends Father {
    public void doSomething() {
        System.out.println("hi,show me something");

    }
}