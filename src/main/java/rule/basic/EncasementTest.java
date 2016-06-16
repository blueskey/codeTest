package rule.basic;

/**
 * Created by Administrator on 2016/6/14 0014.
 */
public class EncasementTest {

    public static void main(String[] args) {
        EncasementTest e = new EncasementTest();
        int i = 100;
        //基本类型可以先加宽，再转变宽类型的包装类型，但不能直接转变成宽类型的包装类型
        e.f(i);
        //
        e.f(Integer.valueOf(i));
    }

    public void f(long a) {
        System.out.println("基本类型的方法被调用");

    }


    private void f(Long a) {
        System.out.println("包装类的方法被调用");

    }


}
