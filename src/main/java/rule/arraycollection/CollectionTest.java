package rule.arraycollection;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

/**
 * Created by Administrator on 2016/6/16 0016.
 * 列表相等只需关心元素数据：可想像为容器不最要，我们只关心里面的元素是不是都一样就可以了
 * 查看equase方法，里面有：if(!(o instanceof List)){return false;}，只要容器实现了List接口即可
 */
public class CollectionTest {

    /**
     * 判断集合是否相等时只须关注元素是否相等即可
     */
    public static void compare() {
        ArrayList<String> strs = Lists.newArrayList();
        strs.add("a");
        strs.add("asd");

        Vector<String> str2 = new Vector<String>();
        str2.add("a");
        str2.add("asd");
        //列表相等只需关心元素数据
        System.out.println(str2.equals(strs));//true

    }

    /**
     * subList()返回的也是一个内部类SubList，它重写的add，remove等都是在原list上的操作，所以它改变了，原list也改变了
     * 相当于原list的一个视图，所有修改动作都反映在了原list上。
     */
    public static void subList() {
        List<String> c = new ArrayList<String>();
        c.add("a");
        c.add("asd");

        //通过构造函数创建的list是新的列表（通过数组的copyOf生成），与原列表无关系了。这里虽然是浅拷贝，但因为元素是String，所以是深拷贝
        List<String> c1 = new ArrayList<String>(c);

        List<String> c2 = c.subList(0, c.size());

        c2.add("C");
        System.out.println("c ==c1?--" + c.equals(c1));//false
        System.out.println("c ==c2?--" + c.equals(c2));//true
    }

    /**
     * 删除一个列表索引位置为20-30的数据，利用subList快速实现
      */
    public static void subRemov() {
        List<Integer> integers = Collections.nCopies(100, 0);
        ArrayList<Integer> list = new ArrayList<Integer>(integers);
        list.subList(20, 30).clear();
    }

    public static void main(String[] args) {
        compare();
    }
}
