package rule;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/15 0015.
 * 匿名类
 */
public class AnonymityClass {

    public static void main(String[] args) {
        List list = new ArrayList();
        List list1=new ArrayList(){

        };//代表一个匿名类的声明和赋值，定义了一个继承自ArrayList的匿名类，只是没有任何覆写方法
        List list2=new ArrayList(){{}};//和上面一样，只是在上面的基础上，匿名类多了一个构造函数块（也是构造函数，因为匿名类没有名字，初始化块就是它的构造函数，可以有多个，如List list3=new ArrayList(){{}{}{}};）

        System.out.println(list.getClass() == list1.getClass());//false
        System.out.println(list1.getClass() == list2.getClass());//false
        System.out.println(list.getClass() == list2.getClass());//false ，虽然父类相同，但类还是不同的

        /**
         * 匿名类的构造函数特殊处理机制：一般类（具有显式名字的类）的所有构造函数默认都是调用父类的无参构造，
         * 而匿名类因为没有名字，只能由构造代码块代替，也是说无所谓有参无参，它在初始化时调用了的类的同参构造，然后再调用自己的构造代码块。
         */
        Calulator calulator = new Calulator(1, 3){
            {
                setOperator(Ops.ADD);
            }
        };
        System.out.println(calulator.getResult());

    }
}


enum Ops{ADD, SUB}

class Calulator {
    private int i,j,result;
    public Calulator() {

    }

    public Calulator(int j, int i) {
        this.j = j;
        this.i = i;
    }

    protected void setOperator(Ops operator) {
        result=operator.equals(Ops.ADD)?i+j:i-j;
    }

    public int getResult() {
        return result;
    }
}