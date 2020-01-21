package myexec;

public interface RejectPolicy {

    void reject(Runnable task, MyThreadPoolExecutor executor);
}
