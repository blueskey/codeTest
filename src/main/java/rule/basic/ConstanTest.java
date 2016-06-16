package rule.basic;

import java.util.Random;

/**
 * Created by Administrator on 2016/6/14 0014.
 */
public class ConstanTest {

    public static final long serialVersionUID=2343L;


    public static void integerCompare() {
        System.out.println(new Integer(10) == new Integer(10));
        System.out.println(new Integer(1111111110) == new Integer(1111111110));
        System.out.println(new Integer(10) == 10);
        System.out.println(new Integer(-111110) == new Integer(-111110));
        System.out.println(new Integer(128) == new Integer(128));
        System.out.println(new Integer(127) == new Integer(127));
        System.out.println(new Integer(126) == new Integer(126));
        System.out.println("---------------------------");
        System.out.println(Integer.valueOf(128) == Integer.valueOf(128));
        System.out.println(Integer.valueOf(127) == Integer.valueOf(127));
        System.out.println(Integer.valueOf(126) == Integer.valueOf(126));
    }

    /**
     * 莫让常量蜕变成变量
     */
    public static final int RAND_CONST = new Random().nextInt();

    /**
     * 三元操作符的类型务必一致
     */
    public static void ifElseOperate() {
        int i=80;
        String s = String.valueOf(i < 100 ? 90 : 100);
        String s1 = String.valueOf(i < 100 ? 90 : 100.0);
        System.out.println(s + "\t" + s1 + "\ts=s1?" + s.equals(s1));
    }

    /**
     * 警惕自增陷阱
     */
    public static void testAutoIncrement() {
        int count = 0;
        for (int i = 0; i < 10; i++) {
            count = count++;//
        }
        System.out.println("count=" + count);
    }


    /**
     * java 取余模拟
     * @param dividend 被除数
     * @param divisor 除数
     * @return
     */
    public static int remainder(int dividend,int divisor) {
        return dividend - dividend / divisor * divisor;
    }


    /**
     * 正常
     */
    public static void random() {
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
            System.out.println("第" + i + "次：" + r.nextInt());
        }
    }

    /**
     * 有问题的，每次重复运行，产生的]随机数相同
     */
    public static void random2() {
        /**
         * 随机数与种子间的关系：
         * 1、种子不同，产生不同的随机数；2、种子相同，即使实例不同也产生相同的随机数
         */
        Random r = new Random(100);
        for (int i = 0; i < 4; i++) {
            System.out.println("第" + i + "次：" + r.nextInt());
        }
    }

    public static void main(String[] args) {
        random();
        random2();
//        System.out.println(RAND_CONST);
//        ifElseOperate();
//        testAutoIncrement();
//        integerCompare();
//        System.out.println(-1 % 2);
//        System.out.println(0 % 2);
//        System.out.println(remainder(0,2));
//        System.out.println(remainder(-1,2));
//        System.out.println(10.00-9.6);


    }

}
