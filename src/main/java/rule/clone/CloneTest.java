package rule.clone;

/**
 * Created by Administrator on 2016/6/16 0016.
 * 一个类实现了Cloneable接口就表具备了拷贝的能力，如果再覆写clone()方法就会完全具备拷贝的能力。
 拷贝在内存中进行，所以在性能方面比直接new生成对象要快，尤其是大对象的时候。
 浅拷贝：Object提供的对象拷贝方法。不会把对象的所有属性全部拷贝一份，而是有选择性的拷贝，拷贝规则如下：
    （1）基本类型：拷贝其值；
    （2）对象：拷贝地址引用，即新拷贝出的对象与原在对象共享该实例变量，不受访问权限限制：一个private修饰的变量，竟然可能被两个不同的实例对象访问！
    （3）String字符串：拷贝的也是一个地址，是个引用 ，但在修改时，会从字符串池中重新生成新的字符串，原有字符串对象保持不变，在此可以认为String是一个基本类型。
 */
public class CloneTest {

    public static void main(String[] args) {
        ClonePerson f = new ClonePerson("爸爸");
        ClonePerson s1 = new ClonePerson("大儿子", f);
        ClonePerson s2 = s1.clone();
        s2.setName("小儿子");

        s1.getFather().setName("新爸爸");

        System.out.println(s1.getName()+"----"+s1.getFather().getName());
        System.out.println(s2.getName()+"----"+s2.getFather().getName());

    }
}

class ClonePerson implements Cloneable {
    private String name;
    private ClonePerson father;

    public ClonePerson(String name, ClonePerson father) {
        this.name = name;
        this.father = father;
    }

    public ClonePerson(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClonePerson getFather() {
        return father;
    }

    public void setFather(ClonePerson father) {
        this.father = father;
    }

//    /**
//     * 浅拷贝，一个儿子认新爸爸了，另一个也认了^^
//     * @return
//     */
//    @Override
//    protected ClonePerson clone()  {
//            ClonePerson person = null;
//            try {
//                person = (ClonePerson) super.clone();
//            } catch (CloneNotSupportedException e) {
//                e.printStackTrace();
//            }
//            return person;
//    }

    @Override
    protected ClonePerson clone(){
        ClonePerson person = null;
        try {
            person = (ClonePerson) super.clone();
            /**
             * 为使实例对象之间不受影响，需要生成自己的，各自爸爸对象不受影响
             */
            person.setFather(new ClonePerson(person.getFather().getName()));
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return person;
    }
}

