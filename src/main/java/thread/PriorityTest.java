package thread;

/**
 * Created by Administrator on 2016/8/22 0022
 * 并不严格按照优先级来执行.
 */
public class PriorityTest implements Runnable {

    public void start(int priority) {
        Thread t = new Thread(this);
        t.setPriority(priority);
        t.start();
    }
    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            Math.hypot(Math.pow(924526789, i), Math.cos(i));
        }

        System.out.println("Priority:" + Thread.currentThread().getPriority());
    }

    public static void main(String[] args) {
        for (int i = 0; i < 30; i++) {
            new PriorityTest().start(i % 10 + 1);
        }
    }
}
