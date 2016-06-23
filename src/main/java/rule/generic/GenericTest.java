package rule.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/22 0022.
 *
 * 泛型的class对象是相同的，
 * 泛型数组初始化时不能声明泛型类型
 * instanceof不允许存在泛型参数
 */
public class GenericTest {
    static {
        System.out.println("Do GenericTest static code ....");
    }
}

class Foo {
    public void arrayMethod(String[] strArray) {

    }

    public void arrayMethod(Integer[] intArray) {

    }


//    /**
//     * 法Ｂ
//     * @param intList
//     */
//    public void listMethod(List<Integer> intList) {
//
//    }

    /**
     * 法Ａ
     * 编译不通过，泛型在编译时会擦除，两个方法（法A与法B）都成了listMethod(List<E> )
     *
     * 编译后所有泛型类型都会做相应转化，转换规则如下：
     *      List<String>、List<Integer>、List<T>擦除后的类型为List；
     *      List<String>[] 擦除后的类型为List[]；
     *      List<？extends E>、List<？super E>  擦除后的类型为List<E>；
     *      List<？extends Serializable & Cloneable> 擦除后的类型为List<Serializable>；
     * @param stringList
     */
    public void listMethod(List<String> stringList) {

    }

    /**
     * 泛型结构只参与“读”操作则限定上界（extends）
     */
    public static <E> void read(List<? extends E> list) {
        for (E e : list) {
            //
        }
    }


    /**
     * 泛型结构只参与“写”操作则限定下界（super）
     *
     *  public static void write(List<? extends Number> list) {
        list.add(32);
        }编译失败，因为编译器无法推断出泛型类型到底是什么,这时，只有list(null)可以成功
     */
    public static void write(List<? super Number> list) {
        list.add(32);
    }

    public static void main(String[] args) {
        List<String> ls = new ArrayList<String>();
        List<Integer> li = new ArrayList<Integer>();
        //泛型的class对象是相同的，
        System.out.println(li.getClass()==ls.getClass());//true，List<String>和List<Integer>擦除后的类型都是List,没有区别

        //编译不通过，可以声明一个带有泛型参数的数组，但不能初始化该数组，因为执行了类型擦除操作，List<Object>[]与List<String>[] 就是同一回事，编译器拒绝如此声明
//        List<String>[] listArray = new List<String>[3];//编译不通过
        List<String>[] listArray1;

        List<String> list = new ArrayList<String>();
        //编译不通过，instanceof不允许存在泛型参数
//        System.out.println(list instanceof List<String>);//编译不通过
        System.out.println(list instanceof List);
    }
}
