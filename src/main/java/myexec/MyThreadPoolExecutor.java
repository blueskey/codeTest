package myexec;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThreadPoolExecutor implements Executor {

    /**
     * 线程池名称
     */
    private String name;

    /**
     * 核心线程数
     */
     private int coreSize;

     /**
      * 最大线程数
      */
      private int maxSize;

    /**
     * 任务队列
     */
    private BlockingQueue<Runnable>  taskQueue;

    /**
     * 线程序列号
     */
    private AtomicInteger sequence = new AtomicInteger(0);

    /**
     * 当前正在运行的线程数
     */
    public AtomicInteger runningCount = new AtomicInteger(0);

    /**
     * 拒绝策略
     */
    public RejectPolicy rejectPolicy;

    public MyThreadPoolExecutor(String name, int coreSize, int maxSize, BlockingQueue<Runnable> taskQueue, RejectPolicy rejectPolicy) {
        this.name = name;
        this.coreSize = coreSize;
        this.maxSize = maxSize;
        this.taskQueue = taskQueue;
        this.rejectPolicy = rejectPolicy;
    }

    @Override
    public void execute(Runnable task) {

        int count = runningCount.get();

        if (count < coreSize) {

            if (addWorker(task, true)) {
                return;
            }
            // 如果添加核心线程失败，进入下面的逻辑
        }
        //如果达到了核心线程数，先尝试让任务入队
        //这里之所以使用offer()，是因为如果队列满了offer()会立即返回false
        if (taskQueue.offer(task)) {

        }else {
            //如果入队失败，说明队列满了，那就添加一个非核心线程
            if (!addWorker(task, false)) {
                //如果添加非核心线程失败了，那就执行拒绝策略
                rejectPolicy.reject(task, this);
            }
        }
    }

    private boolean addWorker(Runnable newTask, boolean core) {

        // 自旋判断是不是真的可以创建一个线程
        for (; ; ) {
            int count = runningCount.get();
            int max = core ? coreSize : maxSize;

            if (count >= max) {
                return false;
            }
            // 修改runningCount成功，可以创建线程
            if (runningCount.compareAndSet(count, count + 1)) {
                String threadName = (core ? "core_" : "") + name + sequence.getAndIncrement();

                new Thread(() -> {
                    System.out.println("创建线程：" + Thread.currentThread().getName());
                    Runnable task = newTask;

                    while (task != null || (task = getTask()) != null) {

                        try {
                            task.run();
                        } finally {
                            //任务执行完成，置为空
                            task = null;
                        }
                    }

                },threadName).start();
                break;
            }
        }
        return true;
    }

    private Runnable getTask() {
        try {
            // take()方法会一直阻塞直到取到任务为止
            return taskQueue.take();
        } catch (InterruptedException e) {
            //线程中断了，返回null可以结束当前线程
            //当前线程都要结束了，理应要把runningCount的数量减一
            e.printStackTrace();
            runningCount.decrementAndGet();
            return null;
        }
    }
}
