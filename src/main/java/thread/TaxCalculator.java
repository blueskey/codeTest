package thread;

import java.util.concurrent.*;

/**
 * Created by Administrator on 2016/8/22 0022.
 *
 * 异步运算考虑使用Callable接口
 *
 */
public class TaxCalculator implements Callable<Integer> {

    private int seedMoney;

    public TaxCalculator(int seedMoney) {
        this.seedMoney = seedMoney;
    }

    @Override
    public Integer call() throws Exception {
        TimeUnit.MILLISECONDS.sleep(10000);
        return seedMoney / 10;
    }

    public static void main(String[] args) throws Exception{
        ExecutorService es = Executors.newSingleThreadExecutor();

        Future<Integer> future = es.submit(new TaxCalculator(100));

        while (!future.isDone()) {
            TimeUnit.MILLISECONDS.sleep(200);
            System.out.print("#");

        }
        System.out.println("\n计算完成，锐金：" + future.get() + "元");
        es.shutdown();

    }
}
