package thread;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by Administrator on 2016/8/22 0022.
 *
 * CyclicBarrier关卡可以让所有线程全部处于等待状态（阻塞），
 * 然后在满足条件的情况下继续执行，这就好比是一条起跑线，
 * 不管是如何到达起跑线的，只要到达这条起跑线就必须等待其他人员，待人员到齐后再各奔东西，
 * CyclicBarrier关注的是汇合点的信息，而不在乎之前或之后做何处理。
 */
public class Worker implements Runnable {

    //关卡
    private CyclicBarrier cb;

    public Worker(CyclicBarrier cb) {
        this.cb = cb;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(new Random().nextInt(1000));
            System.out.println(Thread.currentThread().getName() + "--到达汇合点");
            //到达汇合点
            cb.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //设置汇集数量，以集汇集完成后的任务
        CyclicBarrier cb = new CyclicBarrier(2, new Runnable() {
            @Override
            public void run() {

                System.out.println("隧道已打通！");

            }
        });

        new Thread(new Worker(cb), "工人1").start();
        new Thread(new Worker(cb), "工人2").start();
    }
}
