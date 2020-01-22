package thread.tx;

import java.util.ArrayList;
import java.util.List;

public class ThreadJoin implements Runnable {

    @Override
    public void run() {

        for (int i = 0; i <= 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println(Thread.currentThread().getName()+"完成。。");
    }

    public static void main(String[] args) {
        List<Thread> l = new ArrayList<>();
        for (int i = 0; i <= 4; i++) {
            try {
                l.add(new Thread(new ThreadJoin()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (Thread a : l) {
            a.start();
        }
        for (Thread a : l) {
            try {
                a.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("每个thread都完成了，我可以下班了。。");



    }
}
