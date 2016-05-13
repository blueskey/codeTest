package thread;

/**
 * Created by Administrator on 2016/3/2 0002.
 */
public class TestThread implements Runnable {

    private int i;

    public TestThread(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("do nothing..." + i);

    }

    public static void main(String[] args) {
        for (int i = 0; i <= 100; i++) {
            new TestThread(i).run();

        }
    }
}
