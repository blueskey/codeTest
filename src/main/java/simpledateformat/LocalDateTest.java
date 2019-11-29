package simpledateformat;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

public class LocalDateTest {

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        System.out.println("localDate=" + localDate);

        LocalDate.of(2019, 10, 1);

        LocalDate.parse("2019-11-01");
        //明天
        LocalDate tomorrow = LocalDate.now().plusDays(1);

        //从今天减去一个月
        LocalDate prevMonth = LocalDate.now().minus(1, ChronoUnit.MONTHS);

        //解析日期 2019-11-29，获取每周中的星期和每月中的日：
        DayOfWeek thursday = LocalDate.parse("2019-11-29").getDayOfWeek();

        System.out.println("周四: " + thursday);

        int twenty = LocalDate.parse("2019-11-29").getDayOfMonth();

        System.out.println("twenty: " + twenty);

        //今年是不是闰年
        boolean leapYear = LocalDate.now().isLeapYear();

        System.out.println("是否闰年: " + leapYear);

        //判断是否在日期之前或之后:
        boolean notBefore = LocalDate.parse("2019-11-29")

                .isBefore(LocalDate.parse("2019-11-29"));

        System.out.println("notBefore: " + notBefore);

        boolean isAfter = LocalDate.parse("2019-11-29").isAfter(LocalDate.parse("2019-11-29"));

        System.out.println("isAfter: " + isAfter);

        //获取这个月的第一天
        LocalDate firstDayOfMonth = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("这个月的第一天: " + firstDayOfMonth);
        firstDayOfMonth = firstDayOfMonth.withDayOfMonth(1);

        System.out.println("这个月的第一天: " + firstDayOfMonth);


        //判断今天是否是我的生日，例如我的生日是 2009-07-20
        LocalDate birthday = LocalDate.of(2009, 07, 20);

        MonthDay birthdayMd = MonthDay.of(birthday.getMonth(), birthday.getDayOfMonth());

        MonthDay today = MonthDay.from(LocalDate.now());

        System.out.println("今天是否是我的生日: " + today.equals(birthdayMd));

    }
}
