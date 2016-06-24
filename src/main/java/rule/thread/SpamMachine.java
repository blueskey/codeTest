package rule.thread;

/**
 * Created by Administrator on 2016/6/23 0023.
 */
public class SpamMachine extends Thread {
    @Override
    public void run() {
        System.out.println("制造大量垃圾邮件……");
    }

    public static void main(String[] args) {
        while (true) {
            SpamMachine sm = new SpamMachine();
            if (!false) {
                sm.stop();
            }
            sm.start();

        }
    }
}
