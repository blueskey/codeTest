package rule.arraycollection;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by Administrator on 2016/6/22 0022.
 * 集合查找
 * indexOf使用equals比较
 * Collections.binarySearch采用二分法查找，而二分法查找准确的前提是排序。使用compareTo比较
 */
public class CollectionQueryTest {
    public static void main(String[] args) {
        List<String> cities = Lists.newArrayList();
        cities.add("上海");
        cities.add("广州");
        cities.add("广州");
        cities.add("深圳");
        cities.add("北京");
        cities.add("天津");

        int index1 = cities.indexOf("广州");

        int index2 = Collections.binarySearch(cities, "广州");
        System.out.println(index1 + "-------" + index2);//index1!=index2 ，Collections.binarySearch采用二分法查找，而二分法查找准确的前提是排序。

        hashMapTest();

        mutiThreadTest();
        realMutiThreadTest();
    }

    /**
     * 打乱整个列表
     *
     */
    public static void shuffle() {
        int tagColudNum = 10;
        List<String> tagClouds = Lists.newArrayList();
        Random random = new Random();
        for (int i = 0; i < tagColudNum; i++) {
            int randomPosition = random.nextInt(tagColudNum);
//            String temp = tagClouds.get(i); 　//1
//            tagClouds.set(i, tagClouds.get(randomPosition));//2
//            tagClouds.set(randomPosition, temp); //3

            Collections.swap(tagClouds, i, randomPosition);//相当于 前三行
        }
        Collections.shuffle(tagClouds);//相当于for{},打乱整个列表

    }

    /**
     * HashMap数据不要太多
     * HashMap的键值对会被封装为Entry对象
     */
    public static void hashMapTest() {
        Map<String, String> map = Maps.newHashMap();
        final Runtime rt = Runtime.getRuntime();
        rt.addShutdownHook(new Thread() {
            public void run() {
                StringBuffer sb = new StringBuffer();
                long haapMaxSize = rt.maxMemory() >> 20;
                sb.append("最大可用内存：" + haapMaxSize + "M\n");
                long total = rt.totalMemory() >> 20;
                sb.append("堆内在大小：" + total + "M\n");
                long free = rt.freeMemory() >> 20;
                sb.append("空闲内存：" + free + "M");
                System.out.println(sb);

            }
        });

        for (int i = 0; i < 393217; i++) {
            map.put("key" + i, "value" + i);
        }
    }

    /**
     * Exception in thread "Thread-2" java.util.ConcurrentModificationException
     at java.util.ArrayList$Itr.checkForComodification(ArrayList.java:901)
     at java.util.ArrayList$Itr.next(ArrayList.java:851)
     at rule.arraycollection.CollectionQueryTest$3.run(CollectionQueryTest.java:105)
     　换成Vector后，仍然有这个异常

     ConcurrentModificationException　与modCount修改计数器有关，这与线程同步是两码事
     */
    public static void mutiThreadTest() {
        final List<String> tickets = Lists.newArrayList();
        for (int i = 0; i < 100000; i++) {
            tickets.add("火车票" + i);
        }

        //退票
        Thread returnThread = new Thread(){
            @Override
            public void run() {
                while (true) {
                    tickets.add("火车票" + new Random().nextInt());
                }
            }
        };


        //售票
        Thread saleThread = new Thread(){
            @Override
            public void run() {
                for (String ticker : tickets) {
                    tickets.remove(ticker);
                }
            }
        };

        returnThread.start();
        saleThread.start();

    }

    /**
     * 多线程问题，可用Vector解决
     */
    public static void realMutiThreadTest() {
        final List<String> tickets = Lists.newArrayList();
        for (int i = 0; i < 100000; i++) {
            tickets.add("火车票" + i);
        }
        //售票
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    while (true) {
                        System.out.println(Thread.currentThread().getId() + "--" + tickets.remove(0));
                    }
                }
            }.start();
        }
    }
}

