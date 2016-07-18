package jvm;

/**
 * Created by Administrator on 2016/7/18 0018.
 * 大对象直接进入老年代。
 * 所谓大对象，指需要大量连续内存空间的java对象，最典型的大对象就是那种长的字符串及数组。写程序时应避免出现一群“朝生夕灭”的“短命大对象”。
 * 虚拟机提供了一个-XX:PertenureSizeThreshold参数，令大于这个设置值的对象直接在老年代中分配，避免在Eden区及两个Survivor区之间发生大量的内存拷贝。
 */
public class BigObject {
    public static final int _1MB=1024*1024;

    /**
     * VM:-XX:+PrintGCDetails -verbose:gc  -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8
     * -XX:PretenureSizeThreshole=3145728 ，（不能表示为3M）
     * @param args
     */
    public static void main(String[] args) {
        byte[] allocation;
        allocation = new byte[4 * _1MB];
    }
}
