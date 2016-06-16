package rule.arraycollection;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/6/16 0016.
 *  性能要求较高的场景中，使用数组代替集合
 *  实际开发中，如果确实需要变长的数据集，数组也是可以考虑的。可以扩容。
 *  警惕数组的浅拷贝：通过copyOf方法产生的数组是一个浅拷贝：基本类型是直接拷贝值，其他都是拷贝引用地址。
 *  数组的clone方法和集合的clone也是如此，都是浅拷贝。
 *
 */
public class ArrayTest {

    /**
     * 数组扩容
     * @param datas
     * @param newLength
     * @param <T>
     * @return
     */
    public static <T> T[] expandCapacity(T[] datas, int newLength) {
        newLength = newLength < 0 ? 0 : newLength;//不能是负值
        return Arrays.copyOf(datas, newLength);
    }


    /**
     * asList陷阱：类型数组不能作为asList方法的输入参数，否则会引起程序逻辑混乱
     *
     */
    public static void arrayToList() {
        int[] data = {1, 2, 3, 4, 5};
        //asList入参是一个泛型变长参数，基本类型不能泛型化，但是数组是对象，可以泛型化，这里相当于把int[]作为了泛型的类型
        List list = Arrays.asList(data);
        //list 长度为1，且list.get(0)为int[]。
        System.out.println(list.size()+"----------"+ list.get(0).getClass() );


        Integer[] data2 = {1, 2, 3, 4, 5};
        List list2 = Arrays.asList(data2);
        System.out.println(list2.size()+"----------"+ list2.get(0).getClass() );
    }

    public static void main(String[] args) {
        arrayToList();
    }
}
