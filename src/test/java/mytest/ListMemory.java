package mytest;

import com.google.common.collect.Lists;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/2/15 0015.
 */
public class ListMemory {

    public static void newList() {
        ArrayList<Key> keys = Lists.newArrayList();
        for (int i = 0; 1 < 10000; i++) {
            keys.add(new Key("aa"));
            System.out.println("keys size=" + keys.size());
            System.out.println("free memonry after  is " + getFreeMemory() + " MB");
        }
    }

    public static void sleep(long sleepFor) {
        try {
            Thread.sleep(sleepFor);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //get available memory in MB
    public static long getFreeMemory() {
        return Runtime.getRuntime().freeMemory() / (1024 * 1024);
    }


    public static void main(String[] args) {

        for (int i = 0; i < 20; i++) {
            newList();
            sleep(1000);
        }
        System.out.println("free memonry  is " + getFreeMemory() + " MB");
    }


    static class Key {
        private String key;

        public Key(String key) {
            this.key = key;
        }
    }
}
