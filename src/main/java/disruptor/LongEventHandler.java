package disruptor;


import com.lmax.disruptor.EventHandler;

/**
 * Created by Administrator on 2016/8/10 0010.
 * 定义事件处理的具体实现
 */
public class LongEventHandler implements EventHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
        System.out.println("Event="+longEvent);
    }
}
