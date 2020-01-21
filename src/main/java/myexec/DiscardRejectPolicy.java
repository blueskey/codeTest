package myexec;

public class DiscardRejectPolicy implements RejectPolicy {

    @Override
    public void reject(Runnable task, MyThreadPoolExecutor executor) {
        System.out.println("丢弃...");
    }
}
