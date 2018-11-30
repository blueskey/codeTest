package jvm;

/**
 * Created by Administrator on 2016/7/18 0018.
 * 对象优先在Eden分配
 * 大多数情况下，对象在新生代Eden区中分配，当Eden区没有足够的空间进行分配时，虚拟机将发起一次Minor GC。
 * -XX:+PrintGCDetails 收集器日志参数，告诉虚拟机在发生垃圾收集行为时打印内存回收日志，并且在进程退出进输出当前内存各区域的分配情况。
 */
public class MinorGc {
    public static final int _1MB=1024*1024;

    /**
     * VM:-XX:+PrintGCDetails -verbose:gc  -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8
     * @param args
     */
    public static void main(String[] args) {
//       for(int i=0;i<1000000;i++) {
           byte[] allocation1,allocation2,allocation3,allocation4;
           allocation1 = new byte[2 * _1MB];
           allocation2 = new byte[2 * _1MB];
           allocation3 = new byte[2 * _1MB];
           allocation4 = new byte[4 * _1MB];
//       }
    }
}
