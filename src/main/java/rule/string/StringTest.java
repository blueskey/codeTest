package rule.string;

import com.google.common.collect.Lists;

import java.io.UnsupportedEncodingException;
import java.text.Collator;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;

/**
 * Created by Administrator on 2016/6/16 0016.
 * 字符串常量池　（）
 * new操作不检查字符串池，也不会把对象放到池中
 * intern会检查当前的对象在对象池中是否有字面值相同的引用对象，如果有则返回池中的对象，没有则旋转到对象池中，并返回当前对象。
 *
 * 对象池纯种安全：String 是final类，不可继承；String类提供的所有方法中，如果要有String返回值，会新建一个String对象，不会对原对象进行修改。
 * 垃圾回收问题：不用考虑！字符串池非常特殊，它在编译期已经决定了其存在JVM的常量池，垃圾回收器不会对它进行回收的。
 */
public class StringTest {
    public static void main(String[] args) {
        String str1 = "哈哈";
        String str2 = "哈哈";
        String str3 = new String("哈哈");
        String str4 = str3.intern();
        String str5 = new String("哈哈呼呼");
        String str6 = str5.intern();
        String str7 = "哈哈呼呼";

        System.out.println(str1 == str2);//true
        System.out.println(str1 == str3);//false
        System.out.println(str1 == str4);//true
        System.out.println(str5 == str6);//false
        System.out.println(str6 == str7);//true

        System.out.println("$是$".replaceAll("$", ""));//$是$
        System.out.println("$是$".replace("$", ""));//是


        /**
         *  java对加号的处理机制：在使用加号进行计算的表达式中，只要遇到String，则所有的数据会转换成String类型进行拼接。
         *  如果是原始数据则直接拼接，如果是对象，调用用toString方法返回值拼接。
         *  在"+"表达式中，String有最高优先级
         */
        System.out.println(1+2+"apples");//3apples
        System.out.println("apples" + 1 + 2);//apples12

        Pattern pattern = Pattern.compile("\\b\\w+\\b");//\b表示一个单词的边界
        Matcher matcher = pattern.matcher("i like books");
        System.out.println(matcher.find());

        javaEncode();

        chineseSort();
    }

    /**
     * 字符串拼接方法：１、加号；２、concat方法；３、StringBuilder或StringBuffer 的append方法。
     * append最快，concat次之，加号最慢
     */
    private static void doWithString() {
        String str = "s";
        str += "c";
        str = str.concat("c");

        StringBuffer stringBuffer = new StringBuffer("s");
        stringBuffer.append("c");
    }

    /**
     * java程序涉及的编码包括两部分：
     *  （１）Java文件編碼：如果使用记事本创建一个.java后缀的文件，则文件编码格式就是操作系统默认的格式。如果使用IDE，则依赖于IDE的设置；
     *  （２）Class文件编码：通过javac命令生成的后缀名为.class文件是UTF-8编码的UNICODE文件，这在任何操作系统上都是一样的，
     *       只要是class文件就会是UNICODE格式。（UTF是UNICODE的存储和传输格式，是为了解决UNICODE的高位占用空间而产生的，使用UTF编码就标志着字符集使用的是UNICODE）
     *
     */
    private static void javaEncode() {
        String str = "汉字";
        byte[] bytes = new byte[0];
        try {
            bytes = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(new String(bytes));
    }

    /**
     * 大部分汉字排序使用Collator可以满足了，但不是所有汉字都可以正确排好！不要太责怪java了。
     * 中文　UNICODE字符集源于GB18030，GB18030又是从GB2312发展起来的，
     * GB2312是一个包含了7000多个字符的字符集，它是按照拼音排序，并且是连接的，
     * 之后的GBK 、GB18030都是在其基础上扩充出来的，要让它们完整排序难上加难
     */
    private static void chineseSort() {
        String[] strs = {"张三(Z)","李四(L)","王五(W)","鑫(X)","犇(B)"};
        Comparator c = Collator.getInstance(Locale.CHINA);
        Arrays.sort(strs, c);
        Arrays.asList(strs).forEach((str) -> {
            System.out.println(str);
        });
    }
}
