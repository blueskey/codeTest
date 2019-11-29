package thread;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Cy implements Runnable{

    private static int count=1000;

    @Override
    public void run() {

        System.out.println(count--);
    }


    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(1000);

        List<Thread> threadList = Lists.newArrayList();

        for (int i = 0; i < 1000; i++) {
            threadList.add(new Thread(new Cy()));
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        System.out.println("开始");

        for (Thread thread : threadList) {
            thread.start();
        }
    }
}
