package observe;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author ju
 */
public class Mather implements GCObject<Child>  {
//
    private List<Child> childrens = Lists.newArrayList();
//
//    public void addOrder(Child c) {
//        childrens.add(c);
//        System.out.println("记得叫我吃饭。。。");
//    }
//
//    public void finishLunch() {
//        System.out.println("饭好了。回来吃饭.......");
//        childrens.forEach(c -> c.receiveFunchMsg());
//    }


    @Override
    public void add(Child child) {
        childrens.add(child);
    }

    @Override
    public void notifyAllGc() {
        System.out.println("饭好了。回来吃饭.......");
        childrens.forEach(c -> c.receiveFunchMsg());
    }

    @Override
    public void remove(Child child) {
        if (childrens.contains(child)) {
            childrens.remove(child);
            System.out.println("以后不用再叫我吃饭了。。");
        }
    }
}
