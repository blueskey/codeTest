package thread;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2016/8/22 0022.
 *
 * CountDownLatch的作用是控制一个计数器，每个线程在运行完毕后会执行countDown，表示自己运行结束，
 * 这对于多个子任务的计算特别有效，比如一个异步任务需要拆分成10个子任务执行，
 * 主任务必须要知道子任务是否完成，所有子任务完成后才能进行合并计算，从而保证了一个主任务的逻辑正确性。
 */
public class Runner implements Callable<Integer> {

    //开始信号
    private CountDownLatch begin;
    //结束信号
    private CountDownLatch end;

    public Runner(CountDownLatch begin, CountDownLatch end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    public Integer call() throws Exception {
        //跑步成绩
        int score = new Random().nextInt(25);
        //等待发令枪响起
        begin.await();

        //跑步中……
        TimeUnit.MILLISECONDS.sleep(score);
        //跑步者已跑完全程
        end.countDown();
        return score;
    }

    public static void main(String[] args) throws Exception {

        int num = 10;

        //发令枪只响一次
        CountDownLatch begin = new CountDownLatch(1);
        //参与跑步的人有多个
        CountDownLatch end = new CountDownLatch(num);

        ExecutorService es = Executors.newFixedThreadPool(num);

        List<Future<Integer>> futures = Lists.newArrayList();

        for (int i = 0; i < num; i++) {
            futures.add(es.submit(new Runner(begin, end)));
        }

        begin.countDown();
        end.await();

        int count = 0;
        for (Future<Integer> f : futures) {
            System.out.println("同学成绩："+f.get());
            count = count + f.get();
        }
        System.out.println("平均分为：" + count / num);
    }
}
