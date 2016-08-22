package thread;

/**
 * Created by Administrator on 2016/8/22 0022.
 */
public class TcpServer implements Runnable {

    public TcpServer() {
        Thread t = new Thread(this);
        t.setUncaughtExceptionHandler(new TcpServerExceptionHandler());
        t.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(1000);
                System.out.println("系统正常运行...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        throw new RuntimeException();
    }

    private static class TcpServerExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("线程"+t.getName()+"出现异常，自行重启，请分析原因");
            e.printStackTrace();
            new TcpServer();
        }
    }

    public static void main(String[] args) {
        new TcpServer().run();

    }
}
