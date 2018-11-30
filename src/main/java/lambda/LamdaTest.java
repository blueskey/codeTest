package lambda;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2016/6/1 0001.
 */
public class LamdaTest {
    public static void main(String[] args) {
        sort2();
        foreach();
        thread();
        filterTest();
        filterList();
        map();
        reduce();
        System.out.println("一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十".length());

    }

    public static void sort1() {
        String[] oldWay = "Improving code with Lambda expressions in Java 8".split(" ");
        Arrays.sort(oldWay, new Comparator<String>() {
            public int compare(String o1, String o2) {
                return o1.toLowerCase().compareTo(o2.toLowerCase());
            }
        });
        System.out.println(String.join(",", oldWay));
    }

    public static void sort2() {
        String[] oldWay = "Improving code with Lambda expressions in Java 8".split(" ");
        Arrays.sort(oldWay, (o1, o2) -> {
            return o1.toLowerCase().compareTo(o2.toLowerCase());
        });
        System.out.println(String.join(",", oldWay));
    }

    public static void foreach() {
        Integer[] ss={ 32, 342, 543, 3, 54, 574 };
        Arrays.asList(ss).forEach((n)->{
            System.out.println("我是 :"+n);
        });

        Map<String, String> map = new HashMap<>();
        map.put("aa", "11");
        map.put("bb", "22");
        map.put("cc", "33");
        //map不能为null
        map.forEach((k, v) -> {
            System.out.println(k + "--" + v);
        });


    }

    public static void thread() {
        Runnable oldR=new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ":oldR Runnable");
            }
        };

        Runnable newR=()->{
            System.out.println(Thread.currentThread().getName() + ":newR Runnable");
        };

        new Thread(oldR).start();
        new Thread(newR).start();

    }

    public static void filterTest() {
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

        System.out.println("Languages which starts with J :");
        filter(languages, (str) -> ((String) str).startsWith("J"));

        System.out.println("Languages which ends with a ");
        filter(languages, (str)->((String)str).endsWith("a"));

        System.out.println("Print all languages :");
        filter(languages, (str)->true);

        System.out.println("Print no language : ");
        filter(languages, (str)->false);

        System.out.println("Print language whose length greater than 4:");
        filter(languages, (str) -> ((String) str).length() > 4);
    }

    public static void filter(List<String> names, Predicate condition) {
        for(String name: names)  {
            if(condition.test(name)) {
                System.out.println(name + " ");
            }
        }
    }

    public static void filter1(List names, Predicate condition) {
        names.stream().filter((name) -> (condition.test(name))).forEach((name) -> {
            System.out.println(name + " ");
        });
    }

    public static void filterList() {
        List<String> strList = Arrays.asList("sfdsaf", "fdsfw", "2", "23", "aaad122");
        List<String> filtered = strList.stream().filter(x -> x.length() > 2)
                .collect(Collectors.toList());
        System.out.printf("Original List : %s, filtered list : %s %n",
                strList, filtered);
    }

    public static void map() {
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        for (Integer cost : costBeforeTax) {
            double price = cost + .12*cost;
            System.out.println(price);
        }

        List<Integer> ncostBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        ncostBeforeTax.stream().map((cost) -> cost + .12*cost).forEach(System.out::println);
    }

    public static void reduce() {
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        double total = 0;
        for (Integer cost : costBeforeTax) {
            double price = cost + .12*cost;
            total = total + price;
        }
        System.out.println("Total : " + total);

        // New way:
        List<Integer> ncostBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        double bill = ncostBeforeTax.stream().map((cost) -> cost + .12*cost).reduce((sum, cost) -> sum
                + cost).get();
        System.out.println("Total : " + bill);
    }
}
