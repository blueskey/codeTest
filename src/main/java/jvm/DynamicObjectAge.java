package jvm;

/**
 * Created by Administrator on 2016/7/18 0018.
 *
 * 动态对象年龄判定
 *
 * 为了更好地不同程序的内存状况，虚拟机并不总是要求对象的年龄必须达到MaxTenuringThreshold才能晋升到老年代。
 * 如果在Survivor空间中相同年龄所有对象大小的总和大于Survivor空间的一半，年龄大于或等于该年龄的对象就可以直接进入老年代，
 * 无须等到MaxTenuringThreshold中要求的年龄
 */
public class DynamicObjectAge {

    public static final int _1MB=1024*1024;

    /**
     * VM:-XX:+PrintGCDetails -verbose:gc  -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8
     * -XX:+PrintTenuringDistribution
     * -XX:MaxTenuringThreshold=15
     * @param args
     */
    public static void main(String[] args) {
        byte[] allocation1,allocation2,allocation3,allocation4;
        allocation1 = new byte[_1MB/4];
        // allocation1+allocation2大于survivor空间的一半
        allocation2 = new byte[4 * _1MB];
        allocation3 = new byte[4 * _1MB];
        allocation3 = null;
        allocation4 = new byte[4 * _1MB];
    }


}
