package rule.enumannotation;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/6/22 0022.
 * 1、小心switch带来的空值异常
 * 2、在switch的default代码块中增加AssertionError错误
 */
public class enumTest {
    public static void main(String[] args) {
//        doSports(null);
        valuesOfTest();
    }

    /**
     * 传入null时，会空指针
     * 编译时，编译器判断出switch语句后的参数是枚举，然后会根据枚举的排序值继续匹配。相当于
     switch (season.ordinal()) {
        case Season.SPRING.ordinal():
             System.out.println("放风筝");
            break;
        case Season.SUMMER.ordinal():
            System.out.println("游泳");
            break;
        ...
     }
     当传入null时，无法调用season.ordinal()，所以就空指针了。
     *
     * @param season
     */
    public static void doSports(Season season) {

        switch (season) {
            case SPRING:
                System.out.println("放风筝");
                break;
            case SUMMER:
                System.out.println("游泳");
                break;
            case AUTUMN:
                System.out.println("捉知了");
                break;
            case WINTER:
                System.out.println("滑冰");
                break;
            default:
                System.out.println("这是个什么季节？");
        }
    }


    /**
     * 枚举的valueOf，会把一个String类型的名称转变为枚举项，即在枚举项中查找出字面值与该参数相等的枚举项
     * 如果没有与该值相匹配的枚举值，则抛异常：如下Exception in thread "main" java.lang.IllegalArgumentException: No enum constant rule.enumannotation.Season.summer
     * 解决办法：1、try...catch异常　
     *          ２、扩展枚举类，增加一个方法用来判断是否包含与该String值相等的枚举值，在使用valueOf前判断一下。
     */
    public static void valuesOfTest() {
        List<String> params = Arrays.asList("SPRING", "summer");
        for (String name : params) {
            Season s = Season.valueOf(name);
            if (null != s) {
                System.out.println(s);
            } else {
                System.out.println("无相关枚举项");
            }
        }
    }


}
