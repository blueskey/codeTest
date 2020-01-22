package thread.tx;

import java.util.concurrent.*;

public class FutureTest{

    public static void main(String[] args) {

        ExecutorService s = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 20; i++) {
            FutureTask<String> f = new FutureTask(new Callable() {
                @Override
                public String call() throws Exception {
                    Thread.sleep(1000);
                    return Thread.currentThread().getName()+"--" + String.valueOf(Math.random());
                }
            });
            s.submit(f);
            try {
                System.out.println(f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        s.shutdown();

    }
}
