package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/8/22 0022.
 *
 * 线程池缩减线程创建和销毁时间。
 *
 * 线程池的创建过程：创建一个阻塞队列以容纳任务，在第一次执行任务时创建足够多的线程（不超过许可线程数），
 * 并处理任务，之后每个工作线程自行从任务队列中获得任务，直到任务队列中的任务数量为0为止，此时，线程将处于等待状态，
 * 一旦有任务再加入到队列中，即唤醒工作线程进行处理，实现线程的可复用性
 *
 * 线程池中的线程，只有２个状态：可运行状态和等待状态；
 * 任务接口：每个任务必须实现的接口，以供工作线程调度器调度。
 *      有２种类型的任务：具有返回值（或异常）的Callable接口任务和无返回值并兼容旧版本的Runnable接口任务；
 * 任务队列：用于存放等待处理的任务，一般是BlockingQueue实现类，用于实现任务的排队处理；
 */
public class ThreadPool {

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 4; i++) {
            es.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());

                }
            });
        }

        es.shutdown();
    }
}
