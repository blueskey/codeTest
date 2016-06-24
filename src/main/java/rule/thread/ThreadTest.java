package rule.thread;

/**
 * Created by Administrator on 2016/6/23 0023.
 * 继承自Thread类的多纯种类不必覆写start方法
 */
public class ThreadTest extends Thread{
    /**
     * 没有必要覆写start方法
     */
    @Override
    public synchronized void start() {
        //如果要覆写，一定要加上这名话，否则不会启动一个新线程。
        // 因为super.start()里而有一个本地方法start(),它实现了启动了线程、申请栈内存、运行run方法等职责……
//       super.start();
        run();
    }

    @Override
    public void run() {
        System.out.println("sss");
    }

    public static void main(String[] args) {
        new ThreadTest().start();//如果覆写的start方法里没有super.start()，则不是多线程方式
    }
}
