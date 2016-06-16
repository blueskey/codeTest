package rule.basic;

/**
 * Created by Administrator on 2016/6/14 0014.
 * 覆写变长方法也循规蹈矩
 * 覆写必须满足：
 *      重写方法不能缩小访问权限
 *      参数列表必须与被重写方法相同 （参数列表相同包括：参数数量相同 、类型相同 、顺序相同）
 *      返回类型必须与被重写方法相同或是其子类
 *      重写方法不能抛出新的异常，或者超出父类范围的异常，但是可以抛出更少、更有限或者不抛出异常。
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