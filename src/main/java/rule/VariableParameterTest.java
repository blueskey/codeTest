package rule;

/**
 * Created by Administrator on 2016/6/14 0014.
 */
public class VariableParameterTest {
    public static void main(String[] args) {
        Base base = new Base();
        base.fun(100, 30);
        Base sub = new Sub();
        sub.fun(100,30);
    }
}

class Base{
    void fun(int price, int... discounts) {
        System.out.println("Base ...fun");
    }
}

class Sub extends Base {
    @Override
    void fun(int price, int[] discounts) {
        System.out.println("Sub ...fun");
    }
}