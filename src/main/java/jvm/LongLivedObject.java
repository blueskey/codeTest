package jvm;

/**
 * Created by Administrator on 2016/7/18 0018.
 *
 * 长期戚的对象将进入老年代
 *
 * 虚拟机给每个对象定义了一个对象年龄计数器。如果对象在Eden出生并经过第一次Minor GC后仍然存活，
 * 并且能被Survivor容纳的话，将被移动到Survivor空间中，并将对象年龄设为1.
 * 对象在Survivor区中每熬过一次Minor GC，年龄就增加1岁，当年龄增加到一定程度（默认15），就会被晋升到老年代中。
 * 对象晋升到老年代的年龄阈值，可以通过参数-XX:MaxTenuringThreshold来设置。
 */
public class LongLivedObject {

    public static final int _1MB=1024*1024;

    /**
     * VM:-XX:+PrintGCDetails -verbose:gc  -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintTenuringDistribution
     * -XX:MaxTenuringThreshold=1 // 什么时候进入老年代取决于XX:maxTenuringThreshold设置
     * -XX:MaxTenuringThreshold=15 // 什么时候进入老年代取决于XX:maxTenuringThreshold设置
     * @param args
     */
    public static void main(String[] args) {
        byte[] allocation1,allocation2,allocation3,allocation4;
        allocation1 = new byte[_1MB/4];
        allocation2 = new byte[4 * _1MB];
        allocation3 = new byte[4 * _1MB];
        allocation3 = null;
        allocation4 = new byte[4 * _1MB];
    }


}
