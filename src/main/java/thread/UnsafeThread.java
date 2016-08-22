package thread;

/**
 * Created by Administrator on 2016/8/22 0022.
 */
public class UnsafeThread implements Runnable {

    private volatile int count=0;
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            Math.hypot(Math.pow(92456789,i),Math.cos(i));

        }
        count++;
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) throws Exception{
        int value=1000;

        int loops=0;

        ThreadGroup tg = Thread.currentThread().getThreadGroup();
        while (loops++ < value) {
            UnsafeThread ut = new UnsafeThread();
            for (int i = 0; i < value; i++) {

                new Thread(ut).start();

            }

            do {
                Thread.sleep(15);

            } while (tg.activeCount() != 1);

            if (ut.getCount() != value) {
                System.out.println("循环到第" + loops + "遍 ，出现线程不安全情况");
                System.out.println("此时，count=" + ut.getCount());
                System.exit(0);

            }
        }
    }
}
