package disruptor;


import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/8/10 0010.
 */
public class DisruptorTest {
    public static void main(String[] args) {
        EventFactory<LongEvent> eventEventFactory = new LongEventFactory();
        //Disruptor 通过 java.util.concurrent.ExecutorService 提供的线程来触发 Consumer 的事件处理
        ExecutorService executor = Executors.newSingleThreadExecutor();
        int ringBufferSize=1024*1024;
        //Disruptor 提供了多个 WaitStrategy 的实现，每种策略都具有不同性能和优缺点，根据实际运行环境的 CPU 的硬件特点选择恰当的策略，并配合特定的 JVM 的配置参数，能够实现不同的性能提升。
        //例如，BlockingWaitStrategy、SleepingWaitStrategy、YieldingWaitStrategy 等，其中，
        //BlockingWaitStrategy 是最低效的策略，但其对CPU的消耗最小并且在各种不同部署环境中能提供更加一致的性能表现；
        //SleepingWaitStrategy 的性能表现跟 BlockingWaitStrategy 差不多，对 CPU 的消耗也类似，但其对生产者线程的影响最小，适合用于异步日志类似的场景；
        //YieldingWaitStrategy 的性能是最好的，适合用于低延迟的系统。在要求极高性能且事件处理线数小于 CPU 逻辑核心数的场景中，推荐使用此策略；例如，CPU开启超线程的特性。
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(eventEventFactory, ringBufferSize, executor, ProducerType.SINGLE, new YieldingWaitStrategy());
        EventHandler<LongEvent> eventEventHandler = new LongEventHandler();
        disruptor.handleEventsWith(eventEventHandler);
        disruptor.start();

        //发布事件
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        long sequence = ringBuffer.next();//请求下一个事件序号
        try {
            LongEvent event = ringBuffer.get(sequence);//获取该序号对应的事件对象
            long data = getEventData();
            event.setValue(data);
        } finally {
            ringBuffer.publish(sequence);//有异常也照常发布
        }

        disruptor.shutdown();//关闭disruptor,方法会堵塞，直到所有的事件都得到处理
        executor.shutdown();//关闭disruptor使用的线程池；如果需要的话，必须手动关闭，disruptor在shutdown时不会自动关闭
    }

    private static long getEventData() {
        return 12;
    }

}
