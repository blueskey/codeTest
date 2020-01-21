package myexec;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {

    public static void main(String[] args) {
        Executor myExecutor = new MyThreadPoolExecutor("test", 5, 10, new ArrayBlockingQueue<>(15), new DiscardRejectPolicy());

        AtomicInteger num = new AtomicInteger(0);

        for (int i = 0; i < 100; i++) {
            myExecutor.execute(()->{
                try {
                    Thread.sleep(1000);
                    System.out.println("运行：" + System.currentTimeMillis() + ":" + num.incrementAndGet());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }
    }
}
