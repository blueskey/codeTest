package rule;

import java.util.Date;

/**
 * Created by Administrator on 2016/6/14 0014.
 */
public class InstanceOfTest {
    public static void main(String[] args) {
        boolean b1 = "string" instanceof String;
        boolean b2 = new String() instanceof String;
        boolean b3 = new Object() instanceof String;

//        boolean b4 = 'A' instanceof Character;//编译不通过，instanceof只能用于对象的判断，不能用于基本类型的判断
        boolean b5 = null instanceof String;//A instanseof B ，A为null，则就返回false
        boolean b6 = (String) null instanceof String;
//        boolean b7 = new Date() instanceof String;//编译不通过，A instanseof B ，AB没有继承关系

        boolean b8=new GenericClass<String>().isDateInstance("");
        boolean b9=new GenericClass<Date>().isDateInstance(new Date());

        System.out.println(b1+","+b2+","+b3+","+b5+","+b6+","+","+b8+","+b9);
    }
}

class GenericClass<T>{
    public boolean isDateInstance(T t) {
        return t instanceof Date;//泛型，在编译时，T变为Object了，实际等价于Object instanceof Date
    }
}