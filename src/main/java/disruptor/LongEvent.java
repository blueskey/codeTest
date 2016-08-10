package disruptor;

/**
 * Created by Administrator on 2016/8/10 0010.
 * 定义事件，事件就是通过Disruptor进行交换的数据类型
 */
public class LongEvent {

    private long value;

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
