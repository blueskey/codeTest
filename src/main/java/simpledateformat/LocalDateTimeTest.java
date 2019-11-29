package simpledateformat;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeTest {

    /**
     * 获取当前时间
     */
    @Test
    public void testNow() {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
    }

    /**
     * 根据时间戳初始化时间
     */
    @Test
    public void testNewFromTimestamp() {
        Instant instant = Instant.ofEpochMilli(System.currentTimeMillis());
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.of("+8"));
        System.out.println(dateTime);
    }

    @Test
    /**
     * 根据字符串获取时间
     */
    public void testNewFromString() {
        // 1.默认格式 2019-05-06T11:16:12.361
        String dateStr = "2019-05-06T11:16:12.361";
        LocalDateTime localDateTime = LocalDateTime.parse(dateStr);
        System.out.println(localDateTime);
        // 2. 自定义格式
        String pattern = "yyyy-MM-dd HH:mm:ss";
        dateStr = "2019-01-01 12:12:12";
        localDateTime = LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern(pattern));
        System.out.println(localDateTime);
    }

    @Test
    /**
     * 时间转化成字符串
     */
    public void testToString() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("+8"));
        String pattern = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        String format = formatter.format(now);
        System.out.println(format);
    }

    /**
     * LocalDateTime转时间戳
     */
    @Test
    public void testDateToTimeMillis() {
        LocalDateTime dateTime = LocalDateTime.now();
        long epochMilli = dateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        System.out.println(epochMilli);
    }
}
