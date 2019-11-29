package thread;

import java.util.concurrent.CountDownLatch;

public class Countdown implements Runnable{

    private CountDownLatch countDownLatch;

    private static int count=1000;

    public Countdown(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
            System.out.println(Thread.currentThread().getName()+"到达．．．");
            try {
                countDownLatch.countDown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(count--);
    }


    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1000);

        for (int i = 0; i < 1000; i++) {
            new Thread(new Countdown(countDownLatch)).start();
        }

        System.out.println("开始");
    }
}

