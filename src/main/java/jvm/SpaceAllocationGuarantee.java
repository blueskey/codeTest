package jvm;

/**
 * Created by Administrator on 2016/7/18 0018.
 * 空间分配担保
 *
 * 发生Minor GC时，虚拟机会检测之前每次晋升到老年代的平均大小（因为不知道一共会有多少对象活下来，所以只好取之前的平均值作为经验值）是否大于老年代的剩余空间大小，
 * 如果大于，则改为直接进行一次Full GC。小于，则查看HandlePromotionFailure设置是否允许担保失败；
 * 如果允许，那只会进行Minor GC；如果不允许，则也要改为进行一次Full GC。
 *
 *  如果其次Minor GC存活后的对象突增，远远高于平均值的话，依然会导致担保失败。如果出现失败，只能在失败后重新发起一次Full GC .
 *
 */
public class SpaceAllocationGuarantee {

    public static final int _1MB=1024*1024;

    /**
     * VM:-XX:+PrintGCDetails -verbose:gc  -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8
     * -XX:-HandlePromotionFailure=true
     * -XX:-HandlePromotionFailure=false
     *
     * @param args
     */
    public static void main(String[] args) {
        byte[] allocation1,allocation2,allocation3,allocation4,allocation5,allocation6,allocation7;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[4 * _1MB];
        allocation3 = new byte[4 * _1MB];
        allocation1 = null;
        allocation4 = new byte[2 * _1MB];
        allocation5 = new byte[2 * _1MB];
        allocation6 = new byte[2 * _1MB];
        allocation4 = null;
        allocation5 = null;
        allocation6 = null;

        allocation7 = new byte[2 * _1MB];
    }

}
