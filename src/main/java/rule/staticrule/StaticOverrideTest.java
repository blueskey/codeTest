package rule.staticrule;

/**
 * Created by Administrator on 2016/6/15 0015.
 * 静态方法不依赖实例对象，是通过类名访问的；
 * 可以通过 对象访问 静态方法，如果是通过对象访问，JVM会通过对象的表面类型查找到静态方法的入口，继而执行之。所以调用的是被声明的类型的静态方法。
 */
public class StaticOverrideTest {
    public static void main(String[] args) {
        StaticBase base = new StaticSub();
        base.doAnything();
        base.doSomething();//父静态  调用静态方法时，以声明的类型为准，调用声明类的静态方法

        StaticSub sub = new StaticSub();
        sub.doAnything();
        sub.doSomething();//子静态

    }

}

class StaticBase {
    public static void doSomething() {
        System.out.println("父类---静态方法");
    }

    public void doAnything() {
        System.out.println("父类---非静态方法");
    }
}

class StaticSub extends StaticBase {
    @Override
    public void doAnything() {
        System.out.println("子类---非静态方法");

    }

    public static void doSomething() {
        System.out.println("子类---静态方法");
    }

}

